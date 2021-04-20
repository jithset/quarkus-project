package io.github.jithinsethu.superhero.restvillain;

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
import javax.ws.rs.core.Response;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("/api/villain")
@Produces(APPLICATION_JSON)
@Consumes(APPLICATION_JSON)
public class VillainResource {

    Logger logger = Logger.getLogger(VillainResource.class.getName());

    @Inject
    VillainService service;

    @GET
    @Path("/hello")
    public Uni<String> hello() {
        return Uni.createFrom().item("hello");
    }

    @Operation(summary = "Returns a random villain")
    @APIResponse(responseCode = "200", content = @Content(mediaType = APPLICATION_JSON,
            schema = @Schema(implementation = Villain.class, required = true) ))
    @GET
    @Path("/random")
    public Uni<Response> randomHeroes() {
        return service.findRandomVillain().map(t -> new ResponseDTO("success", true, t))
                .onItem().transform(t -> Response.ok().entity(t).build());
    }


    @Operation(summary = "Returns all the villain from the database")
    @APIResponse(responseCode = "200", content = @Content(mediaType = APPLICATION_JSON,
            schema = @Schema(implementation = ResponseDTO.class, type = SchemaType.ARRAY)))
    @APIResponse(responseCode = "204", description = "No villain")
    @GET
    public Uni<Response> allHeroes() {
        return service.findAllVillain().map(t -> new ResponseDTO("success", true, t))
                .onItem().transform(t -> Response.ok().entity(t).build());

    }

    @POST
    public Uni<Response> create(@Valid Villain villain) {
        return service.persistVillain(villain).map(t -> new ResponseDTO("success", true, t))
                .onItem().transform(t -> Response.ok().status(Response.Status.CREATED).entity(t).build());

    }

    @Operation(summary = "Returns a hero for a given identifier")
    @APIResponse(responseCode = "200", content = @Content(mediaType = APPLICATION_JSON, schema = @Schema(implementation = Villain.class)))
    @APIResponse(responseCode = "204", description = "The hero is not found for a given identifier")
    @GET
    @Path("/{id}")
    public Uni<Response> getHero(@RestPath Long id) {
        return service.findVillainById(id).onItem().ifNull().failWith(new WebApplicationException("Not found", Response.Status.NOT_FOUND))
                .onItem().ifNotNull().transform(t -> new ResponseDTO("success", true, t))
                .onItem().transform(t -> Response.ok().entity(t).build());
    }

    @PUT
    public Uni<Response> updateHero(@Valid Villain villain) {
        return service.findVillainById(villain.id).onItem().ifNull().failWith(new WebApplicationException("Not found", 200))
                .onItem().ifNotNull().transformToUni(t -> service.updateVillain(villain)).onItem().transform(t -> Response.ok().entity(t).build());
    }

    @DELETE
    @Path("/{id}")
    public Uni<Response> deleteHero(@RestPath Long id) {
        return service.deleteVillain(id).map(t -> t ? Response.ok().entity(new ResponseDTO("deleted", true)).build() :
                Response.ok().status(Response.Status.NOT_FOUND).entity(new ResponseDTO("failed to delete", false)).build());
    }
}
