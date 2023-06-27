package com.loja65.frentecaixa.inbound.adapter.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ConsultaPrecoProdutoDto {

    @NotBlank(message = "codBarra não informado")
    @Length(min = 1, max = 14, message = "codBarra tamanho não permitido: min: 1, max = 14")
    private String codBarra;

    @NotBlank(message = "descricao não informado")
    @Length(min = 1, max = 80, message = "descricao tamanho não permitido: min = 1, max = 80")
    private String descricao;

    @NotNull(message = "Novo valor não informado")
    @Min(value = 0, message = "novo valor deve ser maior ou igual a zero")
    private Double newValue;

    private boolean notification = false;

}
