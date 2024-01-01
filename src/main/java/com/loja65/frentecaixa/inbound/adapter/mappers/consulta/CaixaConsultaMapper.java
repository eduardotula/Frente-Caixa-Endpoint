package com.loja65.frentecaixa.inbound.adapter.mappers.consulta;

import com.loja65.frentecaixa.domain.model.Caixa;
import com.loja65.frentecaixa.inbound.adapter.dto.consulta.CaixaConsultaDto;
import com.loja65.frentecaixa.inbound.adapter.mappers.OperacaoCaixaDtoMapper;
import com.loja65.frentecaixa.inbound.adapter.mappers.ProdutoDtoMapper;
import com.loja65.frentecaixa.inbound.adapter.mappers.VendaDtoMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "cdi", uses = {OperacaoCaixaDtoMapper.class, VendaDtoMapper.class, ProdutoDtoMapper.class})
public interface CaixaConsultaMapper {

    CaixaConsultaDto caixa2ConsultaDto(Caixa caixa);

    Caixa caixaConsultaDto2Caixa(CaixaConsultaDto caixaConsultaDto);

}
