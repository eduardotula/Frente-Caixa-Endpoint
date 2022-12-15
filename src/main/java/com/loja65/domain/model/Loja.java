package com.loja65.domain.model;

import lombok.*;

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
    private List<Caixa> caixas;


}
