package com.loja65.outbound.port;

import com.loja65.domain.model.Produto;

public interface ProdutoPort {

    Produto insert(Produto produto);

    Produto findByCodBarra(String codBarra);
    Produto findByCodBarraAndLoja(String codBarra, Integer lojaId);
}
