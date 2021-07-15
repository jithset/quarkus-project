package io.github.jithset.jobs;

import org.quartz.*;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.time.LocalDateTime;

@ApplicationScoped
public class PartnerJob implements Job {

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println("Partner Job Started "+ LocalDateTime.now().toString());
    }
}
