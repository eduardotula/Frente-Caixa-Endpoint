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
        if (Objects.isNull(existProd))
            throw new IllegalArgumentException(String.format("Produto com id:%d não encontrado", produto.getProdutoId()));
        var sameCodebarProdcut = produtoPort.findByCodBarraAndLoja(produto.getCodBarra(),existProd.getLojaId());
        if(Objects.nonNull(sameCodebarProdcut))
            throw new IllegalArgumentException("Produto com já cadastrado nesta loja");

        produto.setLojaId(existProd.getLojaId());

        if (produto.getCreatedAt() == null) produto.setCreatedAt(existProd.getCreatedAt());
        if (produto.getDataUltVenda() == null) produto.setDataUltVenda(existProd.getDataUltVenda());
        return produtoPort.insert(produto);
    }

    @Override
    public Produto createProduto(Produto produto) {
        List<Produto> existProds = produtoPort.findByCodBarraAndLoja(produto.getCodBarra(), produto.getLojaId());
        if (Objects.nonNull(existProds) || existProds.size() > 0) throw new IllegalArgumentException("Produto já cadastrado nesta loja");

        Loja loja = lojaPort.findLojaById(produto.getLojaId());
        if (Objects.isNull(loja))
            throw new IllegalArgumentException(String.format("Loja com id: %d não existente", produto.getLojaId()));

        produto.create(defaultTimeZone.getSp(), loja.getLojaId());
        return produtoPort.insert(produto);
    }

    @Override
    public List<Produto> findByCodbarra(String codBarra) {
        List<Produto> produtos = produtoPort.findByCodBarra(codBarra);
        if (Objects.isNull(produtos) || produtos.size() == 0)
            throw new IllegalStateException(String.format("Produto não encontrado com codBarra: %s", codBarra));
        return produtos;
    }

    @Override
    public Pagination<Produto> findbyFilters(PageParam params, ProdutoFilter produtoFilter) {
        return produtoPort.findByFilters(params, produtoFilter);
    }

    @Override
    @Transactional
    public List<ConsultaPrecoProduto> getUpdatedsProdutosPrecosByLoja(Integer lojaId) {
        Loja loja = lojaPort.findLojaById(lojaId);
        if (Objects.isNull(loja)) throw new IllegalArgumentException("Loja não encontrada com id: " + lojaId);

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
            if (!lojaIds.contains(l.getLojaId())) insertLojas.add(l);
        });

        if (insertLojas.isEmpty()) throw new IllegalArgumentException("Não existem outras lojas cadastradas");

        insertLojas.forEach(lojaConsultaInsert -> {

            Produto produto = consultaPrecoProduto.getProduto();
            List<Produto> existProdutos = produtoPort.findByCodBarraAndLoja(produto.getCodBarra(), lojaConsultaInsert.getLojaId());
            Produto existProduto;

            if (Objects.isNull(existProdutos) || existProdutos.isEmpty()) {
                produto.create(defaultTimeZone.getSp(), lojaConsultaInsert.getLojaId());
                produto.setValor(consultaPrecoProduto.getNewValue());
                consultaPrecoProduto.setProduto(produtoPort.insert(produto));

            } else {
                existProduto = existProdutos.get(0);
                existProduto.setDescricao(consultaPrecoProduto.getProduto().getDescricao());
                existProduto.setValor(consultaPrecoProduto.getNewValue());
                produtoPort.insert(existProduto);
                consultaPrecoProduto.setProduto(existProduto);
            }

            consultaPrecoProduto.setLoja(lojaConsultaInsert);
            consultaPrecoProdutoPort.insert(consultaPrecoProduto);
        });

    }
}
