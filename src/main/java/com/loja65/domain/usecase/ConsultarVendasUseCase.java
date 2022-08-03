package com.loja65.domain.usecase;

import com.loja65.domain.model.Caixa;
import com.loja65.domain.model.Venda;
import com.loja65.inbound.port.ConsultasPort;
import com.loja65.outbound.port.CaixaPort;
import com.loja65.outbound.port.VendaPort;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.util.List;

@RequestScoped
public class ConsultarVendasUseCase implements ConsultasPort {

    @Inject
    CaixaPort caixaPort;

    @Override
    public List<Caixa> getAllCaixaTodayFromLoja(Integer lojaId) throws IllegalArgumentException, IllegalStateException{
        List<Caixa> caixas = caixaPort.findAllTodayByLojaId(lojaId);
        if(caixas == null || caixas.isEmpty()) throw new IllegalStateException("nenhum caixa encontrado para data inserida");
        return caixas;
    }
}
