package com.loja65.inbound.adapter.dto;


import lombok.*;

import javax.persistence.Column;
import javax.validation.Valid;
import javax.validation.constraints.*;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VendaDto {

    private Integer vendaId;
    @NotNull(message = "quantidade não informado")
    @Min(value = 0, message = "quantidade não pode ser negativo")
    private Integer quantidade;
    @NotNull(message = "valorDinheiro não informado")
    @Min(value = 0, message = "valorDinheiro não pode ser negativo")
    private Double valorDinheiro;
    @NotNull(message = "valorCartão não informado")
    @Min(value = 0, message = "valorCartao não pode ser negativo")
    private Double valorCartao;
    @NotNull(message = "valorTotal não informado")
    @Min(value = 0, message = "valorTotal não pode ser negativo")
    private Double valorTotal;
    @NotBlank(message = "Forma de pagamento não informada")
    @Pattern(regexp = "(DINHEIRO|CARTAO|PIX|CARTAO_DINHEIRO)", message = "Forma de pagamento inválida (DINHEIRO|CARTAO|PIX|CARTAO_DINHEIRO)")
    private String tipoPagamento;

    @NotNull(message = "desconto não informado")
    private Double desconto;

    private Double valorFinal;

    @NotNull(message = "localVendaId não informado")
    @Min(value = 1, message = "localVendaId não pode ser zerado")
    private Integer localVendaId;

    @Valid
    @NotNull(message = "produto não informado")
    private ProdutovendaDto produto;

    @NotNull(message = "createdAt não informado")
    private LocalDateTime createdAt;
}
