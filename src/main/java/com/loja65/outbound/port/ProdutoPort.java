package com.loja65.outbound.port;

import com.loja65.domain.model.PageParam;
import com.loja65.domain.model.Pagination;
import com.loja65.domain.model.Produto;
import com.loja65.domain.model.filters.ProdutoFilter;

public interface ProdutoPort {

    Produto insert(Produto produto);

    Produto findByCodBarra(String codBarra);
    Produto findByCodBarraAndLoja(String codBarra, Integer lojaId);

    Pagination<Produto> findByFilters(PageParam params, ProdutoFilter produtoFilter);
}
