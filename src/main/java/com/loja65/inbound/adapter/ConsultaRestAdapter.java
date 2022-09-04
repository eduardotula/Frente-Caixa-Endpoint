package com.loja65.inbound.adapter;

import com.loja65.domain.usecase.ConsultarVendasUseCase;
import com.loja65.inbound.adapter.mappers.consulta.CaixaConsultaMapper;
import com.loja65.inbound.adapter.mappers.consulta.VendaConsultaMapper;
import com.loja65.inbound.adapter.dto.consulta.CaixaConsultaDto;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/")
public class ConsultaRestAdapter {

    @Inject
    VendaConsultaMapper vendaConsultaMapper;
    @Inject
    CaixaConsultaMapper caixaConsultaMapper;
    @Inject
    ConsultarVendasUseCase consultarVendasUseCase;


    @GET
    @Path("/venda/today/")
    @Operation(summary = "Get all vendas from today by the lojaId")
    @APIResponse(
            description = "Get all vendas from today by the lojaId",
            responseCode = "200",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON,
                    schema = @Schema(type = SchemaType.OBJECT)
            )
    )
    public List<CaixaConsultaDto> getAllCaixaTodayFromLoja(@QueryParam("lojaId") Integer lojaId){
        return caixaConsultaMapper.caixa2ConsultaDto(consultarVendasUseCase.getAllCaixaTodayFromLoja(lojaId));
    }
}
