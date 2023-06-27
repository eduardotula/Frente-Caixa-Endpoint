package com.loja65.frentecaixa.domain.model.filters;

import io.quarkus.arc.All;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.criteria.CriteriaBuilder;
import javax.ws.rs.GET;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProdutoFilter {

    private String codBarra = null;
    private String descricao = null;
    private Integer lojaId = null;
    private Integer produtoId = null;
}
