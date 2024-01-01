package com.loja65.frentecaixa.inbound.adapter.dto;

import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OperacaoCaixaDto {

    private Integer operacaoCaixaId;
    @NotNull(message = "localOperacaoCaixaId não informado")
    private Integer localOperacaoCaixaId;

    @Pattern(regexp = "(SANGRIA|ABRIR_CAIXA|FECHAR_CAIXA)", message = "Status de operação inválido (SANGRIA,ABRIR_CAIXA,FECHAR_CAIXA)")
    private String operacao;

    @NotNull(message = "trocoCaixa não informado")
    private Double trocoCaixa;

    @NotNull(message = "valorCart não informado")
    private Double valorCart;

    @NotNull(message = "valorDinheiro não informado")
    private Double valorDinheiro;
    @NotNull(message = "createdAt não informado")
    private LocalDateTime createdAt;
}
