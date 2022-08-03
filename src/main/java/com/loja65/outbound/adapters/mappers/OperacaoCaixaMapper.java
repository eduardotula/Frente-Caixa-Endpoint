package com.loja65.outbound.adapters.mappers;

import com.loja65.domain.model.OperacaoCaixa;
import com.loja65.outbound.adapters.entity.OperacaoCaixaEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@Mapper(componentModel = "cdi")
@ApplicationScoped
public interface OperacaoCaixaMapper {

    @Mapping(source = "caixaId", target = "caixa.caixaId")
    OperacaoCaixaEntity operacaoCaixa2OperacaoCaixaEntity(OperacaoCaixa operacaoCaixa);
    @Mapping(source = "caixa.caixaId", target = "caixaId")
    OperacaoCaixa operacaoCaixaEntity2OperacaoCaixa(OperacaoCaixaEntity operacaoCaixaEntity);

    @Mapping(source = "caixaId", target = "caixa.caixaId")
    List<OperacaoCaixaEntity> operacaoCaixa2OperacaoCaixaEntity(List<OperacaoCaixa> operacaoCaixa);
    @Mapping(source = "caixa.caixaId", target = "caixaId")
    List<OperacaoCaixa> operacaoCaixaEntity2OperacaoCaixa(List<OperacaoCaixaEntity> operacaoCaixaEntity);
}
