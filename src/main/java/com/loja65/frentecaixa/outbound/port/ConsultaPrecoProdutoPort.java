package com.loja65.frentecaixa.outbound.port;

import com.loja65.frentecaixa.domain.model.ConsultaPrecoProduto;

import java.util.List;

public interface ConsultaPrecoProdutoPort {
    ConsultaPrecoProduto insert(ConsultaPrecoProduto consultaPrecoProduto);

    void deleteAllByLoja(Integer lojaId);

    List<ConsultaPrecoProduto> findByLojaLojaId(Integer lojaId);

    List<ConsultaPrecoProduto> findByDiferentLojaLojaId(Integer lojaId);
}
