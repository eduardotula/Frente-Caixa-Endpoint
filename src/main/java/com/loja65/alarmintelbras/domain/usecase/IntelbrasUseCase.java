package com.loja65.alarmintelbras.domain.usecase;

import com.loja65.alarmintelbras.domain.enums.CentralStatusEnum;
import com.loja65.alarmintelbras.domain.exceptions.IntelbrasException;
import com.loja65.alarmintelbras.outbound.adapter.IntelbrasAdapter;

import javax.enterprise.context.ApplicationScoped;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@ApplicationScoped
public class IntelbrasUseCase {

    public void setAlarme(IntelbrasAdapter adapter) throws IntelbrasException {
        try {
            var response = validateConnectionAndAuth(adapter);
            if (response != CentralStatusEnum.CENTRAL_CONECTADA) {
                throw new IllegalStateException("Central Desconectada");
            }

            var status = adapter.getCentralStatusSimple();
            if (status == CentralStatusEnum.CENTRAL_ATIVA) {
                adapter.deactivateAlarm();
            } else if (status == CentralStatusEnum.CENTRAL_DESATIVADA) {
                adapter.activateAlarm();
            }
        } catch (Exception e) {
            throw new IntelbrasException(e);
        }
    }

    public void activateAlarme(IntelbrasAdapter adapter) throws IntelbrasException {
        try {
            var response = validateConnectionAndAuth(adapter);
            if (response != CentralStatusEnum.CENTRAL_CONECTADA) {
                throw new IllegalStateException("Central Desconectada");
            }

            var status = adapter.getCentralStatusSimple();
            if (status == CentralStatusEnum.CENTRAL_DESATIVADA) {
                adapter.activateAlarm();
            }
        } catch (Exception e) {
            throw new IntelbrasException(e);
        }
    }

    public void disableAlarme(IntelbrasAdapter adapter) throws IntelbrasException {
        try {
            var response = validateConnectionAndAuth(adapter);
            if (response != CentralStatusEnum.CENTRAL_CONECTADA) {
                throw new IllegalStateException("Central Desconectada");
            }

            var status = adapter.getCentralStatusSimple();
            if (status == CentralStatusEnum.CENTRAL_ATIVA) {
                adapter.deactivateAlarm();
            }
        } catch (Exception e) {
            throw new IntelbrasException(e);
        }
    }

    private CentralStatusEnum validateConnectionAndAuth(
            IntelbrasAdapter adapter) throws IOException {
        List<Integer> response = adapter.sendAutorization();

        //Resposta Sucesso
        if (responseComparator(Arrays.asList(230, 69), response)) {
            return CentralStatusEnum.CENTRAL_CONECTADA;
        }

        //Central desconectada
        if (responseComparator(Arrays.asList(228), response)) {
            return CentralStatusEnum.CENTRAL_DESCONECTADA;
        }

        return CentralStatusEnum.DESCONHECIDO;
    }

    private boolean responseComparator(List<Integer> expected, List<Integer> actual) {
        if (expected.size() != actual.size()) {
            return false;
        }

        for (int element : expected) {
            if (!actual.contains(element)) {
                return false;
            }
        }
        return true;
    }
}
