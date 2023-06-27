package com.loja65.frentecaixa.inbound.adapter;


import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;


@Path("/")
public class Test {


    @GET
    public Response getAlive(){
        return Response.ok("Alive").build();
    }
}
