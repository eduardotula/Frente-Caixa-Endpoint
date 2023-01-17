package com.loja65.inbound.adapter;

import com.loja65.domain.model.Caixa;
import com.loja65.domain.model.PageParam;
import com.loja65.domain.model.Pagination;
import com.loja65.domain.model.Venda;
import com.loja65.domain.model.filters.CaixaFilter;
import com.loja65.domain.model.filters.VendaFilter;
import com.loja65.domain.utils.Json;
import com.loja65.inbound.adapter.dto.VendaDto;
import com.loja65.inbound.adapter.dto.consulta.FuncionariosResponseDto;
import com.loja65.inbound.adapter.dto.consulta.TotalVendasPeriodResponseDto;
import com.loja65.inbound.adapter.mappers.VendaDtoMapper;
import com.loja65.inbound.adapter.mappers.consulta.CaixaConsultaMapper;
import com.loja65.inbound.adapter.dto.consulta.CaixaConsultaDto;
import com.loja65.inbound.adapter.mappers.consulta.TotalVendasPeriodDtoMapper;
import com.loja65.inbound.adapter.responses.PaginationResponse;
import com.loja65.inbound.port.ConsultasPort;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;

import javax.annotation.security.PermitAll;
import javax.inject.Inject;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.time.LocalDateTime;
import java.util.List;

@Path("/consulta")
public class ConsultaRestAdapter {


    @Inject
    CaixaConsultaMapper caixaConsultaMapper;
    @Inject
    TotalVendasPeriodDtoMapper totalVendasPeriodDtoMapper;
    @Inject
    VendaDtoMapper vendaDtoMapper;
    @Inject
    Json mapper;
    @Inject
    ConsultasPort consultasPort;


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
    public PaginationResponse<VendaDto> getVendasByFilters(@QueryParam("page") @DefaultValue("0") Integer page,
                                                           @QueryParam("pageSize") @DefaultValue("100") Integer pageSize,
                                                           @QueryParam("sortField") @DefaultValue("vendaId") String sortField,
                                                           @QueryParam("sortType") @DefaultValue("ASC") String sortType,
                                                           @QueryParam("funcionario") String funcionario,
                                                           @QueryParam("dataInicial") LocalDateTime dataInicial,
                                                           @QueryParam("dataFinal") LocalDateTime dataFinal,
                                                           @QueryParam("lojaId") Integer lojaId) {
        PageParam pageParam = new PageParam(page, pageSize, sortField, sortType);
        VendaFilter vendaFilter = new VendaFilter(dataInicial, dataFinal, funcionario, lojaId);
        Pagination<Venda> pagination = consultasPort.getVendasByFilter(vendaFilter, pageParam);
        Pagination<VendaDto> paginationDto = pagination.to(vendaDtoMapper::toDto);
        var respo = new PaginationResponse<>(paginationDto, vendaFilter);
        respo.getMetaInfo().setSearch(mapper.toMap(vendaFilter));
        return respo;
    }

    @GET
    @PermitAll
    @Path("/caixa/")
    @Operation(summary = "Get all caixas with filters ")
    @APIResponse(
            description = "Get all caixas with filters ",
            responseCode = "200",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON,
                    schema = @Schema(type = SchemaType.OBJECT)
            )
    )
    public PaginationResponse<CaixaConsultaDto> getCaixaByFilters(@QueryParam("page") @DefaultValue("0") Integer page,
                                                                  @QueryParam("pageSize") @DefaultValue("30") Integer pageSize,
                                                                  @QueryParam("sortField") @DefaultValue("caixaId") String sortField,
                                                                  @QueryParam("sortType") @DefaultValue("ASC") String sortType,
                                                                  @QueryParam("funcionario") String funcionario,
                                                                  @QueryParam("dataInicial") LocalDateTime dataInicial,
                                                                  @QueryParam("dataFinal") LocalDateTime dataFinal,
                                                                  @QueryParam("lojaId") Integer lojaId) {
        PageParam pageParam = new PageParam(page, pageSize, sortField, sortType);
        CaixaFilter caixaFilter = new CaixaFilter(dataInicial, dataFinal, funcionario, lojaId);
        Pagination<Caixa> pagination = consultasPort.getCaixasByFilter(caixaFilter, pageParam);
        Pagination<CaixaConsultaDto> paginationDto = pagination.to(caixaConsultaMapper::caixa2ConsultaDto);
        var respo = new PaginationResponse<>(paginationDto, caixaFilter);
        respo.getMetaInfo().setSearch(mapper.toMap(caixaFilter));
        return respo;
    }

    @GET
    @PermitAll
    @Path("/venda/total")
    @Operation(summary = "Get total vendas with filters ")
    @APIResponse(
            description = "Get total vendas with filters ",
            responseCode = "200",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON,
                    schema = @Schema(type = SchemaType.OBJECT)
            )
    )
    public TotalVendasPeriodResponseDto getTotalVendasPeriodWithFilters(@QueryParam("funcionario") String funcionario,
                                                                        @QueryParam("dataInicial")@NotNull(message = "DataInicial não pode ser nulo") LocalDateTime dataInicial,
                                                                        @QueryParam("dataFinal")@NotNull(message = "DataFinal não pode ser nulo") LocalDateTime dataFinal,
                                                                        @QueryParam("lojaId") Integer lojaId) {
        var consulta = consultasPort.getTotalVendasPeriodWithFilters(new VendaFilter(dataInicial,dataFinal,funcionario,lojaId));
        return totalVendasPeriodDtoMapper.toDto(consulta);
    }

    @GET
    @PermitAll
    @Path("/loja/lastUpdated/{lojaId}")
    @Operation(summary = "Get lastUpdated by lojaId ")
    @APIResponse(
            description = "Get lastUpdated by lojaId ",
            responseCode = "200",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON,
                    schema = @Schema(type = SchemaType.OBJECT)
            )
    )
    public LocalDateTime getLastUpdatedByLojaId(@PathParam("lojaId") Integer lojaId) {
        return consultasPort.getLastUpdatedByLojaId(lojaId);
    }

    @GET
    @PermitAll
    @Path("/caixa/funcionario/")
    @Operation(summary = "Get lastUpdated by lojaId ")
    @APIResponse(
            description = "Get lastUpdated by lojaId ",
            responseCode = "200",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON,
                    schema = @Schema(type = SchemaType.OBJECT)
            )
    )
    public FuncionariosResponseDto getAllFuncionarios(){
        return new FuncionariosResponseDto(consultasPort.getAllFuncionarios());
    }

}
