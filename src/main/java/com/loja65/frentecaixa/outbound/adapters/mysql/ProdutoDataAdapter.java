package com.loja65.frentecaixa.outbound.adapters.mysql;

import com.loja65.frentecaixa.domain.model.PageParam;
import com.loja65.frentecaixa.domain.model.Pagination;
import com.loja65.frentecaixa.domain.model.Produto;
import com.loja65.frentecaixa.domain.model.filters.ProdutoFilter;
import com.loja65.frentecaixa.domain.utils.DefaultTimeZone;
import com.loja65.frentecaixa.outbound.adapters.entity.ProdutoEntity;
import com.loja65.frentecaixa.outbound.adapters.mappers.ProdutoEntityMapper;
import com.loja65.frentecaixa.outbound.adapters.repositories.ProdutoRepository;
import com.loja65.frentecaixa.outbound.port.ProdutoPort;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class ProdutoDataAdapter implements ProdutoPort {


    @Inject
    ProdutoEntityMapper mapper;
    @Inject
    ProdutoRepository repository;
    @Inject
    DefaultTimeZone defaultTimeZone;

    @Override
    public Produto insert(Produto produto) {
        ProdutoEntity prodEntity = mapper.produto2ProdutoEntity(produto);

        try{
            if(prodEntity.getCreatedAt() == null) prodEntity.setCreatedAt(defaultTimeZone.getSp());
            prodEntity = repository.save(prodEntity);
        }catch (Exception e){
            e.printStackTrace();
            throw new IllegalStateException("Falha ao inserir Produto :" + produto.getCodBarra() + ": " + e.getMessage());
        }
        return mapper.produtoEntity2Produto(prodEntity);
    }

    @Override
    public Produto findById(Integer produtoId){
        return mapper.produtoEntity2Produto(repository.findById(produtoId).orElse(null));
    }

    @Override
    public List<Produto> findByCodBarra(String codBarra) {
       return repository.findByCodBarra(codBarra).stream().map(mapper::produtoEntity2Produto).collect(Collectors.toList());
    }

    @Override
    public List<Produto> findByCodBarraAndLoja(String codBarra, Integer lojaId) {
        return repository.findByCodBarraAndLojaId(codBarra, lojaId).stream().map(mapper::produtoEntity2Produto).collect(Collectors.toList());
    }

    @Override
    public Pagination<Produto> findByFilters(PageParam params, ProdutoFilter produtoFilter) {

        Pageable pageable = PageRequest.of(params.getPage(), params.getPageSize());

        var page = repository.findByFilters(produtoFilter.getCodBarra(),
                produtoFilter.getDescricao(), produtoFilter.getLojaId(), produtoFilter.getProdutoId(), pageable);

        return new Pagination<Produto>(params.getPage(), params.getPageSize(),page.getTotalPages(), (int)page.getTotalElements(), params.getSortField(),
                params.getSortType(), page.stream().map(mapper::produtoEntity2Produto).collect(Collectors.toList()));
    }


}
