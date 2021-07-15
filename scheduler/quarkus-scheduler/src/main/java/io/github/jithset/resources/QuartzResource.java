package io.github.jithset.resources;

import io.github.jithset.jobs.AeosJob;
import io.github.jithset.jobs.PartnerJob;
import io.github.jithset.services.QuartzServices;
import org.quartz.*;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import java.util.List;

@Path("/quartz")
public class QuartzResource {

    @Inject
    QuartzServices quartzServices;


    @GET
    @Path("/services")
    public List<String> services()  {
        System.out.println("Quarts AEOS Job Started");
        return quartzServices.getAll();

    }

    @GET
    @Path("/start")
    public void start() throws SchedulerException {
        System.out.println("Quarts AEOS Job Started");
        quartzServices.schedule(AeosJob.class, 1L, 5);

    }

    @GET
    @Path("/stop")
    public void stop() throws SchedulerException {
        System.out.println("Quarts AEOS Stop Job");
        quartzServices.unschedule(AeosJob.class, 1L);

    }

    @GET
    @Path("/startp")
    public void startP() throws SchedulerException {
        System.out.println("Quarts Partner Job Started");
        quartzServices.schedule(PartnerJob.class, 1L, 5);

    }

    @GET
    @Path("/stopp")
    public void stopP() throws SchedulerException {
        System.out.println("Quarts Partner Stop Job");
        quartzServices.unschedule(PartnerJob.class, 1L);

    }

}
