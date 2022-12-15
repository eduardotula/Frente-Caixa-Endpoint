package com.loja65.domain.model;

import com.loja65.domain.enums.TipoPagamentoEnum;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Venda {

    private Integer vendaId;
    private Integer caixaId;
    private Integer quantidade;
    private Double valorDinheiro;
    private Double valorCartao;
    private Double valorTotal;
    private TipoPagamentoEnum tipoPagamento;
    private LocalDateTime createdAt;
    private Integer localVendaId;
    private Produto produto;



}
