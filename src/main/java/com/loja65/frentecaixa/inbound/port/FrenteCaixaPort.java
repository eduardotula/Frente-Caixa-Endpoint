package com.loja65.frentecaixa.inbound.port;

import com.loja65.frentecaixa.domain.model.Caixa;
import com.loja65.frentecaixa.domain.model.Loja;
import com.loja65.frentecaixa.domain.model.OperacaoCaixa;
import com.loja65.frentecaixa.domain.model.Venda;

import javax.transaction.Transactional;
import java.util.List;

public interface FrenteCaixaPort {


    Venda saveVendaByLocalCaixaIdAndLoja(Integer lojaId, Integer localCaixaId, Venda venda) ;

    @Transactional
    Venda saveTrocaOperation(Integer lojaId, Integer localCaixaId, Venda venda);

    void apagarVenda(Integer lojaId, Integer localId) throws IllegalArgumentException, IllegalStateException;

    Caixa abrirCaixa(Caixa caixa);
    Caixa fecharCaixa(Caixa caixa);
    OperacaoCaixa addOperacaoByLocalCaixaIdAndLojaId(OperacaoCaixa operacaoCaixa, Integer lojaId, Integer localCaixaId);
    Loja cadastrarLoja(Loja loja);

    List<Loja> getAllLojas();

    void updateLojaLastUpdatedWithCurrentTime(Integer lojaId);

}
