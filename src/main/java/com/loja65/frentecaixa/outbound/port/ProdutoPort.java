package com.loja65.frentecaixa.outbound.port;

import com.loja65.frentecaixa.domain.model.PageParam;
import com.loja65.frentecaixa.domain.model.Pagination;
import com.loja65.frentecaixa.domain.model.Produto;
import com.loja65.frentecaixa.domain.model.filters.ProdutoFilter;

import java.util.List;

public interface ProdutoPort {

    Produto insert(Produto produto);

    Produto findById(Integer produtoId);

    List<Produto> findByCodBarra(String codBarra);
    List<Produto> findByCodBarraAndLoja(String codBarra, Integer lojaId);

    Pagination<Produto> findByFilters(PageParam params, ProdutoFilter produtoFilter);
}
