package com.loja65.alarmintelbras.outbound.adapter.mapper;

import com.loja65.alarmintelbras.domain.model.Central;
import com.loja65.alarmintelbras.outbound.adapter.entity.CentralEntity;
import org.mapstruct.Mapper;

import javax.enterprise.context.ApplicationScoped;

@Mapper(componentModel = "cdi")
@ApplicationScoped
public interface CentralEntityMapper {


    Central toModel(CentralEntity centralEntity);

    CentralEntity toEntity(Central central);
}
