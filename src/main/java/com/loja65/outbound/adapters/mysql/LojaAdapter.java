package com.loja65.outbound.adapters.mysql;

import com.loja65.domain.model.Loja;
import com.loja65.outbound.adapters.mappers.LojaEntityMapper;
import com.loja65.outbound.adapters.repositories.LojaRepository;
import com.loja65.outbound.port.LojaPort;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class LojaAdapter implements LojaPort {

    @Inject
    LojaEntityMapper mapper;
    @Inject
    LojaRepository repository;
    @Override
    public Loja findLojaById(Integer id) {
        return mapper.lojaEntity2Loja(repository.findLojaBylojaId(id));
    }

    @Override
    public Loja insert(Loja loja) {
        return mapper.lojaEntity2Loja(repository.save(mapper.loja2LojaEntity(loja)));
    }
}
