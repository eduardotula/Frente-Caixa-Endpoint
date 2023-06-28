package com.loja65.alarmintelbras.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Central {

    private Integer centralId;
    private String descricao;
    private String centralMac;
    private String password;
    private LocalDateTime activationTime;
    private LocalDateTime deactivationTime;
    private List<CentralScheduleJob> centralScheduleJobList;

}
