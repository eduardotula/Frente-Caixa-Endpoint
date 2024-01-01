package com.loja65.alarmintelbras.domain.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CentralScheduleJob {

    private Integer centralScheduleJobId;
    private String description;
    private LocalTime runningTime;
    private Integer centralId;
}
