package com.loja65.frentecaixa.inbound.port;

import com.loja65.frentecaixa.domain.model.OperacaoCaixa;

public interface OperacaoCaixaPort {


    OperacaoCaixa addOperacaoLastCaixa(OperacaoCaixa operacaoCaixa, Integer lojaId);
}
