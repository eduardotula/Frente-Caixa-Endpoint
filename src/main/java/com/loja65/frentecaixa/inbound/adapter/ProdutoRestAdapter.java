package com.loja65.frentecaixa.inbound.adapter;

import com.loja65.frentecaixa.domain.model.PageParam;
import com.loja65.frentecaixa.domain.model.Pagination;
import com.loja65.frentecaixa.domain.model.Produto;
import com.loja65.frentecaixa.domain.model.filters.ProdutoFilter;
import com.loja65.frentecaixa.domain.utils.Json;
import com.loja65.frentecaixa.inbound.adapter.dto.ConsultaPrecoProdutoDto;
import com.loja65.frentecaixa.inbound.adapter.dto.ProdutoDto;
import com.loja65.frentecaixa.inbound.adapter.mappers.ConsultaPrecoProdutoDtoMapper;
import com.loja65.frentecaixa.inbound.adapter.mappers.ProdutoDtoMapper;
import com.loja65.frentecaixa.inbound.port.ProdutoDtoPort;
import com.loja65.frentecaixa.inbound.adapter.responses.PaginationResponse;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Path("/produto")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ProdutoRestAdapter {

    @Inject
    ProdutoDtoMapper produtoDtoMapper;
    @Inject
    Json mapper;
    @Inject
    ProdutoDtoPort produtoPort;

    @Inject
    ConsultaPrecoProdutoDtoMapper consultaPrecoProdutoDtoMapper;

    @POST
    @RolesAllowed({"admin", "loja"})
    @Path("/")
    @Operation(summary = "Createupdate Produto")
    public ProdutoDto createUpdateProduto(@QueryParam("lojaId") Integer lojaId, @Valid ProdutoDto produtoDto){
        produtoDto.setLojaId(lojaId);
        Produto produto = produtoDtoMapper.toModel(produtoDto);
        return produtoDtoMapper.toDto(produtoPort.createUpdateProduto(produto));
    }

    @GET
    @RolesAllowed({"loja", "admin"})
    @Path("/")
    @Operation(summary = "Get produto By codBarra")
    @APIResponse(
            description = "Get produto By codBarra",
            responseCode = "200",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON,
                    schema = @Schema(type = SchemaType.OBJECT)
            )
    )
    public PaginationResponse<ProdutoDto> getProduto(@QueryParam("page") @DefaultValue("0") Integer page,
                                         @QueryParam("pageSize") @DefaultValue("50") Integer pageSize,
                                         @QueryParam("sortField") @DefaultValue("produtoId") String sortField,
                                         @QueryParam("sortType") @DefaultValue("ASC") String sortType,
                                         @QueryParam("codBarra") String codBarra,
                                         @QueryParam("descricao") String descricao,
                                         @QueryParam("lojaId") Integer lojaId,
                                         @QueryParam("produtoId") Integer produtoId) {
        PageParam pageParam = new PageParam(page, pageSize, sortField, sortType);
        ProdutoFilter produtoFilter = new ProdutoFilter(codBarra, descricao, lojaId, produtoId);
        Pagination<Produto> pagination = produtoPort.findbyFilters(pageParam, produtoFilter);
        Pagination<ProdutoDto> paginationDto = pagination.to(produtoDtoMapper::toDto);
        var respo = new PaginationResponse<>(paginationDto, produtoFilter);
        respo.getMetaInfo().setSearch(mapper.toMap(produtoFilter));
        return respo;
    }

    @POST
    @RolesAllowed({"admin", "loja"})
    @Path("/addUpdatedProdutoPrecos")
    @Operation(summary = "Update lastUpdatedLoja with current time")
    @APIResponse(
            description = "Update lastUpdatedLoja with current time",
            responseCode = "200",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON,
                    schema = @Schema(type = SchemaType.OBJECT)
            )
    )
    public Response addUpdatedProdutosPrecos(@Valid ConsultaPrecoProdutoDto consulta, @QueryParam("lojaId") Integer[] lojaId) {
        var produto = consultaPrecoProdutoDtoMapper.toModel(consulta);
        produtoPort.addUpdatedProdutosPrecosByDiferentLoja(produto, Arrays.asList(lojaId));
        return Response.ok().build();
    }

    @PUT
    @RolesAllowed({"admin", "loja"})
    @Path("/getUpdatedsProdutosPrecosByLoja")
    @Operation(summary = "Update lastUpdatedLoja with current time")
    @APIResponse(
            description = "Update lastUpdatedLoja with current time",
            responseCode = "200",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON,
                    schema = @Schema(type = SchemaType.OBJECT)
            )
    )
    public List<ConsultaPrecoProdutoDto> getUpdatedsProdutosPrecosByLoja(@QueryParam("lojaId") Integer lojaId) {
        return produtoPort.getUpdatedsProdutosPrecosByLoja(lojaId).stream().map(consultaPrecoProdutoDtoMapper::toDto).collect(Collectors.toList());
    }

}
