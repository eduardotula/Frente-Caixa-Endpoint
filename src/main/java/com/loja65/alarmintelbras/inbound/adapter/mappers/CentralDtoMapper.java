package com.loja65.alarmintelbras.inbound.adapter.mappers;

import com.loja65.alarmintelbras.domain.model.Central;
import com.loja65.alarmintelbras.inbound.adapter.dto.CentralDto;
import org.mapstruct.Mapper;

import javax.enterprise.context.ApplicationScoped;

@Mapper(componentModel = "cdi")
@ApplicationScoped
public interface CentralDtoMapper {

    CentralDto toDto(Central central);

    Central toModel(CentralDto centralDto);
}
