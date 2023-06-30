package com.loja65.alarmintelbras.inbound.adapter.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.time.LocalTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CentralDto {

    private Integer centralId;

    @NotEmpty(message = "descricao não informado")
    private String descricao;
    @NotEmpty(message = "centralMac não informado")
    private String centralMac;
    @NotEmpty(message = "password não informado")
    private String password;
    @NotNull(message = "activationTime não informado")
    private LocalTime activationTime;
    @NotNull(message = "deactivationTime não informado")
    private LocalTime deactivationTime;
}
