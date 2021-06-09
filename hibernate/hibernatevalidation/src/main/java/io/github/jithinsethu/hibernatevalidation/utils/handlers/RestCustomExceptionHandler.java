package io.github.jithinsethu.hibernatevalidation.utils.handlers;

import io.github.jithinsethu.hibernatevalidation.dtos.ResponseBodyDTO;
import org.hibernate.HibernateException;

import javax.json.Json;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

//@Provider
public class RestCustomExceptionHandler implements ExceptionMapper<Exception> {
    @Override
    public Response toResponse(Exception e) {
        int code = 500;
        ResponseBodyDTO dto = new ResponseBodyDTO();
        dto.status = false;
        dto.message = e.getMessage();
        dto.data = e.getLocalizedMessage();
        if (e instanceof WebApplicationException) {
            code = ((WebApplicationException) e).getResponse().getStatus();
        }
        if (e instanceof RestCustomException) {
            code = ((RestCustomException) e).getStatus();
        }

        return Response.status(code).entity(dto).build();
    }
}
