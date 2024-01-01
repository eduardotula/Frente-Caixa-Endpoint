package com.loja65.frentecaixa.inbound.port;

import com.loja65.frentecaixa.domain.model.*;
import com.loja65.frentecaixa.domain.model.filters.CaixaFilter;
import com.loja65.frentecaixa.domain.model.filters.VendaFilter;

import java.time.LocalDateTime;
import java.util.List;

public interface ConsultasPort {

    TotalVendasPeriod getTotalVendasPeriodWithFilters(VendaFilter filter);

    Pagination<Venda> getVendasByFilter(VendaFilter filter, PageParam pageParam);

    Pagination<Caixa> getCaixasByFilter(CaixaFilter filter, PageParam pageParam);

    LocalDateTime getLastUpdatedByLojaId(Integer lojaId);

    List<String> getAllFuncionarios();
}
