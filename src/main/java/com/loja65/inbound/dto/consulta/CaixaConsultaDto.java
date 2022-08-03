package com.loja65.inbound.dto.consulta;

import com.loja65.domain.model.Caixa;
import com.loja65.domain.model.OperacaoCaixa;
import com.loja65.domain.model.Venda;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class CaixaConsultaDto {

    private Caixa.CaixaStatus status;
    private LocalDateTime createdAt;
    private List<OperacaoCaixa> operacaoesCaixa;
    private List<Venda> vendas;
    private String funcionario;
}
