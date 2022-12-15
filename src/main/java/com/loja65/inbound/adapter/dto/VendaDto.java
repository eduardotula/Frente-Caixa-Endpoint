package com.loja65.inbound.adapter.dto;


import lombok.*;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
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
    @Pattern(regexp = "(DINHEIRO|CARTAO|PIX|CARTAO_DINHEIRO)", message = "Forma de pagamento inválida (DINHEIRO|CARTAO|PIX|CARTAO_DINHEIRO)")
    private String tipoPagamento;

    @NotNull(message = "localVendaId não informado")
    private Integer localVendaId;

    @Valid
    @NotNull(message = "produto não informado")
    private ProdutovendaDto produto;

    @NotNull(message = "createdAt não informado")
    private LocalDateTime createdAt;
}
