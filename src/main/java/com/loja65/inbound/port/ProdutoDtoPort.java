package com.loja65.inbound.port;

import com.loja65.domain.model.Produto;

public interface ProdutoDtoPort {

    Produto updateProduto(Produto produto);
    Produto findByCodbarra(String codBarra);
}
