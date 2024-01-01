package com.loja65.alarmintelbras.domain.usecase;

import com.loja65.alarmintelbras.domain.jobs.ActivateJob;
import com.loja65.alarmintelbras.domain.jobs.DisableJob;
import com.loja65.alarmintelbras.domain.model.Central;
import com.loja65.alarmintelbras.domain.model.CentralScheduleJob;
import com.loja65.alarmintelbras.domain.utils.CronUtils;
import com.loja65.alarmintelbras.domain.utils.JobScheduler;
import com.loja65.alarmintelbras.outbound.port.CentralPort;
import com.loja65.alarmintelbras.outbound.port.CentralScheduleJobPort;
import com.loja65.frentecaixa.domain.utils.DefaultTimeZone;
import io.quarkus.runtime.StartupEvent;
import org.quartz.SchedulerException;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
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
    @Inject
    DefaultTimeZone defaultTimeZone;

    @Transactional
    public List<CentralScheduleJob> createScheduleJobsByCentral(Central central) {
        String activationJobName = central.getActivationJobName();
        String disableJobName = central.getDisableJobName();

        var convertedActivation = defaultTimeZone.convertToSystemZoneId(LocalDateTime.of(LocalDate.now(), central.getActivationTime()));
        var convertedDisable = defaultTimeZone.convertToSystemZoneId(LocalDateTime.of(LocalDate.now(), central.getDeactivationTime()));
        String activeCron = utils.toCronHour(convertedActivation.toLocalTime());
        String disableCron = utils.toCronHour(convertedDisable.toLocalTime());

        try {
            scheduler.createJob(activationJobName, activeCron, ActivateJob.class, central);
        } catch (SchedulerException e) {
            throw new IllegalStateException(String.format("Falha ao criar agendamentos de central :%s", e.getMessage()));
        }

        try {
            scheduler.createJob(disableJobName, disableCron, DisableJob.class, central);
        } catch (SchedulerException e) {
            scheduler.deleteJob(activationJobName);
            throw new IllegalStateException(String.format("Falha ao criar agendamentos de central :%s", e.getMessage()));
        }

        List<CentralScheduleJob> jobs = new ArrayList<>();
        jobs.add(adapter.save(CentralScheduleJob.builder().centralId(central.getCentralId())
                .description(activationJobName).runningTime(central.getActivationTime()).build()));

        jobs.add(adapter.save(CentralScheduleJob.builder().centralId(central.getCentralId())
                .description(disableJobName).runningTime(central.getDeactivationTime()).build()));

        central.setCentralScheduleJobList(jobs);
        centralAdapter.save(central);
        return jobs;
    }

    @Transactional
    public void deleteScheduledJobsByCentral(Central central){
        scheduler.deleteJob(central.getActivationJobName());
        scheduler.deleteJob(central.getDisableJobName());

        central.getCentralScheduleJobList().forEach(e -> {
            adapter.delete(e.getCentralScheduleJobId());
        });
    }

    @Transactional
    public void createScheduleAllCentral(@Observes StartupEvent ev) {
        var centrals = centralAdapter.listAll();
        for (Central central : centrals){
            deleteScheduledJobsByCentral(central);
            central.setCentralScheduleJobList(new ArrayList<>());
            createScheduleJobsByCentral(central);
        }
    }


}
