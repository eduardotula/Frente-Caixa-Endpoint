package com.loja65.inbound.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;


@Data
public class ProdutoDto {

    @NotBlank(message = "codBarra não informado")
    @Length(min = 1, max = 14, message = "codBarra tamanho não permitido: min: 1, max = 14")
    private String codBarra;

    @NotBlank(message = "descricao não informado")
    @Length(min = 1, max = 80, message = "descricao tamanho não permitido: min = 1, max = 80")
    private String descricao;
}
