package com.loja65.inbound.adapter;

import com.loja65.domain.model.Caixa;
import com.loja65.domain.model.Loja;
import com.loja65.domain.model.OperacaoCaixa;
import com.loja65.domain.model.Venda;
import com.loja65.inbound.adapter.dto.*;
import com.loja65.inbound.adapter.mappers.CaixaDtoMapper;
import com.loja65.inbound.adapter.mappers.LojaDtoMapper;
import com.loja65.inbound.adapter.mappers.OperacaoCaixaDtoMapper;
import com.loja65.inbound.adapter.mappers.VendaDtoMapper;
import com.loja65.inbound.adapter.mappers.consulta.VendaConsultaMapper;
import com.loja65.inbound.port.FrenteCaixaPort;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.stream.Collectors;

@Path("/")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class FrenteCaixaRestAdapter {


    @Inject
    LojaDtoMapper lojaDtoMapper;
    @Inject
    FrenteCaixaPort frenteCaixaPort;
    @Inject
    VendaDtoMapper vendaDtoMapper;
    @Inject
    CaixaDtoMapper caixaDtoMapper;
    @Inject
    OperacaoCaixaDtoMapper operacaoCaixaDtoMapper;


    @POST
    @RolesAllowed({"admin", "loja"})
    @Path("/venda/ultimoCaixa/{lojaId}")
    @Operation(summary = "Insert Venda into the last opened Caixa")
    @APIResponse(
            description = "Insert Venda into the last opened Caixa",
            responseCode = "200",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON,
                    schema = @Schema(type = SchemaType.OBJECT)
            )
    )
    public VendaDto inserirVendaUltimoCaixaAberto(@PathParam("lojaId") Integer lojaId, @Valid VendaDto vendaDto){
        Venda venda = vendaDtoMapper.vendaDto2Venda(vendaDto);
        return vendaDtoMapper.venda2VendaDto(frenteCaixaPort.inserirVendaUltimoCaixaAberto(lojaId, venda));
    }

    @POST
    @RolesAllowed({"admin", "loja"})
    @Path("/caixa/abrir")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Abrir Caixa")
    @APIResponse(
            description = "Abrir Caixa",
            responseCode = "200",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON,
                    schema = @Schema(type = SchemaType.OBJECT)
            )
    )
    @ResponseStatus(HttpStatus.OK)
    public CaixaDto abrirCaixa(@Valid CaixaDto caixaDto)throws IllegalArgumentException, IllegalStateException{
        Caixa caixa = caixaDtoMapper.caixaDto2Caixa(caixaDto);
        return caixaDtoMapper.caixa2CaixaDto(frenteCaixaPort.abrirCaixa(caixa));
    }

    @POST
    @RolesAllowed({"admin", "loja"})
    @Path("/caixa/fechar")
    @Operation(summary = "Fechar Caixa")
    @APIResponse(
            description = "Fechar Caixa",
            responseCode = "200",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON,
                    schema = @Schema(type = SchemaType.OBJECT)
            )
    )
    public CaixaDto fecharCaixa(@Valid CaixaDto caixaDto){
        Caixa caixa = caixaDtoMapper.caixaDto2Caixa(caixaDto);
        return caixaDtoMapper.caixa2CaixaDto(frenteCaixaPort.fecharCaixa(caixa));
    }

    @POST
    @RolesAllowed({"admin", "loja"})
    @Path("/caixa/ultimoCaixa/operacaoCaixa/{lojaId}")
    @Operation(summary = "Insert OperacaoCaixa into the last opened Caixa")
    @APIResponse(
            description = "Insert OperacaoCaixa into the last opened Caixa",
            responseCode = "200",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON,
                    schema = @Schema(type = SchemaType.OBJECT)
            )
    )
    public OperacaoCaixaDto addOperacaoLastCaixa(@PathParam("lojaId") Integer lojaId, @Valid OperacaoCaixaDto operacaoCaixaDto){
        OperacaoCaixa operacaoCaixa = operacaoCaixaDtoMapper.operacaoCaixaDto2OperacaoCaixa(operacaoCaixaDto);
        return operacaoCaixaDtoMapper.operacaoCaixa2OperacaoCaixaDto(frenteCaixaPort.addOperacaoLastCaixa(operacaoCaixa, lojaId));
    }

    @DELETE
    @RolesAllowed({"admin", "loja"})
    @Path("/venda/ultimoCaixa/{lojaId}")
    @Operation(summary = "Delete a venda with the localId")
    @APIResponse(
            description = "Delete a venda with the localId",
            responseCode = "200",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON,
                    schema = @Schema(type = SchemaType.OBJECT)
            )
    )
    public Response deleteVendaByLocalId(@PathParam("lojaId") Integer lojaId, @QueryParam("localId") Integer vendaLocalId){
        frenteCaixaPort.apagarVenda(lojaId,vendaLocalId);
        return Response.ok().build();
    }



    @POST
    @RolesAllowed({"admin"})
    @Path("/loja")
    @Operation(summary = "Cadastra Loja")
    @APIResponse(
            description = "Cadastra Loja",
            responseCode = "200",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON,
                    schema = @Schema(type = SchemaType.OBJECT)
            )
    )
    public LojaDto cadastrarLoja(@Valid LojaDto lojaDto){
        Loja loja = lojaDtoMapper.lojaDto2Loja(lojaDto);
        return lojaDtoMapper.loja2LojaDto(frenteCaixaPort.cadastrarLoja(loja));
    }

    @GET
    @PermitAll
    @Path("/loja")
    @Operation(summary = "Get all lojas")
    @APIResponse(
            description = "Get all lojas",
            responseCode = "200",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON,
                    schema = @Schema(type = SchemaType.OBJECT)
            )
    )
    public List<LojaDto> getAllLojas(){
        return frenteCaixaPort.getAllLojas().stream().map(lojaDtoMapper::loja2LojaDto).collect(Collectors.toList());
    }

    @PUT
    @RolesAllowed({"admin", "loja"})
    @Path("/loja/lastUpdated/{lojaId}/now")
    @Operation(summary = "Update lastUpdatedLoja with current time")
    @APIResponse(
            description = "Update lastUpdatedLoja with current time",
            responseCode = "200",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON,
                    schema = @Schema(type = SchemaType.OBJECT)
            )
    )
    public Response updateLojaLastUpdatedWithCurrentTime(@PathParam("lojaId") Integer lojaId){
        frenteCaixaPort.updateLojaLastUpdatedWithCurrentTime(lojaId);
        return Response.ok().build();
    }

}
