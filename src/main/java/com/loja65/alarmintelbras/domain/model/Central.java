package com.loja65.alarmintelbras.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.time.LocalTime;
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
    private LocalTime activationTime;
    private LocalTime deactivationTime;
    private List<CentralScheduleJob> centralScheduleJobList;

    public String getActivationJobName(){
        return String.format("active:%s:%s", this.getDescricao(), this.centralMac);
    }

    public String getDisableJobName(){
        return  String.format("disable:%s:%s", this.getDescricao(), this.centralMac);
    }

    public String getActivationJobRetryName(LocalDateTime localDateTime){
        return String.format("retry_active_%s:%s :%s:%s",localDateTime.getHour(), localDateTime.getMinute(),
                this.getDescricao(), this.centralMac);
    }

    public String getDisableJobRetryName(LocalDateTime localDateTime){
        return  String.format("retry_disable_%s:%s: %s:%s",localDateTime.getHour(), localDateTime.getMinute(),
                this.getDescricao(), this.centralMac);
    }

}
