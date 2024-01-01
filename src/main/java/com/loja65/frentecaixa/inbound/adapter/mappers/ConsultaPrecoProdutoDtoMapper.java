package com.loja65.frentecaixa.inbound.adapter.mappers;

import com.loja65.frentecaixa.domain.model.ConsultaPrecoProduto;
import com.loja65.frentecaixa.inbound.adapter.dto.ConsultaPrecoProdutoDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import javax.enterprise.context.ApplicationScoped;

@Mapper(componentModel = "cdi")
@ApplicationScoped
public interface ConsultaPrecoProdutoDtoMapper {

    @Mapping(source = "produto.codBarra", target = "codBarra")
    @Mapping(source = "produto.descricao", target = "descricao")
    ConsultaPrecoProdutoDto toDto(ConsultaPrecoProduto consultaPrecoProduto);

    @Mapping(source = "codBarra", target = "produto.codBarra")
    @Mapping(source = "descricao", target = "produto.descricao")
    ConsultaPrecoProduto toModel(ConsultaPrecoProdutoDto consultaPrecoProdutoDto);

}


