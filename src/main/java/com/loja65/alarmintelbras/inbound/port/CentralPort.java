package com.loja65.alarmintelbras.inbound.port;

import com.loja65.alarmintelbras.domain.model.Central;

import javax.resource.spi.IllegalStateException;

public interface CentralPort {

    Central save(Central central) throws IllegalStateException;

    void delete(int id);
}
