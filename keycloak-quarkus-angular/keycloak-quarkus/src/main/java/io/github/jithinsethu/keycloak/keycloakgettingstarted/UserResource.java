package io.github.jithinsethu.keycloak.keycloakgettingstarted;

import io.quarkus.security.identity.SecurityIdentity;
import io.smallrye.mutiny.Uni;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/api/users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@RequestScoped
public class UserResource {


    @Inject
    public SecurityIdentity identity;

    @GET
    public Uni<String> get() {
        return Uni.createFrom().item("User Get");
    }

/*
    public UserResource(SecurityIdentity identity) {
        this.identity = identity;
    }*/

    @GET
    @Path("/me")
    public Uni<User> me() {
        return Uni.createFrom().item(new User(identity));
    }

    public static class User {

        private final String userName;

        User(SecurityIdentity identity) {
            this.userName = identity.getPrincipal().getName();
        }

        public String getUserName() {
            return userName;
        }
    }

}
