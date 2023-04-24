package com.loja65.inbound.port;

import com.loja65.domain.model.ConsultaPrecoProduto;
import com.loja65.domain.model.PageParam;
import com.loja65.domain.model.Pagination;
import com.loja65.domain.model.Produto;
import com.loja65.domain.model.filters.ProdutoFilter;

import javax.transaction.Transactional;
import java.util.List;

public interface ProdutoDtoPort {

    Produto updateProduto(Produto produto);

    Produto createProduto(Produto produto);

    List<Produto> findByCodbarra(String codBarra);

    Pagination<Produto> findbyFilters(PageParam params, ProdutoFilter produtoFilter);

    @Transactional
    List<ConsultaPrecoProduto> getUpdatedsProdutosPrecosByLoja(Integer lojaId);

    @Transactional
    void addUpdatedProdutosPrecosByDiferentLoja(ConsultaPrecoProduto consultaPrecoProduto, List<Integer> lojaIds);
}
