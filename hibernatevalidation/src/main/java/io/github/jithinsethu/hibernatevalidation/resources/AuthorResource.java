package io.github.jithinsethu.hibernatevalidation.resources;

import io.github.jithinsethu.hibernatevalidation.dtos.ResponseBodyDTO;
import io.github.jithinsethu.hibernatevalidation.models.Author;
import io.github.jithinsethu.hibernatevalidation.services.AuthorService;
import io.github.jithinsethu.hibernatevalidation.utils.handlers.RestCustomException;
import io.smallrye.mutiny.Uni;
import io.smallrye.mutiny.unchecked.Unchecked;

import javax.inject.Inject;
import javax.json.Json;
import javax.persistence.PersistenceException;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Path("author")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AuthorResource {

    @Inject
    AuthorService authorService;

    @GET
    public Uni<Response> get() {
        return authorService.getAllAuthors()
                .map(t -> new ResponseBodyDTO("msg", true, t))
                .onItem().transform(t -> Response.ok(t).build());
    }

    @GET
    @Path("/{name}")
    public Uni<Response> get(@PathParam("name") String name) {
        return authorService.findByName(name)
                .onItem().ifNotNull().transform(t -> Response.ok(new ResponseBodyDTO("success", true, t)).build())
                .onItem().ifNull().continueWith(Response.status(Response.Status.NOT_FOUND)
                        .entity(new ResponseBodyDTO("fail", false, null)).build());
    }

    @POST
    public Uni<Response> save(Author author) {
        return authorService.findByName(author.getName()).onItem().ifNotNull()
                .transform(t -> Response.ok(new ResponseBodyDTO("Author name already exist", false, null)).build())
                .onItem().ifNull().switchTo(
                authorService.saveAuthor(author)
                        .onItem().transform(t -> Response.ok(new ResponseBodyDTO("success", true, t)).build())
        );
    }
}
