package com.loja65.frentecaixa.outbound.adapters.mappers;

import com.loja65.frentecaixa.domain.model.ConsultaPrecoProduto;
import com.loja65.frentecaixa.outbound.adapters.entity.ConsultaPrecoProdutoEntity;
import org.mapstruct.Mapper;

import javax.enterprise.context.ApplicationScoped;

@Mapper(componentModel = "cdi")
@ApplicationScoped
public interface ConsultaPrecoProdutoEntityMapper {

    ConsultaPrecoProduto toModel(ConsultaPrecoProdutoEntity consultaPrecoProduto);

    ConsultaPrecoProdutoEntity toEntity(ConsultaPrecoProduto consultaPrecoProduto);
}
