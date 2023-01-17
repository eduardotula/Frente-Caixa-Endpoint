package com.loja65.domain.model.filters;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VendaFilter {

    private LocalDateTime dataInicial = null;
    private LocalDateTime dataFinal = null;
    private String funcionario = null;
    private Integer lojaId = null;
}
