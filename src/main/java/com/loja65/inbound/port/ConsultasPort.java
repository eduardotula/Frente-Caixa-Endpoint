package com.loja65.inbound.port;

import com.loja65.domain.model.*;
import com.loja65.domain.model.filters.CaixaFilter;
import com.loja65.domain.model.filters.VendaFilter;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.List;

public interface ConsultasPort {

    TotalVendasPeriod getTotalVendasPeriodWithFilters(VendaFilter filter);

    Pagination<Venda> getVendasByFilter(VendaFilter filter, PageParam pageParam);

    Pagination<Caixa> getCaixasByFilter(CaixaFilter filter, PageParam pageParam);

    LocalDateTime getLastUpdatedByLojaId(Integer lojaId);

    List<String> getAllFuncionarios();
}
