package com.loja65.alarmintelbras.outbound.port;

import com.loja65.alarmintelbras.domain.model.Central;

import java.util.List;

public interface CentralPort {

    Central save(Central central);

    List<Central> listAll();

    Central findById(int id);

    void delete(Integer id);
}
