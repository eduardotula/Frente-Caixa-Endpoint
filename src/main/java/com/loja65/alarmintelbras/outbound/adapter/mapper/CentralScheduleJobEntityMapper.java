package com.loja65.alarmintelbras.outbound.adapter.mapper;

import com.loja65.alarmintelbras.domain.model.CentralScheduleJob;
import com.loja65.alarmintelbras.outbound.adapter.entity.CentralScheduleJobEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import javax.enterprise.context.ApplicationScoped;

@Mapper(componentModel = "cdi")
@ApplicationScoped
public interface CentralScheduleJobEntityMapper {

    @Mapping(source = "centralId", target = "central.centralId")
    CentralScheduleJobEntity toEntity(CentralScheduleJob centralScheduleJob);
    @Mapping(source = "central.centralId", target = "centralId")
    CentralScheduleJob toModel(CentralScheduleJobEntity centralScheduleJob);
}
