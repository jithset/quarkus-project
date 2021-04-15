package io.github.jithinsethu.superheroe.resthero;

import io.github.jithinsethu.superheroe.resthero.dtos.ResponseDTO;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import org.jboss.logging.Logger;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/api/heroes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class HeroResource {

    Logger logger = Logger.getLogger(HeroResource.class.getName());

    @Inject
    HeroService service;

    @GET
    @Path("/random")
    public Uni<Response> randomHeroes() {
        return service.findRandomHero().map(t -> new ResponseDTO("success", true, t))
                .onItem().transform(t -> Response.ok().entity(t).build());
    }

    @GET
    public Multi<Response> allHeroes() {
        return service.findAllHeroes().map(t -> new ResponseDTO("success", true, t))
                .onItem().transform(t -> Response.ok().entity(t).build());

    }


    @POST
    public Uni<Response> create(@Valid Hero hero) {
        return service.persistHero(hero).map(t -> new ResponseDTO("success", true, t))
                .onItem().transform(t -> Response.ok().entity(t).build());

    }
}
