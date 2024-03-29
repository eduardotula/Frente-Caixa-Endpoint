package com.loja65.frentecaixa.outbound.adapters.mappers;

import com.loja65.frentecaixa.domain.model.Caixa;
import com.loja65.frentecaixa.outbound.adapters.entity.CaixaEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import javax.enterprise.context.ApplicationScoped;

@Mapper(componentModel = "cdi", uses = {OperacaoCaixaMapper.class, VendaEntityMapper.class, ProdutoEntityMapper.class})
@ApplicationScoped
public interface CaixaEntityMapper {

    @Mapping(source = "loja.lojaId", target = "lojaId")
    Caixa caixaEntity2Caixa(CaixaEntity caixaEntity);

    @Mapping(source = "lojaId", target = "loja.lojaId")
    CaixaEntity caixa2CaixaEntity(Caixa caixa);

}
