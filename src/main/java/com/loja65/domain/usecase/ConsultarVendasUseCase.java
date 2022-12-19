package com.loja65.domain.usecase;

import com.loja65.domain.model.Caixa;
import com.loja65.domain.model.Venda;
import com.loja65.inbound.port.ConsultasPort;
import com.loja65.outbound.port.CaixaPort;
import com.loja65.outbound.port.LojaPort;
import com.loja65.outbound.port.VendaPort;
import org.springframework.data.domain.Pageable;

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
    public List<Caixa> getAllCaixaTodayFromLoja(Integer lojaId) throws IllegalArgumentException, IllegalStateException{
        List<Caixa> caixas = caixaPort.findAllTodayByLojaId(lojaId);
        if(caixas == null || caixas.isEmpty()) throw new IllegalStateException("nenhum caixa encontrado");
        return caixas;
    }


    @Override
    public List<Venda> getAllVendasByFilter(LocalDateTime dataInicial, LocalDateTime dataFinal, Integer lojaId, Pageable pageable){
        var vendas = vendaPort.getVendasByFilter(dataInicial,dataFinal,lojaId, pageable);
        if(Objects.isNull(vendas) || vendas.isEmpty()) throw new IllegalStateException("Nenhuma venda encontrada para filtros");
        return vendas;
    }

    @Override
    public LocalDateTime getLastUpdatedByLojaId(Integer lojaId){
        var loja = lojaPort.findLojaById(lojaId);
        if(Objects.isNull(loja)) throw new IllegalArgumentException("Loja não encontrada com id: " + lojaId);

        return loja.getLastUpdated();
    }


}
