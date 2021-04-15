package io.github.jithinsethu.hibernatevalidation.resources;

import io.github.jithinsethu.hibernatevalidation.dtos.BookCreateDTO;
import io.github.jithinsethu.hibernatevalidation.dtos.ResponseBodyDTO;
import io.github.jithinsethu.hibernatevalidation.models.Book;
import io.github.jithinsethu.hibernatevalidation.services.BookService;
import io.github.jithinsethu.hibernatevalidation.utils.mappers.BookMapper;
import io.smallrye.mutiny.Uni;
import org.jboss.logmanager.Logger;

import javax.inject.Inject;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Set;

@Path("book")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class BookResource {

    private static final Logger LOG = Logger.getLogger(BookResource.class.getName());

    @Inject
    Validator validator;

    @Inject
    BookMapper bookMapper;

    @Inject
    BookService bookService;

    @GET
    public String get() {
        return "How are you?";
    }

    @POST
    public Uni<Response> create(BookCreateDTO dto) {
        Set<ConstraintViolation<BookCreateDTO>> violations = validator.validate(dto);

        System.out.println("Resource Current thread "+ Thread.currentThread().getName());
        if (violations.isEmpty()) {
            Book book = bookMapper.fromBookDTO(dto);
            LOG.info(book.toString());
            return bookService.createBook(book).onItem().transform(t ->Response.ok(t).build());
        } else {
            return Uni.createFrom().item(new ResponseBodyDTO(violations))
                    .onItem().transform(t -> Response.status(Response.Status.BAD_REQUEST).entity(t).build());
        }
    }
}
