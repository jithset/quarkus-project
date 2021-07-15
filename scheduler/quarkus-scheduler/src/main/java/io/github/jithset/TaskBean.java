package io.github.jithset;

import io.github.jithset.jobs.AeosJob;
import io.github.jithset.jobs.PartnerJob;
import io.github.jithset.services.QuartzServices;
import io.quarkus.runtime.ShutdownEvent;
import io.quarkus.runtime.StartupEvent;
import org.quartz.SchedulerException;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;

@ApplicationScoped
public class TaskBean {
    @Inject
    QuartzServices services;

//    void onStart(@Observes StartupEvent event) throws SchedulerException {
//        services.schedule(PartnerJob.class, 1L, 5);
//        services.schedule(AeosJob.class, 1L, 5);
//    }
//    void onStop(@Observes ShutdownEvent event) throws SchedulerException {
//        services.schedule(PartnerJob.class, 1L, 5);
//        services.schedule(AeosJob.class, 1L, 5);
//    }
//
//    void performTask() {
//        System.out.println("Running Job in "+ LocalDateTime.now().toString());
//    }
//
//    // A new instance of MyJob is created by Quartz for every job execution
//    public static class MyJob implements Job {
//
//        @Inject
//        TaskBean taskBean;
//
//        public void execute(JobExecutionContext context) throws JobExecutionException {
//            taskBean.performTask();
//        }
//
//    }
}
