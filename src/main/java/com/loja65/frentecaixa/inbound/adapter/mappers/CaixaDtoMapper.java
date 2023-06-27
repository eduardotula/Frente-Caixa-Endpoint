package com.loja65.frentecaixa.inbound.adapter.mappers;

import com.loja65.frentecaixa.domain.model.Caixa;
import com.loja65.frentecaixa.inbound.adapter.dto.CaixaDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "cdi")
public interface CaixaDtoMapper {

    @Mappings({
            @Mapping(target = "operacaoesCaixa", source = "operacaoCaixaDto")
    })
    Caixa caixaDto2Caixa(CaixaDto caixaDto);
    @Mappings({
            @Mapping(target = "operacaoCaixaDto", source = "operacaoesCaixa")
    })
    CaixaDto caixa2CaixaDto(Caixa caixa);
}
