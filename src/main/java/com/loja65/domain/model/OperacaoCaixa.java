package com.loja65.domain.model;

import com.loja65.domain.enums.OperacaoCaixaEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
public class OperacaoCaixa {

    private Integer operacaoCaixaId;
    private Integer caixaId;
    private Integer localOperacaoCaixaId;
    private OperacaoCaixaEnum operacao;
    private Double trocoCaixa;
    private Double valorCart;
    private Double valorDinheiro;
    private LocalDateTime createdAt;

}
