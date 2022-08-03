package com.loja65.inbound.dto;


import lombok.Data;

import javax.validation.Payload;
import javax.validation.Valid;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class VendaDto {

    @NotNull()
    private Integer quantidade;
    @NotNull(message = "valorDinheiro não informado")
    private Double valorDinheiro;
    @NotNull(message = "valorCartão não informado")
    private Double valorCartao;
    @NotNull(message = "valorTotal não informado")
    private Double valorTotal;

    @NotBlank(message = "Forma de pagamento não informada")
    @Pattern(regexp = "(DINHEIRO|CARTAO|PIX)", message = "Forma de pagamento inválida (DINHEIRO,CARTAO,PIX)")
    private String tipoPagamento;

    @NotNull(message = "localVendaId não informado")
    private Integer localVendaId;

    @Valid
    @NotNull(message = "produto não informado")
    private ProdutoDto produto;

    @NotNull(message = "createdAt não informado")
    private LocalDateTime createdAt;
}
