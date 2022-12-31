package com.loja65.inbound.adapter.mappers;

import com.loja65.domain.model.Produto;
import com.loja65.inbound.adapter.dto.ProdutoDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "cdi")
public interface ProdutoDtoMapper {

    ProdutoDto toDto(Produto produto);

    Produto toModel(ProdutoDto produtoDto);
}
