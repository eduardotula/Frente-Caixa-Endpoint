package com.loja65.outbound.adapters.mysql;

import com.loja65.domain.model.Venda;
import com.loja65.outbound.adapters.entity.VendaEntity;
import com.loja65.outbound.adapters.mappers.VendaEntityMapper;
import com.loja65.outbound.adapters.repositories.VendaRepository;
import com.loja65.outbound.port.VendaPort;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class VendaAdapter implements VendaPort {

    @Inject
    VendaRepository repository;
    @Inject
    VendaEntityMapper mapper;

    @Override
    public Venda insert(Venda venda) {
        VendaEntity v = mapper.venda2VendaEntity(venda);
        try{
            v = repository.save(v);
        }catch (Exception e){
            throw new IllegalStateException("Falha ao gravar venda" + venda.getLocalVendaId() +": " + e.getMessage());
        }
        return mapper.vendaEntity2Venda(v);
    }
}
