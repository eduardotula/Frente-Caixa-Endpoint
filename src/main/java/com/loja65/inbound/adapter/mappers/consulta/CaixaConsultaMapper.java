package com.loja65.inbound.adapter.mappers.consulta;

import com.loja65.domain.model.Caixa;
import com.loja65.inbound.adapter.dto.consulta.CaixaConsultaDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "cdi")
public interface CaixaConsultaMapper {

    CaixaConsultaDto caixa2ConsultaDto(Caixa caixa);
    Caixa caixaConsultaDto2Caixa(CaixaConsultaDto caixaConsultaDto);
    List<CaixaConsultaDto> caixa2ConsultaDto(List<Caixa> caixa);
    List<Caixa> caixaConsultaDto2Caixa(List<CaixaConsultaDto> caixaConsultaDto);

}
