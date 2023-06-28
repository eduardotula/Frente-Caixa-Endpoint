package com.loja65.alarmintelbras.inbound.adapter.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class CentralDto {

    private Integer centralId;

    @NotNull(message = "descricao não informado")
    @NotEmpty(message = "descricao não informado")
    private String descricao;
    @NotNull(message = "centralMac não informado")
    @NotEmpty(message = "centralMac não informado")
    private String centralMac;
    @NotNull(message = "password não informado")
    @NotEmpty(message = "password não informado")
    private String password;
    @NotNull(message = "activationTime não informado")
    private LocalDateTime activationTime;
    @NotNull(message = "deactivationTime não informado")
    private LocalDateTime deactivationTime;
}
