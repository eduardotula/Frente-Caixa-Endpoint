package com.loja65.frentecaixa.inbound.adapter.dto;

import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProdutovendaDto {

    private Integer produtoId;
    @NotBlank(message = "codBarra não informado")
    @Length(min = 1, max = 14, message = "codBarra tamanho não permitido: min: 1, max = 14")
    private String codBarra;

    @NotBlank(message = "descricao não informado")
    @Length(min = 1, max = 80, message = "descricao tamanho não permitido: min = 1, max = 80")
    private String descricao;
}
