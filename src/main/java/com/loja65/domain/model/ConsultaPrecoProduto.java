package com.loja65.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ConsultaPrecoProduto {

    private Integer id;
    private Double newValue;
    private Produto produto;
    private Loja loja;
    private LocalDateTime createdAt;

}
