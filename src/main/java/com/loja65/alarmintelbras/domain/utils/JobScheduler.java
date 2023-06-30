package com.loja65.alarmintelbras.domain.utils;

import org.quartz.*;
import org.quartz.impl.matchers.GroupMatcher;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@ApplicationScoped
public class JobScheduler {

    @Inject
    org.quartz.Scheduler quartz;

    public Date createJob(String jobName, String cron, Class job, Object... jobParameters) throws SchedulerException {
        var activateJob = JobBuilder.newJob(job).withIdentity(jobName).build();

        for (Object jobParameter : jobParameters)
            activateJob.getJobDataMap().put(jobParameter.getClass().getSimpleName(), jobParameter);

        var activationTrigger = TriggerBuilder.newTrigger().withSchedule(
                CronScheduleBuilder.cronSchedule(cron)).build();

        return quartz.scheduleJob(activateJob, activationTrigger);
    }

    public List<String> getScheduledJobs() {
        List<String> jobsList = new ArrayList<>();

        try {
            for (String groupName : quartz.getJobGroupNames()) {

                for (JobKey jobKey : quartz.getJobKeys(GroupMatcher.jobGroupEquals(groupName))) {

                    String jobName = jobKey.getName();

                    //get job's trigger
                    List<Trigger> triggers = (List<Trigger>) quartz.getTriggersOfJob(jobKey);
                    Date nextFireTime = triggers.get(0).getNextFireTime();

                    jobsList.add("[jobName] : " + jobName + " - " + nextFireTime);

                }
            }
        } catch (SchedulerException e) {
            throw new IllegalStateException("Falha ao obter agendamentos");
        }
        return jobsList;
    }

    public void deleteJob(String jobName) {
        try {
            quartz.deleteJob(new JobKey(jobName));
        } catch (SchedulerException e) {
            throw new IllegalStateException(String.format("Falha ao deletar agendamento %s"));
        }
    }


}
