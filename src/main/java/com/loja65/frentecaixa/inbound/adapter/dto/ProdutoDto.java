package com.loja65.frentecaixa.inbound.adapter.dto;

import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProdutoDto {

    private Integer produtoId;

    @NotBlank(message = "codBarra não informado")
    @Length(min = 1, max = 14, message = "codBarra tamanho não permitido: min: 1, max = 14")
    private String codBarra;

    @NotBlank(message = "descricao não informado")
    @Length(min = 1, max = 80, message = "descricao tamanho não permitido: min = 1, max = 80")
    private String descricao;

    @NotNull(message = "valorUltVenda não informado")
    private Double valorUltVenda;

    @NotNull(message = "valor não informado")
    private Double valor;

    private LocalDateTime dataUltVenda;

    private Integer lojaId;
}
