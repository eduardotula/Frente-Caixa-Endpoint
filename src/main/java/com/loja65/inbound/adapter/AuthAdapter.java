package com.loja65.inbound.adapter;

import com.loja65.outbound.adapters.entity.security.UserAuth;
import com.loja65.outbound.adapters.mysql.UserAuthAdapter;
import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@RequestScoped
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("/security/user")
public class AuthAdapter {


    @Inject
    UserAuthAdapter userAuthAdapter;

    @POST
    public Response addAuthUser(@QueryParam("username") String userName, @QueryParam("password") String password, @QueryParam("role") String role){
        userAuthAdapter.addUserAuth(new UserAuth(userName,password,role));
        return Response.ok().build();
    }
}
