package com.loja65.domain.usecase;

import com.loja65.domain.model.Produto;
import com.loja65.inbound.port.ProdutoDtoPort;
import com.loja65.outbound.port.ProdutoPort;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.Objects;

@ApplicationScoped
public class ProdutoUseCase implements ProdutoDtoPort {

    @Inject
    ProdutoPort produtoPort;

    @Override
    public Produto updateProduto(Produto produto) {
        return produtoPort.insert(produto);
    }

    @Override
    public Produto findByCodbarra(String codBarra) {
        Produto produto = produtoPort.findByCodBarra(codBarra);
        if(Objects.isNull(produto)) throw new IllegalStateException(String.format("Produto não encontrado com codBarra: %s", codBarra));
        return produto;
    }
}
