package org.acme.vertx;

import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import io.vertx.mutiny.pgclient.PgPool;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.jboss.resteasy.reactive.RestPath;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/fruits")
public class FruitResource {

    @Inject
    FruitRepo repo;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Multi<Fruit> getAll() {
        System.out.println("Thread Resource Current "+ Thread.currentThread().getName());
        return repo.getAll();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public Uni<Fruit> getById(@RestPath("id") Long id) {
        System.out.println("Thread Resource Current "+ Thread.currentThread().getName());
        return repo.findById(id);
    }
}