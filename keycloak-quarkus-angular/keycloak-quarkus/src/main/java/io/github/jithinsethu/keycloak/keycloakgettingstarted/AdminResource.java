package io.github.jithinsethu.keycloak.keycloakgettingstarted;

import io.quarkus.security.Authenticated;
import io.smallrye.mutiny.Uni;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/api/admin")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Authenticated
public class AdminResource {


    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public Uni<String> admin() {
        return Uni.createFrom().item("Granted");
    }

}
