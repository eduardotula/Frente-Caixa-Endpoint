package com.loja65.inbound.adapter.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LojaDto {

    private Integer lojaId;
    @Length(min = 1, max = 100, message = "tamanho de nome não permitido: min = 0, max = 100")
    @NotNull(message = "nome não informado")
    private String nome;
    @NotNull(message = "cnpj não informado")
    @Length(min = 1, max = 20, message = "tamanho de cnpj não permitido: min = 0, max = 20")
    private String cnpj;
    @NotNull(message = "rua não informado")
    @Length(min = 1, max = 100, message = "tamanho de rua não permitido: min = 0, max = 100")
    private String rua;
    @NotNull(message = "cidade não informado")
    @Length(min = 1, max = 100, message = "tamanho de cidade não permitido: min = 0, max = 100")
    private String cidade;
    @NotNull(message = "cep não informado")
    @Length(min = 1, max = 20, message = "tamanho de cep não permitido: min = 0, max = 20")
    private String cep;

}
