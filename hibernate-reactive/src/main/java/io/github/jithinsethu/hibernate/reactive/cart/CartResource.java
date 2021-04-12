package io.github.jithinsethu.hibernate.reactive.cart;

import io.github.jithinsethu.hibernate.reactive.fruit.Fruit;
import io.smallrye.mutiny.Uni;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Arrays;
import java.util.List;

@Path("cart")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CartResource {

    @Inject
    CartService cartService;

    @Path("create")
    @POST
    public Uni<Response> create() {
        List<Fruit> fruits = Arrays.asList(new Fruit("Cherry"),
                new Fruit("Mango"));
        return cartService.create(1L, fruits).onItem().ifNotNull().transform(t -> Response.ok().entity(t).build());
    }
}
