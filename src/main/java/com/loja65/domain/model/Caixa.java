package com.loja65.domain.model;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
public class Caixa {



    public enum CaixaStatus{
        ABERTO,
        FECHADO
    }
    private Integer caixaId;
    private Integer localCaixaId;
    private Integer lojaId;
    private CaixaStatus status;
    private LocalDateTime createdAt;
    private List<OperacaoCaixa> operacaoesCaixa;
    private List<Venda> vendas;
    private String funcionario;



    public void addVenda(Venda venda){
        if(this.vendas == null) this.vendas = new ArrayList<>();
        this.vendas.add(venda);
    }

    public void addOperacoesCaixa(OperacaoCaixa operacaoCaixa){
        if(this.operacaoesCaixa == null) this.operacaoesCaixa = new ArrayList<>();
        this.operacaoesCaixa.add(operacaoCaixa);
    }
}
