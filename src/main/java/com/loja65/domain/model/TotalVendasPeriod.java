package com.loja65.domain.model;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TotalVendasPeriod {

    private Double totalCartao;
    private Double totalDinheiro;
    private Double total;
}
