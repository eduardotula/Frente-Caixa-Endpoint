package com.loja65.frentecaixa.inbound.adapter.dto.consulta;

import com.loja65.frentecaixa.domain.model.Caixa;
import com.loja65.frentecaixa.inbound.adapter.dto.OperacaoCaixaDto;
import com.loja65.frentecaixa.inbound.adapter.dto.VendaDto;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class CaixaConsultaDto {

    private Integer caixaId;
    private Caixa.CaixaStatus status;
    private LocalDateTime createdAt;
    private List<OperacaoCaixaDto> operacaoesCaixa;
    private List<VendaDto> vendas;
    private String funcionario;
}
