package com.loja65.frentecaixa.domain.model;

import com.loja65.frentecaixa.domain.enums.OperacaoCaixaEnum;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
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
