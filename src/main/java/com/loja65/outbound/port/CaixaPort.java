package com.loja65.outbound.port;

import com.loja65.domain.model.Caixa;

import java.util.List;

public interface CaixaPort {

    Caixa insert(Caixa caixa);
    Caixa findLastCaixaByLojaId(Integer lojaId);
    Caixa findByCaixaId(Integer caixaId);
    List<Caixa> findAllTodayByLojaId(Integer lojaId);
}
