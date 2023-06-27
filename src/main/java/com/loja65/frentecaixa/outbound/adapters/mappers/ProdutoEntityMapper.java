package com.loja65.frentecaixa.outbound.adapters.mappers;

import com.loja65.frentecaixa.domain.model.Produto;
import com.loja65.frentecaixa.outbound.adapters.entity.ProdutoEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import javax.enterprise.context.ApplicationScoped;

@Mapper(componentModel = "cdi")
@ApplicationScoped
public interface ProdutoEntityMapper {

    @Mapping(source = "loja.lojaId", target = "lojaId")
    Produto produtoEntity2Produto(ProdutoEntity produtoEntity);

    @Mapping(source = "lojaId", target = "loja.lojaId")
    ProdutoEntity produto2ProdutoEntity(Produto produto);
}
