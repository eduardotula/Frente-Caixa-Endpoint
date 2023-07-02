package com.loja65.alarmintelbras.domain.jobs;

import com.loja65.alarmintelbras.domain.exceptions.IntelbrasException;
import com.loja65.alarmintelbras.domain.model.Central;
import com.loja65.alarmintelbras.domain.usecase.IntelbrasUseCase;
import com.loja65.alarmintelbras.domain.utils.CronUtils;
import com.loja65.alarmintelbras.domain.utils.JobScheduler;
import com.loja65.alarmintelbras.outbound.adapter.IntelbrasAdapter;
import com.loja65.frentecaixa.domain.utils.DefaultTimeZone;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.SchedulerException;

import javax.inject.Inject;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class ActivateJob implements Job {

    @Inject
    IntelbrasUseCase intelbrasUseCase;
    @Inject
    JobScheduler scheduler;

    @Inject
    CronUtils cronUtils;
    @Inject
    DefaultTimeZone defaultTimeZone;

    @ConfigProperty(name = "android.token")
    String token;

    @ConfigProperty(name = "android.androidId")
    String androidId;
    @ConfigProperty(name = "intelbras.retryConnection")
    Integer retryTime;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) {
        Central central = (Central) jobExecutionContext.getJobDetail().getJobDataMap().get("Central");

        try {
            IntelbrasAdapter adapter = new IntelbrasAdapter(androidId, central.getCentralMac(), central.getPassword(), token);
            intelbrasUseCase.activateAlarme(adapter);
            adapter.close();
        } catch (Exception e) {
            e.printStackTrace();
            createRetryJob(central);
        }
    }

    private void createRetryJob(Central central){
        var convertedActivation = defaultTimeZone.convertToSystemZoneId(LocalDateTime.now().plus(retryTime, ChronoUnit.MINUTES));
        String activateCron = cronUtils.toCronHourSingleUse(convertedActivation);
        String activationJobNameRetry = central.getActivationJobRetryName(convertedActivation);

        try {
            scheduler.createJob(activationJobNameRetry, activateCron, ActivateJob.class, central);
        } catch (SchedulerException e) {
            e.printStackTrace();
            System.out.println("Falha ao criar agendamento de retry");
        }
    }

}
