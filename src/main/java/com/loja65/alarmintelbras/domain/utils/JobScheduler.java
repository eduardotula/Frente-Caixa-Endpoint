package com.loja65.alarmintelbras.domain.utils;

import org.quartz.*;
import org.quartz.core.QuartzScheduler;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.Date;

@ApplicationScoped
public class JobScheduler {

    @Inject
    QuartzScheduler quartzScheduler;
    @Inject
    Scheduler scheduler;

    public Date createJob(String jobName, String cron, Class job, Object... jobParameters) throws SchedulerException {
        var activateJob = JobBuilder.newJob(job).withIdentity(jobName).build();

        for (Object jobParameter : jobParameters)
            activateJob.getJobDataMap().put(jobParameter.getClass().getName(), jobParameter);

        var activationTrigger = TriggerBuilder.newTrigger().withSchedule(
                CronScheduleBuilder.cronSchedule(cron)).build();
        return quartzScheduler.scheduleJob(activateJob, activationTrigger);
    }

    public void deleteJob(String jobName) {
        try {
            scheduler.deleteJob(new JobKey(jobName));
        } catch (SchedulerException e) {
            throw new IllegalStateException(String.format("Falha ao deletar agendamento %s"));
        }
    }


}
