package com.loja65.outbound.adapters.mysql;

import com.loja65.domain.model.OperacaoCaixa;
import com.loja65.outbound.adapters.mappers.OperacaoCaixaMapper;
import com.loja65.outbound.adapters.repositories.OperacaoCaixaRepository;
import com.loja65.outbound.port.OperacaoCaixaDataPort;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;

@ApplicationScoped
public class OperacaoCaixaAdapter implements OperacaoCaixaDataPort {

    @Inject
    OperacaoCaixaMapper mapper;
    @Inject
    OperacaoCaixaRepository repository;
    @Override
    public OperacaoCaixa insert(OperacaoCaixa operacaoCaixa) {
        return mapper.operacaoCaixaEntity2OperacaoCaixa(repository.save(mapper.operacaoCaixa2OperacaoCaixaEntity(operacaoCaixa)));
    }

    @Override
    public List<OperacaoCaixa> insertAll(List<OperacaoCaixa> operacaoCaixa) {
        return mapper.operacaoCaixaEntity2OperacaoCaixa(repository.saveAll(mapper.operacaoCaixa2OperacaoCaixaEntity(operacaoCaixa)));
    }
}
