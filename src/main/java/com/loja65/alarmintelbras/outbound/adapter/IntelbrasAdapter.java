package com.loja65.alarmintelbras.outbound.adapter;

import com.loja65.alarmintelbras.domain.enums.CentralStatusEnum;
import com.loja65.alarmintelbras.outbound.port.IntelbrasPort;
import org.eclipse.microprofile.config.ConfigProvider;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Singleton;
import java.io.IOException;
import java.net.Socket;
import java.util.*;

public class IntelbrasAdapter implements IntelbrasPort {


    List<Integer> androidId = new ArrayList<>();
    final List<Integer> centralMac = new ArrayList<>();
    List<Integer> password;
    List<Integer> token;
    String address;
    int port;
    Socket socket;
    static int COMANDO_CONEXAO = 229;
    static int DISPOSITIVO_TYPE = 2;

    public IntelbrasAdapter(String androidId, String centralMacHex, String password, String token) throws IOException {
        this.address = ConfigProvider.getConfig().getValue("intelbras.address", String.class);
        this.port =  ConfigProvider.getConfig().getValue("intelbras.port", Integer.class);
        this.socket = new Socket(address, port);
        this.socket.setSoTimeout(10000);

        this.token = convertToIntValues(token);
        this.password = convertToIntValues(password);

        for (int i = 0; i < androidId.length(); i += 2) {
            String hex = androidId.substring(i, i + 2);
            this.androidId.add(Integer.parseInt(hex, 16));
        }

        for (int i = 0; i < centralMacHex.length(); i += 2) {
            String hex = centralMacHex.substring(i, i + 2);
            centralMac.add(Integer.parseInt(hex, 16));
        }
    }

    @Override
    public void close() throws IOException {
        socket.close();
    }

    @Override
    public List<Integer> sendAutorization() throws IOException {
        List<Integer> finalBody = new ArrayList<>();
        finalBody.add(0);
        finalBody.add(COMANDO_CONEXAO);
        finalBody.add(DISPOSITIVO_TYPE);
        finalBody.addAll(androidId);
        finalBody.addAll(centralMac);

        //Calcular checkSum token
        finalBody.add(calculateChecksum(this.token));
        finalBody.add(69);
        finalBody.addAll(Arrays.asList(0, 0, 0, 0, 3, 0));

        //Adicionar token em body
        finalBody.addAll(token);

        finalBody.set(0, finalBody.size() - 1);
        finalBody.add(calculateChecksum(finalBody));

        sendCommand(finalBody);

        return readResponseData(2);
    }

    @Override
    public List<Integer> activateAlarm() throws IOException {
        //socket?.add([9, 233, 33, 55, 50, 54, 48, 65, 65, 33, 28]);
        List<Integer> command = new ArrayList<>();
        command.addAll(Arrays.asList(9, 233, 33));
        command.addAll(password);
        command.addAll(Arrays.asList(65, 65, 33));
        command.add(calculateChecksum(command));
        sendCommand(command);
        return readResponseData(4);
    }

    @Override
    public List<Integer> deactivateAlarm() throws IOException {
        List<Integer> command = new ArrayList<>();
        command.addAll(Arrays.asList(9, 233, 33));
        command.addAll(password);
        command.addAll(Arrays.asList(68, 65, 33));
        command.add(calculateChecksum(command));
        sendCommand(command);
        return readResponseData(4);
    }

    @Override
    public List<Integer> getCentralStatus() throws IOException {
        List<Integer> command = new ArrayList<>();
        command.addAll(Arrays.asList(8, 233, 33));
        command.addAll(password);
        command.addAll(Arrays.asList(90, 33));
        command.add(calculateChecksum(command));
        sendCommand(command);

        return readResponseData(46);
    }

    @Override
    public CentralStatusEnum getCentralStatusSimple() throws IOException {
        List<Integer> statusCentral = getCentralStatus();

        if (statusCentral.get(23) == 0) {
            return CentralStatusEnum.CENTRAL_DESATIVADA;
        } else if (statusCentral.get(23) == 3) {
            return CentralStatusEnum.CENTRAL_ATIVA;
        }

        return CentralStatusEnum.DESCONHECIDO;
    }

    @Override
    public void sendCommand(List<Integer> command) throws IOException {
        this.socket.getOutputStream().write(convertToByteArr((command)));
    }

    public static int calculateChecksum(List<Integer> bodyData) {
        int xor = 0;
        for (int bodyDatum : bodyData) {
            xor ^= bodyDatum;
        }
        xor ^= 255;
        return xor;
    }

    private byte[] convertToByteArr(List<Integer> intArr) {
        byte[] finalBytes = new byte[intArr.size()];
        for (int i = 0; i < intArr.size(); i++) {
            finalBytes[i] = (byte) intArr.get(i).intValue();
        }
        return finalBytes;
    }

    private List<Integer> convertToIntValues(String string) {
        List<Integer> finalArr = new ArrayList<>();
        for (char c : string.toCharArray()) {
            finalArr.add((int) c);
        }
        return finalArr;
    }

    private List<Integer> readResponseData(int limit) throws IOException {
        List<Integer> response = new ArrayList<>();
        for (int i = 0; i < limit; i++) {
            response.add(this.socket.getInputStream().read());
        }
        return response;
    }

}
