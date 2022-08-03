package com.loja65.domain.enums;

public enum OperacaoCaixaEnum {


    SANGRIA("SANGRIA"),
    ABRIR_CAIXA("ABRIR_CAIXA"),
    FECHAR_CAIXA("FECHAR_CAIXA");
    private String descricao;

    OperacaoCaixaEnum(String descricao){this.descricao = descricao;}


}
