package com.loja65.frentecaixa.outbound.adapters.mysql;

import com.loja65.frentecaixa.domain.model.ConsultaPrecoProduto;
import com.loja65.frentecaixa.domain.utils.DefaultTimeZone;
import com.loja65.frentecaixa.outbound.adapters.entity.ConsultaPrecoProdutoEntity;
import com.loja65.frentecaixa.outbound.adapters.mappers.ConsultaPrecoProdutoEntityMapper;
import com.loja65.frentecaixa.outbound.adapters.repositories.ConsultaPrecoProdutoRepository;
import com.loja65.frentecaixa.outbound.adapters.repositories.LojaRepository;
import com.loja65.frentecaixa.outbound.port.ConsultaPrecoProdutoPort;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@ApplicationScoped
public class ConsultaPrecoProdutoAdapter implements ConsultaPrecoProdutoPort {

    @Inject
    DefaultTimeZone defaultTimeZone;
    @Inject
    ConsultaPrecoProdutoEntityMapper consultaPrecoProdutoEntityMapper;
    @Inject
    ConsultaPrecoProdutoRepository repository;
    @Inject
    LojaRepository lojaRepository;

    @Override
    public ConsultaPrecoProduto insert(ConsultaPrecoProduto consultaPrecoProduto){
        if(Objects.isNull(consultaPrecoProduto.getCreatedAt())) consultaPrecoProduto.setCreatedAt(defaultTimeZone.getSp());

        var entity = repository.save(consultaPrecoProdutoEntityMapper.toEntity(consultaPrecoProduto));
        return consultaPrecoProdutoEntityMapper.toModel(entity);
    }

    @Override
    public void deleteAllByLoja(Integer lojaId){
        List<ConsultaPrecoProdutoEntity>  apgr = repository.findByLojaLojaId(lojaId);
        apgr.forEach(ap -> repository.deleteById(ap.getId()));
    }

    @Override
    public List<ConsultaPrecoProduto> findByLojaLojaId(Integer lojaId){
        return repository.findByLojaLojaId(lojaId).stream().map(consultaPrecoProdutoEntityMapper::toModel).collect(Collectors.toList());
    }

    @Override
    public List<ConsultaPrecoProduto> findByDiferentLojaLojaId(Integer lojaId){
        return repository.findDistinctByLojaLojaId(lojaId).stream().map(consultaPrecoProdutoEntityMapper::toModel).collect(Collectors.toList());
    }
}
