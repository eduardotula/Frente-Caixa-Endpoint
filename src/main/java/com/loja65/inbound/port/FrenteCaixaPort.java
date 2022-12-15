package com.loja65.inbound.port;

import com.loja65.domain.model.Caixa;
import com.loja65.domain.model.Loja;
import com.loja65.domain.model.OperacaoCaixa;
import com.loja65.domain.model.Venda;
import com.loja65.inbound.adapter.dto.ProdutoDto;

import java.util.List;

public interface FrenteCaixaPort {


    Venda inserirVendaUltimoCaixaAberto(Integer lojaId, Venda venda) ;
    void apagarVenda(Integer lojaId, Integer localId) throws IllegalArgumentException, IllegalStateException;

    Caixa abrirCaixa(Caixa caixa);
    Caixa fecharCaixa(Caixa caixa);
    OperacaoCaixa addOperacaoLastCaixa(OperacaoCaixa operacaoCaixa, Integer lojaId);
    Loja cadastrarLoja(Loja loja);

    List<Loja> getAllLojas();
}
