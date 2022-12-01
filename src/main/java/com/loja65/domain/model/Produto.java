package com.loja65.domain.model;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class Produto {

    private Integer produtoId;
    private String codBarra;
    private String descricao;
    private Double valorUltVenda;
    private Double valor;
    private LocalDateTime DataUltVenda;
    private LocalDateTime createdAt;
}
