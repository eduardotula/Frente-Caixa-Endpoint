package com.loja65.frentecaixa.inbound.adapter.mappers;

import com.loja65.frentecaixa.domain.model.Venda;
import com.loja65.frentecaixa.inbound.adapter.dto.VendaDto;
import org.mapstruct.Mapper;

import javax.enterprise.context.ApplicationScoped;

@Mapper(componentModel = "cdi")
@ApplicationScoped
public interface VendaDtoMapper {

    Venda toModel(VendaDto venda);
    VendaDto toDto(Venda venda);
}
