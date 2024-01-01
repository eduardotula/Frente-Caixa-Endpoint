package com.loja65.frentecaixa.inbound.adapter.mappers.consulta;

import com.loja65.frentecaixa.domain.model.TotalVendasPeriod;
import com.loja65.frentecaixa.inbound.adapter.dto.consulta.TotalVendasPeriodResponseDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "cdi")
public interface TotalVendasPeriodDtoMapper {

    TotalVendasPeriod toModel(TotalVendasPeriodResponseDto vendasPeriodResponseDto);

    TotalVendasPeriodResponseDto toDto(TotalVendasPeriod vendasPeriod);
}
