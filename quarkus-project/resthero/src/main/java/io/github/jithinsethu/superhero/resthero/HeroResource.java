package io.github.jithinsethu.superhero.resthero;

import io.github.jithinsethu.superhero.utils.dtos.ResponseDTO;
import io.smallrye.mutiny.Uni;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.jboss.logging.Logger;
import org.jboss.resteasy.reactive.RestPath;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("/api/heroes")
@Produces(APPLICATION_JSON)
@Consumes(APPLICATION_JSON)
public class HeroResource {

    Logger logger = Logger.getLogger(HeroResource.class.getName());

    @Inject
    HeroService service;

    @GET
    @Path("/hello")
    public Uni<String> hello() {
        return Uni.createFrom().item("hello");
    }

    @Operation(summary = "Returns a random hero")
    @APIResponse(responseCode = "200", content = @Content(mediaType = APPLICATION_JSON,
            schema = @Schema(implementation = Hero.class, required = true) ))
    @GET
    @Path("/random")
    public Uni<Response> randomHeroes() {
        logger.debug("Random heroes");
        return service.findRandomHero().map(t -> new ResponseDTO("success", true, t))
                .onItem().transform(t -> Response.ok().entity(t).build());
    }


    @Operation(summary = "Returns all the heroes from the database")
    @APIResponse(responseCode = "200", content = @Content(mediaType = APPLICATION_JSON,
            schema = @Schema(implementation = ResponseDTO.class, type = SchemaType.ARRAY)))
    @APIResponse(responseCode = "204", description = "No heroes")
    @GET
    public Uni<Response> allHeroes() {
        return service.findAllHeroes().map(t -> new ResponseDTO("success", true, t))
                .onItem().transform(t -> Response.ok().entity(t).build());

    }

    @POST
    public Uni<Response> create(@Valid Hero hero) {
        return service.persistHero(hero).map(t -> new ResponseDTO("success", true, t))
                .onItem().transform(t -> Response.ok().status(Response.Status.CREATED).entity(t).build());

    }

    @Operation(summary = "Returns a hero for a given identifier")
    @APIResponse(responseCode = "200", content = @Content(mediaType = APPLICATION_JSON, schema = @Schema(implementation = Hero.class)))
    @APIResponse(responseCode = "204", description = "The hero is not found for a given identifier")
    @GET
    @Path("/{id}")
    public Uni<Response> getHero(@RestPath Long id) {
        return service.findHeroById(id).onItem().ifNull().failWith(new WebApplicationException("Not found", Response.Status.NOT_FOUND))
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
        return service.deleteHero(id).map(t -> t ? Response.ok().entity(new ResponseDTO("deleted", true)).build() :
                Response.ok().status(Response.Status.NOT_FOUND).entity(new ResponseDTO("failed to delete", false)).build());
    }
}
