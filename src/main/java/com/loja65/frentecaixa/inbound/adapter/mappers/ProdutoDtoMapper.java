package com.loja65.frentecaixa.inbound.adapter.mappers;

import com.loja65.frentecaixa.domain.model.Produto;
import com.loja65.frentecaixa.inbound.adapter.dto.ProdutoDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "cdi")
public interface ProdutoDtoMapper {

    ProdutoDto toDto(Produto produto);

    Produto toModel(ProdutoDto produtoDto);
}
