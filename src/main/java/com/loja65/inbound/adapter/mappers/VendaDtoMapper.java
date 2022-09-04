package com.loja65.inbound.adapter.mappers;

import com.loja65.domain.model.Venda;
import com.loja65.inbound.adapter.dto.VendaDto;
import org.mapstruct.Mapper;

import javax.enterprise.context.ApplicationScoped;

@Mapper(componentModel = "cdi")
@ApplicationScoped
public interface VendaDtoMapper {

    Venda vendaDto2Venda(VendaDto venda);
    VendaDto venda2VendaDto(Venda venda);
}
