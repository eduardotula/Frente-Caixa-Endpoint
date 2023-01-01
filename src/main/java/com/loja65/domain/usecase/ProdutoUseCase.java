package com.loja65.domain.usecase;

import com.loja65.domain.model.*;
import com.loja65.domain.model.filters.ProdutoFilter;
import com.loja65.domain.utils.DefaultTimeZone;
import com.loja65.inbound.port.ProdutoDtoPort;
import com.loja65.outbound.port.ConsultaPrecoProdutoPort;
import com.loja65.outbound.port.LojaPort;
import com.loja65.outbound.port.ProdutoPort;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@ApplicationScoped
public class ProdutoUseCase implements ProdutoDtoPort {

    @Inject
    ProdutoPort produtoPort;
    @Inject
    LojaPort lojaPort;
    @Inject
    DefaultTimeZone defaultTimeZone;
    @Inject
    ConsultaPrecoProdutoPort consultaPrecoProdutoPort;

    @Override
    public Produto updateProduto(Produto produto) {
        var existProd = produtoPort.findById(produto.getProdutoId());
        if(Objects.isNull(existProd)) throw new IllegalArgumentException(String.format("Produto com id:%d não encontrado", produto.getProdutoId()));
        produto.setLojaId(existProd.getLojaId());
        return produtoPort.insert(produto);
    }

    @Override
    public Produto findByCodbarra(String codBarra) {
        Produto produto = produtoPort.findByCodBarra(codBarra);
        if(Objects.isNull(produto)) throw new IllegalStateException(String.format("Produto não encontrado com codBarra: %s", codBarra));
        return produto;
    }

    @Override
    public Pagination<Produto> findbyFilters(PageParam params, ProdutoFilter produtoFilter){
        return produtoPort.findByFilters(params, produtoFilter);
    }

    @Override
    @Transactional
    public List<ConsultaPrecoProduto> getUpdatedsProdutosPrecosByLoja(Integer lojaId){
        Loja loja = lojaPort.findLojaById(lojaId);
        if(Objects.isNull(loja)) throw new IllegalArgumentException("Loja não encontrada com id: " + lojaId);

        List<ConsultaPrecoProduto> produtosAtt = consultaPrecoProdutoPort.findByLojaLojaId(lojaId);

        consultaPrecoProdutoPort.deleteAllByLoja(lojaId);

        return produtosAtt;
    }

    @Override
    @Transactional
    public void addUpdatedProdutosPrecosByDiferentLoja(ConsultaPrecoProduto consultaPrecoProduto, List<Integer> lojaIds){
        List<Loja> insertLojas = new ArrayList<>();

        List<Loja> allLojas = lojaPort.listAll();

        allLojas.stream().forEach(l -> {
            if(!lojaIds.contains(l.getLojaId())) insertLojas.add(l);
        });

        if(insertLojas.isEmpty()) throw new IllegalArgumentException("Não existem outras lojas cadastradas");

        insertLojas.forEach(lojaConsultaInsert -> {
            Produto produto = consultaPrecoProduto.getProduto();
            Produto existProduto = produtoPort.findByCodBarraAndLoja(produto.getCodBarra(), lojaConsultaInsert.getLojaId());
            if(Objects.isNull(existProduto)) {
                produto.create(defaultTimeZone.getSp(), lojaConsultaInsert.getLojaId());
                produto.setValor(consultaPrecoProduto.getNewValue());
                consultaPrecoProduto.setProduto(produtoPort.insert(produto));
            }else{
                existProduto.setDescricao(consultaPrecoProduto.getProduto().getDescricao());
                produtoPort.insert(existProduto);
                consultaPrecoProduto.setProduto(existProduto);
            }
            consultaPrecoProduto.setLoja(lojaConsultaInsert);
            consultaPrecoProdutoPort.insert(consultaPrecoProduto);
        });

    }
}
