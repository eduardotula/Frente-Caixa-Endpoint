package com.loja65.domain.usecase;

import com.loja65.domain.model.*;
import com.loja65.domain.utils.DefaultTimeZone;
import com.loja65.inbound.port.FrenteCaixaPort;
import com.loja65.outbound.port.*;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

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
    @Inject
    DefaultTimeZone defaultTimeZone;



    @Override
    @Transactional
    public Venda inserirVendaUltimoCaixaAberto(Integer lojaId, Venda venda) throws IllegalArgumentException, IllegalStateException{
        Loja loja = lojaPort.findLojaById(lojaId);
        if(loja == null) throw new IllegalArgumentException("Nenhuma loja localizada por id");

        Caixa lastCaixa = caixaPort.findLastCaixaByLojaId(lojaId);
        if(lastCaixa == null || lastCaixa.getStatus().equals(Caixa.CaixaStatus.FECHADO)) throw new IllegalArgumentException("Nenhum caixa aberto encontrado");

        venda.setCaixaId(lastCaixa.getCaixaId());
        Produto produto = venda.getProduto();
        Produto prodEntity = produtoPort.findByCodBarraAndLoja(produto.getCodBarra(), lojaId);
        if(prodEntity == null) {
            produto.create(venda.getCreatedAt(), loja.getLojaId());
            produto.setValor(venda.getValorTotal()/venda.getQuantidade());
            prodEntity = produtoPort.insert(produto);
        }

        prodEntity.setDataUltVenda(venda.getCreatedAt());
        prodEntity.setValorUltVenda(venda.getValorTotal()/venda.getQuantidade());
        prodEntity.setDescricao(venda.getProduto().getDescricao());
        produtoPort.insert(prodEntity);
        venda.setProduto(prodEntity);
        final var fVenda = vendaPort.insert(venda);

        loja.setLastUpdated(defaultTimeZone.getSp());
        lojaPort.update(loja);
        return fVenda;
    }

    @Override
    public void apagarVenda(Integer lojaId, Integer localId) throws IllegalArgumentException, IllegalStateException{
        List<Venda> vendas = vendaPort.findByLojaIdAndlocalVendaId(lojaId,localId);
        vendas.forEach(venda -> vendaPort.deleteVenda(venda));
    }

    @Override
    @Transactional
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
    @Transactional
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
    @Transactional
    public OperacaoCaixa addOperacaoLastCaixa(OperacaoCaixa operacaoCaixa, Integer lojaId) {
        Loja loja = lojaPort.findLojaById(lojaId);
        if(loja == null) throw new IllegalArgumentException("Nenhuma loja localizada por id");

        Caixa lastCaixa = caixaPort.findLastCaixaByLojaId(lojaId);
        if(lastCaixa == null) throw new IllegalArgumentException("Nenhum caixa aberto encontrado");
        operacaoCaixa.setCaixaId(lastCaixa.getCaixaId());

        return operacaoCaixaPort.insert(operacaoCaixa);
    }

    @Override
    @Transactional
    public Loja cadastrarLoja(Loja loja) throws IllegalArgumentException, IllegalStateException{
        return lojaPort.insert(loja);
    }

    @Override
    public List<Loja> getAllLojas(){
        var lojas = lojaPort.listAll();
        if(Objects.isNull(lojas) || lojas.isEmpty()) throw new IllegalArgumentException("Nenhuma loja cadastrada");
        return lojas;
    }

    @Override
    @Transactional
    public void updateLojaLastUpdatedWithCurrentTime(Integer lojaId){
        Loja loja = lojaPort.findLojaById(lojaId);
        if(Objects.isNull(loja)) throw new IllegalArgumentException("Loja não encontrada com id: " + lojaId);

        loja.setLastUpdated(defaultTimeZone.getSp());
        lojaPort.update(loja);
    }



}
