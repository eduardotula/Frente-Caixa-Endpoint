package com.loja65.frentecaixa.inbound.adapter.mappers;

import com.loja65.frentecaixa.domain.model.Loja;
import com.loja65.frentecaixa.inbound.adapter.dto.LojaDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "cdi")
public interface LojaDtoMapper {

    LojaDto loja2LojaDto(Loja loja);
    Loja lojaDto2Loja(LojaDto lojaDto);

}
