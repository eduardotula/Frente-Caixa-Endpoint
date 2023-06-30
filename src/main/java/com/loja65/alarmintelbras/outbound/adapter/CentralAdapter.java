package com.loja65.alarmintelbras.outbound.adapter;

import com.loja65.alarmintelbras.domain.model.Central;
import com.loja65.alarmintelbras.outbound.adapter.entity.CentralEntity;
import com.loja65.alarmintelbras.outbound.adapter.mapper.CentralEntityMapper;
import com.loja65.alarmintelbras.outbound.adapter.repositories.CentralRepository;
import com.loja65.alarmintelbras.outbound.port.CentralPort;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class CentralAdapter implements CentralPort {

    @Inject
    CentralRepository repository;
    @Inject
    CentralEntityMapper mapper;

    @Override
    public Central save(Central central) {
        CentralEntity centralEntity = mapper.toEntity(central);
        try{
            centralEntity = repository.save(centralEntity);
        }catch (Exception e){
            throw new IllegalStateException("Falha ao gravar central" + central.getDescricao() +": " + e.getMessage());
        }
        return mapper.toModel(centralEntity);
    }

    @Override
    public List<Central> findByDescricao(String descricao){
        return repository.findByDescricao(descricao).stream().map(mapper::toModel).collect(Collectors.toList());
    }

    @Override
    public Central findByMac(String mac){
        return mapper.toModel(repository.findByCentralMac(mac));
    }

    @Override
    public List<Central> listAll(){
        return repository.findAll().stream().map(mapper::toModel).collect(Collectors.toList());
    }

    @Override
    public Central findById(int id){
        return mapper.toModel(repository.findById(id).orElseGet(() -> null));
    }

    @Override
    public void delete(Integer id) {
        repository.deleteById(id);
    }
}
