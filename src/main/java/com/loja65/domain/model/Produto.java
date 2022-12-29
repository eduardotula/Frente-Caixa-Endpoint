package com.loja65.domain.model;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Produto {

    private Integer produtoId;
    private String codBarra;
    private String descricao;
    private Double valorUltVenda;
    private Double valor;
    private Integer lojaId;
    private LocalDateTime DataUltVenda;
    private LocalDateTime createdAt;

    public void create(LocalDateTime createdAt, Integer lojaId){
        this.createdAt = createdAt;
        this.lojaId = lojaId;
    }
}
