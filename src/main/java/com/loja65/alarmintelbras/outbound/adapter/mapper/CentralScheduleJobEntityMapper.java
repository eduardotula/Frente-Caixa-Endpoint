package com.loja65.alarmintelbras.outbound.adapter.mapper;

import com.loja65.alarmintelbras.domain.model.CentralScheduleJob;
import com.loja65.alarmintelbras.outbound.adapter.entity.CentralScheduleJobEntity;
import org.mapstruct.Mapper;

import javax.enterprise.context.ApplicationScoped;

@Mapper(componentModel = "cdi")
@ApplicationScoped
public interface CentralScheduleJobEntityMapper {

    CentralScheduleJobEntity toEntity(CentralScheduleJob centralScheduleJob);

    CentralScheduleJob toModel(CentralScheduleJobEntity centralScheduleJob);
}
