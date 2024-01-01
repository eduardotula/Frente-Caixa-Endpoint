package com.loja65.frentecaixa.inbound.port;

import com.loja65.frentecaixa.domain.model.ConsultaPrecoProduto;
import com.loja65.frentecaixa.domain.model.PageParam;
import com.loja65.frentecaixa.domain.model.Pagination;
import com.loja65.frentecaixa.domain.model.Produto;
import com.loja65.frentecaixa.domain.model.filters.ProdutoFilter;

import javax.transaction.Transactional;
import java.util.List;

public interface ProdutoDtoPort {

    Produto createUpdateProduto(Produto produto);

    List<Produto> findByCodbarra(String codBarra);

    Pagination<Produto> findbyFilters(PageParam params, ProdutoFilter produtoFilter);

    @Transactional
    List<ConsultaPrecoProduto> getUpdatedsProdutosPrecosByLoja(Integer lojaId);

    @Transactional
    void addUpdatedProdutosPrecosByDiferentLoja(ConsultaPrecoProduto consultaPrecoProduto, List<Integer> lojaIds);
}
