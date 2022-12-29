package com.loja65.inbound.adapter;

import com.loja65.domain.model.Loja;
import com.loja65.domain.model.Produto;
import com.loja65.inbound.adapter.dto.ConsultaPrecoProdutoDto;
import com.loja65.inbound.adapter.dto.ProdutoDto;
import com.loja65.inbound.adapter.mappers.ConsultaPrecoProdutoDtoMapper;
import com.loja65.inbound.adapter.mappers.ProdutoDtoMapper;
import com.loja65.inbound.port.FrenteCaixaPort;
import com.loja65.inbound.port.ProdutoDtoPort;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.stream.Collectors;

@Path("/produto")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ProdutoRestAdapter {

    @Inject
    ProdutoDtoMapper produtoDtoMapper;

    @Inject
    ProdutoDtoPort produtoPort;
    @Inject
    FrenteCaixaPort frenteCaixaPort;

    @Inject
    ConsultaPrecoProdutoDtoMapper consultaPrecoProdutoDtoMapper;

    @PUT
    @RolesAllowed({"admin", "loja"})
    @Path("/{id}")
    @Operation(summary = "Update produto")
    @APIResponse(
            description = "Update produto",
            responseCode = "200",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON,
                    schema = @Schema(type = SchemaType.OBJECT)
            )
    )
    public ProdutoDto updateProduto(@PathParam("id")Integer id, @Valid ProdutoDto produtoDto){
        produtoDto.setProdutoId(id);
        Produto produto = produtoDtoMapper.toModel(produtoDto);
        return produtoDtoMapper.toDto(produtoPort.updateProduto(produto));
    }

    @GET
    @PermitAll
    @Path("/codBarra")
    @Operation(summary = "Get produto By codBarra")
    @APIResponse(
            description = "Get produto By codBarra",
            responseCode = "200",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON,
                    schema = @Schema(type = SchemaType.OBJECT)
            )
    )
    public ProdutoDto getProdutoByCodBarra(@QueryParam("codBarra") String codBarra){
        return produtoDtoMapper.toDto(produtoPort.findByCodbarra(codBarra));
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
    public Response addUpdatedProdutosPrecos(@Valid ConsultaPrecoProdutoDto consulta, @QueryParam("lojaId") Integer lojaId){
        var produto = consultaPrecoProdutoDtoMapper.toModel(consulta);
        produto.setLoja(new Loja());
        produto.getLoja().setLojaId(lojaId);
        frenteCaixaPort.addUpdatedProdutosPrecosByDiferentLoja(produto);
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
    public List<ConsultaPrecoProdutoDto> getUpdatedsProdutosPrecosByLoja(@QueryParam("lojaId") Integer lojaId){
        return frenteCaixaPort.getUpdatedsProdutosPrecosByLoja(lojaId).stream().map(consultaPrecoProdutoDtoMapper::toDto).collect(Collectors.toList());
    }
}
