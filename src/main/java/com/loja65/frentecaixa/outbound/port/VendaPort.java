package com.loja65.frentecaixa.outbound.port;

import com.loja65.frentecaixa.domain.model.PageParam;
import com.loja65.frentecaixa.domain.model.Pagination;
import com.loja65.frentecaixa.domain.model.TotalVendasPeriod;
import com.loja65.frentecaixa.domain.model.Venda;
import com.loja65.frentecaixa.domain.model.filters.VendaFilter;

import java.util.List;

public interface VendaPort {

    Venda insert(Venda venda);

    void deleteVenda(Venda venda);

    Pagination<Venda> findByFilters(VendaFilter filters, PageParam pageParam);

    List<Venda> findByLojaIdAndlocalVendaId(Integer lojaId, Integer localId);

    TotalVendasPeriod findTotalVendasPeriodWithFilters(VendaFilter filter);
}
