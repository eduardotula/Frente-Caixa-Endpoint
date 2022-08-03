package com.loja65.inbound.adapter.mappers.consulta;

import com.loja65.domain.model.Venda;
import com.loja65.inbound.dto.consulta.VendaConsultaDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "cdi")
public interface VendaConsultaMapper {

    Venda vendaConsultaDto2Venda(VendaConsultaDto vendaConsultaDto);
    VendaConsultaDto venda2VendaConsultaDto(Venda venda);
}
