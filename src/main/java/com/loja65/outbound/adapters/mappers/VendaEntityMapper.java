package com.loja65.outbound.adapters.mappers;

import com.loja65.domain.model.Venda;
import com.loja65.outbound.adapters.entity.VendaEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import javax.enterprise.context.ApplicationScoped;

@Mapper(componentModel = "cdi")
@ApplicationScoped
public interface VendaEntityMapper {

    @Mapping(source = "caixaId", target = "caixa.caixaId")
    VendaEntity venda2VendaEntity(Venda venda);
    @Mapping(source = "caixa.caixaId", target = "caixaId")
    Venda vendaEntity2Venda(VendaEntity vendaEntity);
}
