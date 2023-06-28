package com.loja65.alarmintelbras.domain.usecase;


import com.loja65.alarmintelbras.domain.model.Central;
import com.loja65.alarmintelbras.inbound.port.CentralPort;
import com.loja65.alarmintelbras.outbound.adapter.CentralAdapter;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.Objects;

@RequestScoped
public class CentralUseCase implements CentralPort {

    @Inject
    CentralAdapter adapter;
    @Inject
    CentralScheduleJobUseCase centralScheduleJobUseCase;

    @Override
    @Transactional
    public Central save(Central central) {
        var jobs = centralScheduleJobUseCase.createScheduleJobsByCentral(central);
        central = adapter.save(central);
        central.setCentralScheduleJobList(jobs);
        return central;
    }

    @Override
    @Transactional
    public void delete(int id) {
        var central = adapter.findById(id);
        if (Objects.isNull(central))
            throw new IllegalArgumentException(String.format("Central com id: %d não encontrado", id));
        adapter.delete(id);
    }


}
