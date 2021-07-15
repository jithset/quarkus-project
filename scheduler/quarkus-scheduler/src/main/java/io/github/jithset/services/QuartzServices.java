package io.github.jithset.services;

import lombok.extern.slf4j.Slf4j;
import org.quartz.*;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;
import java.util.Objects;


@ApplicationScoped
@Slf4j
public class QuartzServices {

    @Inject
    Scheduler quartz;

    public List<String> getAll() {
        try {
            return quartz.getJobGroupNames();
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void schedule(Class <? extends Job> jobClass, Long syncAccountId, Integer interval) {
        String jobName = jobClass.getSimpleName()+"SyncJob"+syncAccountId;
        String jobTriggerName = jobClass.getSimpleName()+"SyncTrigger"+syncAccountId;
        String jobGroupName = jobClass.getSimpleName()+"SyncGroup";

        JobDetail job = JobBuilder.newJob(jobClass)
                .withIdentity(jobName, jobGroupName)
                .usingJobData("accessAccountId", Objects.toString(syncAccountId)).storeDurably(true)
                .build();

        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity(jobTriggerName, jobGroupName)
                .startNow()
                .withSchedule(
                        SimpleScheduleBuilder.simpleSchedule()
                                .withIntervalInSeconds(interval)
                                .withMisfireHandlingInstructionNextWithRemainingCount()
                                .repeatForever())
                .build();

        try {
            quartz.scheduleJob(job, trigger);
        } catch (SchedulerException e) {
            log.error("Error: Job unschedule problem: {}", jobName);
        }

    }

    public void unschedule(Class <? extends Job> jobClass, Long syncAccountId) {
        String jobName = jobClass.getSimpleName()+"SyncJob"+syncAccountId;
        String jobGroupName = jobClass.getSimpleName()+"SyncGroup";

        try {
            quartz.deleteJob(JobKey.jobKey(jobName, jobGroupName));
        } catch (SchedulerException e) {
            log.error("Error: Job unschedule problem : {}", jobName);
        }
    }
}
