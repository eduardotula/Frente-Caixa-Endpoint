package com.loja65.inbound.adapter;

import com.loja65.domain.usecase.ConsultarVendasUseCase;
import com.loja65.inbound.adapter.dto.LojaDto;
import com.loja65.inbound.adapter.dto.VendaDto;
import com.loja65.inbound.adapter.mappers.LojaDtoMapper;
import com.loja65.inbound.adapter.mappers.VendaDtoMapper;
import com.loja65.inbound.adapter.mappers.consulta.CaixaConsultaMapper;
import com.loja65.inbound.adapter.mappers.consulta.VendaConsultaMapper;
import com.loja65.inbound.adapter.dto.consulta.CaixaConsultaDto;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import javax.annotation.security.PermitAll;
import javax.inject.Inject;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Path("/consulta")
public class ConsultaRestAdapter {


    @Inject
    CaixaConsultaMapper caixaConsultaMapper;
    @Inject
    VendaDtoMapper vendaDtoMapper;
    @Inject
    ConsultarVendasUseCase consultarVendasUseCase;


    @GET
    @PermitAll
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

    @GET
    @PermitAll
    @Path("/venda/")
    @Operation(summary = "Get all vendas with filters ")
    @APIResponse(
            description = "Get all vendas with filters ",
            responseCode = "200",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON,
                    schema = @Schema(type = SchemaType.OBJECT)
            )
    )
    public List<VendaDto> getVendasByFilters(@QueryParam("page") @DefaultValue("0") Integer page,
                                             @QueryParam("pageSize")@DefaultValue("100") Integer pageSize,
                                             @QueryParam("dataInicial")LocalDateTime dataInicial,
                                             @QueryParam("dataFinal") LocalDateTime dataFinal,
                                             @QueryParam("lojaId") Integer lojaId){
        Pageable pageable = PageRequest.of(page,pageSize);
        return consultarVendasUseCase.getAllVendasByFilter(dataInicial,dataFinal,lojaId, pageable).stream().map(vendaDtoMapper::venda2VendaDto).collect(Collectors.toList());
    }

}
