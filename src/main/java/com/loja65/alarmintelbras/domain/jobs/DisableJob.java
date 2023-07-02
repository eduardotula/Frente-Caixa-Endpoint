package com.loja65.alarmintelbras.domain.jobs;

import com.loja65.alarmintelbras.domain.exceptions.IntelbrasException;
import com.loja65.alarmintelbras.domain.model.Central;
import com.loja65.alarmintelbras.domain.usecase.IntelbrasUseCase;
import com.loja65.alarmintelbras.outbound.adapter.IntelbrasAdapter;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.quartz.Job;
import org.quartz.JobExecutionContext;

import javax.inject.Inject;
import java.io.IOException;

public class DisableJob implements Job {

    @Inject
    IntelbrasUseCase intelbrasUseCase;

    @ConfigProperty(name = "android.token")
    String token;

    @ConfigProperty(name = "android.androidId")
    String androidId;

    @Override
    public void execute(JobExecutionContext jobExecutionContext){
        Central central = (Central) jobExecutionContext.getJobDetail().getJobDataMap().get("Central");

        try {
            IntelbrasAdapter adapter = new IntelbrasAdapter(androidId,central.getCentralMac(),central.getPassword(),token);
            intelbrasUseCase.disableAlarme(adapter);
            adapter.close();
        } catch (IOException e) {
            throw new IllegalArgumentException(String.format("Falha ao executar SetCentral de central :%s", central.getDescricao()));

        } catch (IntelbrasException e){
            System.out.println(e.getMessage());
        }

    }


}
