package com.loja65.inbound.port;

import com.loja65.domain.model.Caixa;
import com.loja65.domain.model.Loja;
import com.loja65.domain.model.OperacaoCaixa;
import com.loja65.domain.model.Venda;

public interface FrenteCaixaPort {


    Venda inserirVendaUltimoCaixaAberto(Integer lojaId, Venda venda) ;
    void apagarVenda();
    Caixa abrirCaixa(Caixa caixa);
    Caixa fecharCaixa(Caixa caixa);
    OperacaoCaixa addOperacaoLastCaixa(OperacaoCaixa operacaoCaixa, Integer lojaId);
    Loja cadastrarLoja(Loja loja);
}
