package com.loja65.outbound.port;

import com.loja65.domain.model.ConsultaPrecoProduto;

import java.util.List;

public interface ConsultaPrecoProdutoPort {
    ConsultaPrecoProduto insert(ConsultaPrecoProduto consultaPrecoProduto);

    void deleteAllByLoja(Integer lojaId);

    List<ConsultaPrecoProduto> findByLojaLojaId(Integer lojaId);

    List<ConsultaPrecoProduto> findByDiferentLojaLojaId(Integer lojaId);
}
