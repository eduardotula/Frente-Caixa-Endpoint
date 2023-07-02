package com.loja65.alarmintelbras.inbound.adapter;

import com.loja65.alarmintelbras.inbound.adapter.dto.CentralDto;
import com.loja65.alarmintelbras.inbound.adapter.mappers.CentralDtoMapper;
import com.loja65.alarmintelbras.inbound.port.CentralPort;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;

import javax.annotation.security.RolesAllowed;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.stream.Collectors;

@RequestScoped
@Path("/central")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CentralRestAdapter {

    @Inject
    CentralDtoMapper mapper;
    @Inject
    CentralPort centralPort;


    @POST
    @RolesAllowed({"admin", "loja"})
    @Path("/")
    @Operation(summary = "Create central")
    @APIResponse(
            description = "Create central",
            responseCode = "200",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON,
                    schema = @Schema(type = SchemaType.OBJECT)
            )
    )
    public CentralDto saveCreateCentral(@Valid CentralDto centralDto) {
        var central = mapper.toModel(centralDto);
        return mapper.toDto(centralPort.create(central));
    }

    @PUT
    @RolesAllowed({"admin", "loja"})
    @Path("/")
    @Operation(summary = "Update central")
    @APIResponse(
            description = "Update central",
            responseCode = "200",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON,
                    schema = @Schema(type = SchemaType.OBJECT)
            )
    )
    public CentralDto saveUpdateCentral(@Valid CentralDto centralDto) {
        var central = mapper.toModel(centralDto);
        return mapper.toDto(centralPort.update(central));
    }

    @DELETE
    @RolesAllowed({"admin", "loja"})
    @Path("/{centralId}")
    @Operation(summary = "Delete central by id")
    @APIResponse(
            description = "Delete central by id",
            responseCode = "204",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON,
                    schema = @Schema(type = SchemaType.OBJECT)
            )
    )
    public void deleteCentralById(@NotNull @Positive @PathParam("centralId") Integer centralId) {
        centralPort.delete(centralId);
    }

    @GET
    @RolesAllowed({"admin", "loja"})
    @Path("/jobs")
    @Operation(summary = "Get all scheduled Jobs")
    @APIResponse(
            description = "Get all scheduled Jobs",
            responseCode = "200",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON,
                    schema = @Schema(type = SchemaType.OBJECT)
            )
    )
    public List<String> getAllScheduledJobs(){
        return centralPort.getScheduledJobs();
    }

    @GET
    @RolesAllowed({"admin", "loja"})
    @Path("/")
    @Operation(summary = "Get all scheduled Jobs")
    @APIResponse(
            description = "Get all scheduled Jobs",
            responseCode = "200",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON,
                    schema = @Schema(type = SchemaType.OBJECT)
            )
    )
    public List<CentralDto> listAllCentral(){
        return centralPort.listAllCentral().stream().map(mapper::toDto).collect(Collectors.toList());
    }

}
