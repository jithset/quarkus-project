package io.github.jithinsethu.superheroe.resthero.handlers;

import io.github.jithinsethu.superheroe.resthero.dtos.ResponseDTO;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class RestCustomExceptionHandler implements ExceptionMapper<Exception> {
    @Override
    public Response toResponse(Exception e) {
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