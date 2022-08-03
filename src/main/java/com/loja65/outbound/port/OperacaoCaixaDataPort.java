package com.loja65.outbound.port;

import com.loja65.domain.model.OperacaoCaixa;

import java.util.List;

public interface OperacaoCaixaDataPort {
    OperacaoCaixa insert(OperacaoCaixa operacaoCaixa);
    List<OperacaoCaixa> insertAll(List<OperacaoCaixa> operacaoCaixa);

}
