package com.loja65.inbound.adapter.dto.consulta;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TotalVendasPeriodResponseDto {

    private Double totalCartao;
    private Double totalDinheiro;
    private Double total;
}
