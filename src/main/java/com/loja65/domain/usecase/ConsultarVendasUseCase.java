package com.loja65.domain.usecase;

import com.loja65.domain.model.*;
import com.loja65.domain.model.filters.CaixaFilter;
import com.loja65.domain.model.filters.VendaFilter;
import com.loja65.inbound.port.ConsultasPort;
import com.loja65.outbound.port.CaixaPort;
import com.loja65.outbound.port.LojaPort;
import com.loja65.outbound.port.VendaPort;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@RequestScoped
public class ConsultarVendasUseCase implements ConsultasPort {

    @Inject
    CaixaPort caixaPort;
    @Inject
    VendaPort vendaPort;

    @Inject
    LojaPort lojaPort;

    @Override
    public TotalVendasPeriod getTotalVendasPeriodWithFilters(VendaFilter filter) {
        TotalVendasPeriod totalCart = vendaPort.findTotalVendasPeriodWithFilters(filter);
        return totalCart;
    }

    @Override
    public Pagination<Venda> getVendasByFilter(VendaFilter filter, PageParam pageParam) {
        Pagination<Venda> vendas = vendaPort.findByFilters(filter, pageParam);
        if (vendas.getResults().isEmpty()) throw new IllegalArgumentException("Nenhuma venda encontrada com filtros");
        return vendas;
    }

    @Override
    public Pagination<Caixa> getCaixasByFilter(CaixaFilter filter, PageParam pageParam) {
        Pagination<Caixa> caixa = caixaPort.findByFilters(filter, pageParam);
        if (caixa.getResults().isEmpty()) throw new IllegalArgumentException("Nenhuma Caixa encontrada com filtros");
        return caixa;
    }

    @Override
    public LocalDateTime getLastUpdatedByLojaId(Integer lojaId) {
        var loja = lojaPort.findLojaById(lojaId);
        if (Objects.isNull(loja)) throw new IllegalArgumentException("Loja não encontrada com id: " + lojaId);

        return loja.getLastUpdated();
    }

    @Override
    public List<String> getAllFuncionarios(){
        return caixaPort.listAllFuncionarios();
    }

}
