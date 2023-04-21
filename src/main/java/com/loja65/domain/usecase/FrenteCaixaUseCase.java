package com.loja65.domain.usecase;

import com.loja65.domain.enums.TipoPagamentoEnum;
import com.loja65.domain.model.*;
import com.loja65.domain.utils.DefaultTimeZone;
import com.loja65.inbound.port.FrenteCaixaPort;
import com.loja65.outbound.port.*;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;

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
    public Venda saveVendaByLocalCaixaIdAndLoja(Integer lojaId, Integer localCaixaId, Venda venda) throws IllegalArgumentException, IllegalStateException{
        Loja loja = lojaPort.findLojaById(lojaId);
        if(loja == null) throw new IllegalArgumentException("Nenhuma loja localizada por id");

        Caixa lastCaixa = caixaPort.findCaixaByLojaIdAndLocalCaixaId(lojaId, localCaixaId);
        if(lastCaixa == null || lastCaixa.getStatus().equals(Caixa.CaixaStatus.FECHADO)) throw new IllegalArgumentException(String.format("Nenhum caixa aberto com localCaixaId: %d encontrado", localCaixaId));

        venda.setCaixaId(lastCaixa.getCaixaId());

        Produto produto = venda.getProduto();
        Produto prodEntity = produtoPort.findByCodBarraAndLoja(produto.getCodBarra(), lojaId);
        if(prodEntity == null) {
            produto.create(venda.getCreatedAt(), loja.getLojaId());
            prodEntity = produtoPort.insert(produto);
        }

        prodEntity.setDataUltVenda(venda.getCreatedAt());
        prodEntity.setValorUltVenda(venda.getValorTotal()/venda.getQuantidade());
        prodEntity.setDescricao(venda.getProduto().getDescricao());
        produto.setValor(venda.getValorTotal()/venda.getQuantidade());
        produtoPort.insert(prodEntity);

        venda.setProduto(prodEntity);
        venda.setValorFinal(venda.getValorTotal() - venda.getDesconto());

        final var fVenda = vendaPort.insert(venda);

        loja.setLastUpdated(defaultTimeZone.getSp());
        lojaPort.update(loja);
        return fVenda;
    }

    @Override
    @Transactional
    public Venda saveTrocaOperation(Integer lojaId, Integer localCaixaId, Venda venda){
        venda.setTipoPagamento(TipoPagamentoEnum.TROCA);
        venda.setValorCartao(venda.getValorCartao() < 0 ? venda.getValorCartao() : venda.getValorCartao() * -1);
        venda.setValorDinheiro(venda.getValorDinheiro() < 0 ? venda.getValorDinheiro() : venda.getValorDinheiro() * -1);
        venda.setValorTotal(venda.getValorTotal() < 0 ? venda.getValorTotal() : venda.getValorTotal() * -1);
        return saveVendaByLocalCaixaIdAndLoja(lojaId,localCaixaId, venda);
    }

    @Override
    public void apagarVenda(Integer lojaId, Integer localId) throws IllegalArgumentException, IllegalStateException{
        List<Venda> vendas = vendaPort.findByLojaIdAndlocalVendaId(lojaId,localId);
        vendas.forEach(venda -> vendaPort.deleteVenda(venda));
    }

    @Override
    @Transactional
    public Caixa abrirCaixa(Caixa caixa) throws IllegalArgumentException, IllegalStateException {
        Caixa lastCaixa = caixaPort.findCaixaByLojaIdAndLocalCaixaId(caixa.getLojaId(), caixa.getLocalCaixaId());
        if(lastCaixa!=null && lastCaixa.getStatus().equals(Caixa.CaixaStatus.ABERTO) ) throw new IllegalArgumentException(String.format("Caixa com localCaixaId: %d já aberto", caixa.getLocalCaixaId()));

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
        Caixa lastCaixa = caixaPort.findCaixaByLojaIdAndLocalCaixaId(caixa.getLojaId(), caixa.getLocalCaixaId());
        if(lastCaixa!=null && lastCaixa.getStatus().equals(Caixa.CaixaStatus.FECHADO)) throw new IllegalArgumentException(String.format("Caixa com localCaixaId: %d já fechado", caixa.getLocalCaixaId()));

        Loja loja = lojaPort.findLojaById(caixa.getLojaId());
        if(loja == null) throw new IllegalArgumentException("Nenhuma loja localizada por id");

        lastCaixa.setStatus(Caixa.CaixaStatus.FECHADO);

        caixa.getOperacaoesCaixa().forEach(operacaoCaixa -> operacaoCaixa.setCaixaId(lastCaixa.getCaixaId()));
        operacaoCaixaPort.insertAll(caixa.getOperacaoesCaixa()).forEach(operacaoCaixa -> lastCaixa.addOperacoesCaixa(operacaoCaixa));

        return caixaPort.insert(lastCaixa);
    }

    @Override
    @Transactional
    public OperacaoCaixa addOperacaoByLocalCaixaIdAndLojaId(OperacaoCaixa operacaoCaixa, Integer lojaId, Integer localCaixaId) {
        Loja loja = lojaPort.findLojaById(lojaId);
        if(loja == null) throw new IllegalArgumentException("Nenhuma loja localizada por id");

        Caixa lastCaixa = caixaPort.findCaixaByLojaIdAndLocalCaixaId(lojaId, localCaixaId);
        if(lastCaixa == null) throw new IllegalArgumentException(String.format("Nenhum caixa aberto com localCaixaId: %d encontrado", localCaixaId));
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
