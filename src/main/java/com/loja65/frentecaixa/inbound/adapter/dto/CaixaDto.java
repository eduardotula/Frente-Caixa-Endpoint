package com.loja65.frentecaixa.inbound.adapter.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CaixaDto {

    @NotNull(message = "localCaixaId não informado")
    private Integer localCaixaId;
    @NotNull(message = "lojaId não informado")
    private Integer lojaId;

    @Pattern(regexp = "(ABERTO|FECHADO)", message = "Status de caixa inválido (ABERTO,FECHADO)")
    private String status;

    @NotBlank(message = "funcionario não informado")
    private String funcionario;

    @NotNull(message = "createdAt não informado")
    private LocalDateTime createdAt;
    @NotNull(message = "operacaoCaixaDtos não informado")
    @Valid
    private List<OperacaoCaixaDto> operacaoCaixaDto;
}
