package com.loja65.outbound.adapters.mysql;

import com.loja65.domain.model.Produto;
import com.loja65.domain.utils.DefaultTimeZone;
import com.loja65.outbound.adapters.entity.ProdutoEntity;
import com.loja65.outbound.adapters.mappers.ProdutoEntityMapper;
import com.loja65.outbound.adapters.repositories.ProdutoRepository;
import com.loja65.outbound.port.ProdutoPort;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.time.LocalDateTime;

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
    public Produto findByCodBarra(String codBarra) {
       return mapper.produtoEntity2Produto(repository.findByCodBarra(codBarra));
    }

    @Override
    public Produto findByCodBarraAndLoja(String codBarra, Integer lojaId) {
        return mapper.produtoEntity2Produto(repository.findByCodBarraAndLojaId(codBarra, lojaId));
    }


}
