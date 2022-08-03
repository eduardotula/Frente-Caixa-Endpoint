package com.loja65.inbound.adapter.exceptions;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class ExceptionHandler implements ExceptionMapper<Exception> {

    @Override
    public Response toResponse(Exception e) {
        return Response.status(Response.Status.BAD_REQUEST.getStatusCode())
                .entity(String.format("Erro: %s",e.getMessage())).build();
    }
}
