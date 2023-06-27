package com.loja65.frentecaixa.inbound.adapter.mappers;

import com.loja65.frentecaixa.domain.model.OperacaoCaixa;
import com.loja65.frentecaixa.inbound.adapter.dto.OperacaoCaixaDto;
import org.mapstruct.Mapper;

import javax.enterprise.context.ApplicationScoped;

@Mapper(componentModel = "cdi")
@ApplicationScoped
public interface OperacaoCaixaDtoMapper {

    OperacaoCaixa operacaoCaixaDto2OperacaoCaixa(OperacaoCaixaDto operacaoCaixaDto);
    OperacaoCaixaDto operacaoCaixa2OperacaoCaixaDto(OperacaoCaixa operacaoCaixa);
}
