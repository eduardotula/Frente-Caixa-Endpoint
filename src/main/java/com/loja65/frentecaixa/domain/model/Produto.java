package com.loja65.frentecaixa.domain.model;

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
    private LocalDateTime dataUltVenda;
    private LocalDateTime createdAt;

    public void create(LocalDateTime createdAt, Integer lojaId){
        this.produtoId = null;
        this.createdAt = createdAt;
        this.lojaId = lojaId;
    }

    public void update(Produto oldProd){
        this.dataUltVenda = oldProd.getDataUltVenda();
        this.createdAt = oldProd.getCreatedAt();
        this.produtoId = oldProd.getProdutoId();
    }
}
