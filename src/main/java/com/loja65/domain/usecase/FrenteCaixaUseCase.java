package com.loja65.domain.usecase;

import com.loja65.domain.model.*;
import com.loja65.inbound.port.FrenteCaixaPort;
import com.loja65.outbound.port.*;
import org.springframework.stereotype.Component;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.time.LocalDateTime;

@RequestScoped
public class FrenteCaixaUseCase implements FrenteCaixaPort {

    @Inject
    CaixaPort caixaPort;
    @Inject
    LojaPort lojaPort;
    @Inject
    OperacaoCaixaDataPort operacaoCaixaPort;
    @Inject
    ProdutoPort produtoPort;
    @Inject
    VendaPort vendaPort;



    @Override
    public Venda inserirVendaUltimoCaixaAberto(Integer lojaId, Venda venda) throws IllegalArgumentException, IllegalStateException{
        Loja loja = lojaPort.findLojaById(lojaId);
        if(loja == null) throw new IllegalArgumentException("Nenhuma loja localizada por id");

        Caixa lastCaixa = caixaPort.findLastCaixaByLojaId(lojaId);
        if(lastCaixa == null || lastCaixa.getStatus().equals(Caixa.CaixaStatus.FECHADO)) throw new IllegalArgumentException("Nenhum caixa aberto encontrado");

        venda.setCaixaId(lastCaixa.getCaixaId());

        Produto produto = venda.getProduto();
        Produto prodEntity = produtoPort.findByCodBarra(produto.getCodBarra());
        if(prodEntity == null) {
            prodEntity.setCreatedAt(produto.getCreatedAt());
            prodEntity = produtoPort.insert(produto);
        }

        prodEntity.setDataUltVenda(LocalDateTime.now());
        prodEntity.setValorUltVenda(venda.getValorTotal()/venda.getQuantidade());
        produtoPort.insert(prodEntity);
        venda.setProduto(prodEntity);

        return vendaPort.insert(venda);
    }

    @Override
    public void apagarVenda() throws IllegalArgumentException, IllegalStateException{

    }

    @Override
    public Caixa abrirCaixa(Caixa caixa) throws IllegalArgumentException, IllegalStateException {
        Caixa lastCaixa = caixaPort.findLastCaixaByLojaId(caixa.getLojaId());
        if(lastCaixa!=null && lastCaixa.getStatus().equals(Caixa.CaixaStatus.ABERTO) ) throw new IllegalArgumentException("Ultimo caixa já aberto");

        Loja loja = lojaPort.findLojaById(caixa.getLojaId());
        if(loja == null) throw new IllegalArgumentException("Nenhuma loja localizada por id");

        caixa.setLojaId(loja.getLojaId());
        caixa.setStatus(Caixa.CaixaStatus.ABERTO);
        caixa = caixaPort.insert(caixa);

        Integer caixaId = caixa.getCaixaId();
        caixa.getOperacaoesCaixa().forEach(operacaoCaixa -> operacaoCaixa.setCaixaId(caixaId));
        operacaoCaixaPort.insertAll(caixa.getOperacaoesCaixa());
        return caixa;
    }

    @Override
    public Caixa fecharCaixa(Caixa caixa) throws IllegalArgumentException, IllegalStateException{
        Caixa lastCaixa = caixaPort.findLastCaixaByLojaId(caixa.getLojaId());
        if(lastCaixa!=null && lastCaixa.getStatus().equals(Caixa.CaixaStatus.FECHADO)) throw new IllegalArgumentException("Ultimo caixa já fechado");

        Loja loja = lojaPort.findLojaById(caixa.getLojaId());
        if(loja == null) throw new IllegalArgumentException("Nenhuma loja localizada por id");

        lastCaixa.setStatus(Caixa.CaixaStatus.FECHADO);

        caixa.getOperacaoesCaixa().forEach(operacaoCaixa -> operacaoCaixa.setCaixaId(lastCaixa.getCaixaId()));
        operacaoCaixaPort.insertAll(caixa.getOperacaoesCaixa()).forEach(operacaoCaixa -> lastCaixa.addOperacoesCaixa(operacaoCaixa));

        return caixaPort.insert(lastCaixa);
    }

    @Override
    public OperacaoCaixa addOperacaoLastCaixa(OperacaoCaixa operacaoCaixa, Integer lojaId) {
        Loja loja = lojaPort.findLojaById(lojaId);
        if(loja == null) throw new IllegalArgumentException("Nenhuma loja localizada por id");

        Caixa lastCaixa = caixaPort.findLastCaixaByLojaId(lojaId);
        if(lastCaixa == null) throw new IllegalArgumentException("Nenhum caixa aberto encontrado");
        operacaoCaixa.setCaixaId(lastCaixa.getCaixaId());

        return operacaoCaixaPort.insert(operacaoCaixa);
    }

    @Override
    public Loja cadastrarLoja(Loja loja) throws IllegalArgumentException, IllegalStateException{
        return lojaPort.insert(loja);
    }


}
