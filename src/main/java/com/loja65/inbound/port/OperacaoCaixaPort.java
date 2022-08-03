package com.loja65.inbound.port;

import com.loja65.domain.model.OperacaoCaixa;

public interface OperacaoCaixaPort {


    OperacaoCaixa addOperacaoLastCaixa(OperacaoCaixa operacaoCaixa, Integer lojaId);
}
