package com.loja65.frentecaixa.domain.model;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Loja {

    private Integer lojaId;
    private String nome;
    private String cnpj;
    private String rua;
    private String cidade;
    private String cep;
    private LocalDateTime lastUpdated;

}
