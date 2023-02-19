package com.loja65.outbound.port;

import com.loja65.domain.model.Caixa;
import com.loja65.domain.model.PageParam;
import com.loja65.domain.model.Pagination;
import com.loja65.domain.model.filters.CaixaFilter;

import java.util.List;

public interface CaixaPort {

    Caixa insert(Caixa caixa);
    Caixa findCaixaByLojaIdAndLocalCaixaId(Integer lojaId, Integer localCaixaId);
    Caixa findByCaixaId(Integer caixaId);
    List<Caixa> findAllTodayByLojaId(Integer lojaId);

    Pagination<Caixa> findByFilters(CaixaFilter filter, PageParam param);

    List<String> listAllFuncionarios();
}
