package com.loja65.domain.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Produto {

    private Integer produtoId;
    private String codBarra;
    private String descricao;
    private Double valorUltVenda;
    private LocalDateTime DataUltVenda;
    private LocalDateTime createdAt;
}
