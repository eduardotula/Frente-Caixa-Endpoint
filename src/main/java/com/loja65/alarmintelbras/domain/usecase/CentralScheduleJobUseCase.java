package com.loja65.alarmintelbras.domain.usecase;

import com.loja65.alarmintelbras.domain.jobs.SetCentral;
import com.loja65.alarmintelbras.domain.model.Central;
import com.loja65.alarmintelbras.domain.model.CentralScheduleJob;
import com.loja65.alarmintelbras.domain.utils.CronUtils;
import com.loja65.alarmintelbras.domain.utils.JobScheduler;
import com.loja65.alarmintelbras.outbound.port.CentralPort;
import com.loja65.alarmintelbras.outbound.port.CentralScheduleJobPort;
import org.quartz.SchedulerException;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class CentralScheduleJobUseCase {

    @Inject
    CentralScheduleJobPort adapter;
    @Inject
    CentralPort centralAdapter;
    @Inject
    CronUtils utils;
    @Inject
    JobScheduler scheduler;

    @Transactional
    public List<CentralScheduleJob> createScheduleJobsByCentral(Central central) {
        String activationJobName = String.format("active_%s", central.getDescricao());
        String disableJobName = String.format("disable_%s", central.getDescricao());

        String activeCron = utils.toCron(central.getActivationTime());
        String disableCron = utils.toCron(central.getActivationTime());

        try {
            scheduler.createJob(activeCron, activeCron, SetCentral.class, central);
        } catch (SchedulerException e) {
            throw new IllegalStateException(String.format("Falha ao criar agendamentos de central :%s", e.getMessage()));
        }

        try {
            scheduler.createJob(disableJobName, disableCron, SetCentral.class, central);
        } catch (SchedulerException e) {
            scheduler.deleteJob(activationJobName);
            throw new IllegalStateException(String.format("Falha ao criar agendamentos de central :%s", e.getMessage()));
        }

        List<CentralScheduleJob> jobs = new ArrayList<>();
        jobs.add(adapter.save(CentralScheduleJob.builder().centralId(central.getCentralId())
                .description(activationJobName).running_time(central.getActivationTime()).build()));

        jobs.add(adapter.save(CentralScheduleJob.builder().centralId(central.getCentralId())
                .description(disableCron).running_time(central.getDeactivationTime()).build()));

        return jobs;
    }

    public void createScheduleAllCentral() {
        var centrals = centralAdapter.listAll();
        for (Central central : centrals) createScheduleJobsByCentral(central);
    }


}
