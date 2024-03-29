package com.loja65.frentecaixa.outbound.adapters.mysql;

import com.loja65.frentecaixa.domain.model.Loja;
import com.loja65.frentecaixa.outbound.adapters.mappers.LojaEntityMapper;
import com.loja65.frentecaixa.outbound.adapters.repositories.LojaRepository;
import com.loja65.frentecaixa.outbound.port.LojaPort;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class LojaAdapter implements LojaPort {

    @Inject
    LojaEntityMapper mapper;
    @Inject
    LojaRepository repository;
    @Override
    public Loja findLojaById(Integer id) {
        var loja = repository.findLojaBylojaId(id);
        return mapper.lojaEntity2Loja(loja);
    }

    @Override
    public Loja insert(Loja loja) {
        return mapper.lojaEntity2Loja(repository.save(mapper.loja2LojaEntity(loja)));
    }

    @Override
    public Loja update(Loja loja) {
        return mapper.lojaEntity2Loja(repository.save(mapper.loja2LojaEntity(loja)));
    }

    @Override
    public List<Loja> listAll(){
        return repository.findAll().stream().map(mapper::lojaEntity2Loja).collect(Collectors.toList());
    }
}
