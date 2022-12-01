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

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
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
    public void deleteVendaByLocalId(@PathParam("lojaId") Integer lojaId, Integer vendaLocalId){
        frenteCaixaPort.apagarVenda(lojaId,vendaLocalId);
    }



    @POST
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

}
