package com.loja65.outbound.adapters.mappers;

import com.loja65.domain.model.Produto;
import com.loja65.outbound.adapters.entity.ProdutoEntity;
import org.mapstruct.Mapper;

import javax.enterprise.context.ApplicationScoped;

@Mapper(componentModel = "cdi")
@ApplicationScoped
public interface ProdutoEntityMapper {

    Produto produtoEntity2Produto(ProdutoEntity produtoEntity);
    ProdutoEntity produto2ProdutoEntity(Produto produto);
}
