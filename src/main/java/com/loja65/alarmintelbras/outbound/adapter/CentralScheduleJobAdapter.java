package com.loja65.alarmintelbras.outbound.adapter;

import com.loja65.alarmintelbras.domain.model.CentralScheduleJob;
import com.loja65.alarmintelbras.outbound.adapter.entity.CentralScheduleJobEntity;
import com.loja65.alarmintelbras.outbound.adapter.mapper.CentralScheduleJobEntityMapper;
import com.loja65.alarmintelbras.outbound.adapter.repositories.CentralScheduleJobRepository;
import com.loja65.alarmintelbras.outbound.port.CentralScheduleJobPort;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class CentralScheduleJobAdapter implements CentralScheduleJobPort {

    @Inject
    CentralScheduleJobEntityMapper mapper;
    @Inject
    CentralScheduleJobRepository repository;

    @Override
    public CentralScheduleJob save(CentralScheduleJob centralScheduleJob) {
        CentralScheduleJobEntity entity = mapper.toEntity(centralScheduleJob);
        try{
            entity = repository.save(entity);
        }catch (Exception e){
            throw new IllegalStateException("Falha ao gravar centralSchedule" + centralScheduleJob.getDescription() +": " + e.getMessage());
        }
        return mapper.toModel(entity);
    }

    @Override
    public List<CentralScheduleJob> listAll(){
        return repository.findAll().stream().map(mapper::toModel).collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {
        repository.deleteById(id);
    }
}
