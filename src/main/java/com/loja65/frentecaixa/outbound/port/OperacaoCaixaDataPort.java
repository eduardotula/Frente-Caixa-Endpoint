package com.loja65.frentecaixa.outbound.port;

import com.loja65.frentecaixa.domain.model.OperacaoCaixa;

import java.util.List;

public interface OperacaoCaixaDataPort {
    OperacaoCaixa insert(OperacaoCaixa operacaoCaixa);
    List<OperacaoCaixa> insertAll(List<OperacaoCaixa> operacaoCaixa);

}
