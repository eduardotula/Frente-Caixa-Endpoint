package com.loja65.alarmintelbras.outbound.port;

import com.loja65.alarmintelbras.domain.enums.CentralStatusEnum;

import java.io.IOException;
import java.util.List;

public interface IntelbrasPort {


    void close() throws IOException;

    List<Integer> sendAutorization() throws IOException;

    List<Integer> activateAlarm() throws IOException;

    List<Integer> deactivateAlarm() throws IOException;

    List<Integer> getCentralStatus() throws IOException;

    CentralStatusEnum getCentralStatusSimple() throws IOException;

    void sendCommand(List<Integer> command) throws IOException;
}
