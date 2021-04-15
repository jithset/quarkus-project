package io.github.jithinsethu.hibernatevalidation.resources;

import io.github.jithinsethu.hibernatevalidation.models.Employee;
import io.github.jithinsethu.hibernatevalidation.services.EmployeeService;
import io.smallrye.mutiny.Uni;
import io.vertx.pgclient.PgException;

import org.jboss.logging.Logger;
import org.jboss.resteasy.reactive.RestPath;

import javax.inject.Inject;
import javax.persistence.PersistenceException;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Optional;

@Path("employee")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class EmpoyeeResource {

    private static final Logger logger = Logger.getLogger(EmpoyeeResource.class.getName());
    @Inject
    EmployeeService employeeService;

    @POST
    public Uni<Employee> addNewProduct(Employee product) {
        Employee employee = new Employee();
        employee.setName("TEST how are you");
        System.out.println("Create");
        //employeeService.create(employee);
        return employeeService.create(employee);
                //.onFailure(PersistenceException.class).invoke(th -> handleException(th));
    }

    private void handleException(Throwable th) {
        PersistenceException pEx = (PersistenceException)th;
        Optional<Throwable> opt = Optional.ofNullable(pEx.getCause().getCause());
        opt.ifPresent(pgExTh -> {
            if(pgExTh instanceof PgException) {
                PgException pgX = (PgException) pgExTh;
                logger.error(">>>>>>>>>>>>>>. PgException --- Message = " + pgX.getMessage() + " --- Root Cause = " + pgX + " --- Localized Message = " + pgX.getLocalizedMessage() + " <<<<<<<<<<<<<<<");

                logger.error("PGXXXX Caught --- Detail = " + pgX.getDetail() + " --- Message = " + pgX.getMessage() + " -- Localized Message = " + pgX.getLocalizedMessage() + " --- Code = " + pgX.getCode());

            } else {
                logger.error(">>>>>>>>>>>>>>. persistenceException --- Message = " + pgExTh.getMessage() + " --- Root Cause = " + pgExTh + " --- Localized Message = " + pgExTh.getLocalizedMessage() + " <<<<<<<<<<<<<<<");
            }
        });
        opt.orElse(th);
    }
}
