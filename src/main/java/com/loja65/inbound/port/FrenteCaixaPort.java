package com.loja65.inbound.port;

import com.loja65.domain.model.*;

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
