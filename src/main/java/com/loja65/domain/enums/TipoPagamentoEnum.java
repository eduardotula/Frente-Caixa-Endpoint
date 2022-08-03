package com.loja65.domain.enums;

public enum TipoPagamentoEnum {


    PIX("PIX"),
    DINHEIRO("DINHEIRO"),
    CARTAO("CARTAO");
    private String descricao;

    TipoPagamentoEnum(String descricao){this.descricao = descricao;}


}
