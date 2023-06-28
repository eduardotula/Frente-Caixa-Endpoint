package com.loja65.alarmintelbras.domain.jobs;

import com.loja65.alarmintelbras.domain.exceptions.IntelbrasException;
import com.loja65.alarmintelbras.domain.model.Central;
import com.loja65.alarmintelbras.domain.usecase.IntelbrasUseCase;
import com.loja65.alarmintelbras.outbound.adapter.IntelbrasAdapter;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import javax.inject.Inject;
import java.io.IOException;
import java.util.ArrayList;

public class SetCentral implements Job {

    @Inject
    IntelbrasUseCase intelbrasUseCase;

    @ConfigProperty(name = "android.token")
    String token;

    @ConfigProperty(name = "android.androidId")
    String androidId;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        Central central = (Central) jobExecutionContext.getJobDetail().getJobDataMap().get("central");

        try {
            IntelbrasAdapter adapter = new IntelbrasAdapter(androidId,central.getCentralMac(),central.getPassword(),token);
            intelbrasUseCase.setAlarme(adapter);
        } catch (IOException | IntelbrasException e) {
            throw new IllegalArgumentException(String.format("Falha ao executar SetCentral de central :%s", central.getDescricao()));

        }

    }
}
