package io.github.jithinsethu.superheroe.resthero;

import io.github.jithinsethu.superheroe.resthero.dtos.ResponseDTO;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import org.jboss.logging.Logger;
import org.jboss.resteasy.reactive.RestPath;

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
        logger.debug("Random heroes");
        return service.findRandomHero().map(t -> new ResponseDTO("success", true, t))
                .onItem().transform(t -> Response.ok().entity(t).build());
    }

    @GET
    public Uni<Response> allHeroes() {
        return service.findAllHeroes().map(t -> new ResponseDTO("success", true, t))
                .onItem().transform(t -> Response.ok().entity(t).build());

    }

    @POST
    public Uni<Response> create(@Valid Hero hero) {
        return service.persistHero(hero).map(t -> new ResponseDTO("success", true, t))
                .onItem().transform(t -> Response.ok().entity(t).build());

    }

    @GET
    @Path("/{id}")
    public Uni<Response> getHero(@RestPath Long id) {
        return service.findHeroById(id).onItem().ifNull().failWith(new WebApplicationException("Not found", 200))
                .onItem().ifNotNull().transform(t -> new ResponseDTO("success", true, t))
                .onItem().transform(t -> Response.ok().entity(t).build());
    }

    @PUT
    public Uni<Response> updateHero(@Valid Hero hero) {
        return service.findHeroById(hero.id).onItem().ifNull().failWith(new WebApplicationException("Not found", 200))
                .onItem().ifNotNull().transformToUni(t -> service.updateHero(hero)).onItem().transform(t -> Response.ok().entity(t).build());
    }

    @DELETE
    @Path("/{id}")
    public Uni<Response> deleteHero(@RestPath Long id) {
        return service.deleteHero(id).map(t -> t? Response.ok().entity(new ResponseDTO("deleted", true)).build():
                Response.ok().status(Response.Status.NOT_FOUND).entity(new ResponseDTO("failed to delete", false)).build());
    }
}
