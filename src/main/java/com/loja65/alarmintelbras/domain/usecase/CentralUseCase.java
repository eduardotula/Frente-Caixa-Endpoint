package com.loja65.alarmintelbras.domain.usecase;


import com.loja65.alarmintelbras.domain.model.Central;
import com.loja65.alarmintelbras.domain.utils.JobScheduler;
import com.loja65.alarmintelbras.inbound.port.CentralPort;
import com.loja65.alarmintelbras.outbound.adapter.CentralAdapter;
import com.loja65.alarmintelbras.outbound.port.CentralScheduleJobPort;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;

@RequestScoped
public class CentralUseCase implements CentralPort {

    @Inject
    CentralAdapter adapter;
    @Inject
    CentralScheduleJobUseCase centralScheduleJobUseCase;
    @Inject
    JobScheduler jobScheduler;

    @Override
    @Transactional
    public Central update(Central central) {
        if (central.getCentralId() == null) {

            var existingCentral = adapter.findByMac(central.getCentralMac());
            if (Objects.isNull(existingCentral))
                throw new IllegalArgumentException(String.format("Central com mac %s não encontrado", central.getCentralMac()));

            central.setCentralId(existingCentral.getCentralId());
            central.setCentralScheduleJobList(existingCentral.getCentralScheduleJobList());
        }else {
            var existingCentral = adapter.findById(central.getCentralId());
            central.setCentralScheduleJobList(existingCentral.getCentralScheduleJobList());
        }

        centralScheduleJobUseCase.deleteScheduledJobsByCentral(central);
        central = adapter.save(central);
        var jobs = centralScheduleJobUseCase.createScheduleJobsByCentral(central);
        central.setCentralScheduleJobList(jobs);
        return central;
    }

    @Override
    public Central create(Central central) {
        central.setCentralId(null);
        if (Objects.nonNull(adapter.findByMac(central.getCentralMac())))
            throw new IllegalArgumentException("Central com endereço mac já cadastrado");

        central = adapter.save(central);
        var jobs = centralScheduleJobUseCase.createScheduleJobsByCentral(central);
        central.setCentralScheduleJobList(jobs);
        return central;
    }

    @Override
    @Transactional
    public void delete(int id) {
        var central = adapter.findById(id);
        centralScheduleJobUseCase.deleteScheduledJobsByCentral(central);

        if (Objects.isNull(central))
            throw new IllegalArgumentException(String.format("Central com id: %d não encontrado", id));
        adapter.delete(id);
    }

    @Override
    public List<String> getScheduledJobs() {
        return jobScheduler.getScheduledJobs();
    }


}
