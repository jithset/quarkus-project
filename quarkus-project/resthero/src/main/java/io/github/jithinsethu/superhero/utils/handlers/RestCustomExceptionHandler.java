package io.github.jithinsethu.superhero.utils.handlers;

import io.github.jithinsethu.superhero.utils.dtos.ResponseDTO;
import org.jboss.logging.Logger;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class RestCustomExceptionHandler implements ExceptionMapper<Exception> {

    Logger logger = Logger.getLogger(RestCustomExceptionHandler.class.getName());

    @Override
    public Response toResponse(Exception e) {
        logger.error(e.getMessage());
        e.printStackTrace();
        int code = 500;
        ResponseDTO dto = new ResponseDTO();
        dto.status = false;
        dto.message = e.getMessage();
        dto.data = e.getLocalizedMessage();
        if (e instanceof WebApplicationException) {
            code = ((WebApplicationException) e).getResponse().getStatus();
        }

        return Response.status(code).entity(dto).build();
    }
}