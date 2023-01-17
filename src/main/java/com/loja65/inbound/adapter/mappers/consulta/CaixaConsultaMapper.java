package com.loja65.inbound.adapter.mappers.consulta;

import com.loja65.domain.model.Caixa;
import com.loja65.inbound.adapter.dto.consulta.CaixaConsultaDto;
import com.loja65.inbound.adapter.mappers.OperacaoCaixaDtoMapper;
import com.loja65.inbound.adapter.mappers.ProdutoDtoMapper;
import com.loja65.inbound.adapter.mappers.VendaDtoMapper;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "cdi", uses = {OperacaoCaixaDtoMapper.class, VendaDtoMapper.class, ProdutoDtoMapper.class})
public interface CaixaConsultaMapper {

    CaixaConsultaDto caixa2ConsultaDto(Caixa caixa);

    Caixa caixaConsultaDto2Caixa(CaixaConsultaDto caixaConsultaDto);

}
