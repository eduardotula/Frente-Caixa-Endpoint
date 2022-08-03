package com.loja65.inbound.dto.consulta;

import com.loja65.domain.enums.TipoPagamentoEnum;
import com.loja65.domain.model.Produto;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class VendaConsultaDto {

    private Integer quantidade;
    private Double valorDinheiro;
    private Double valorCartao;
    private Double valorTotal;
    private TipoPagamentoEnum tipoPagamento;
    private LocalDateTime createdAt;
    private Integer localVendaId;
    private Produto produto;
}
