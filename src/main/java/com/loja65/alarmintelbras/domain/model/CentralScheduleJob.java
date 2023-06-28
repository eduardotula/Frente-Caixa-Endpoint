package com.loja65.alarmintelbras.domain.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CentralScheduleJob {

    private Integer centralScheduleJobId;
    private String description;
    private LocalDateTime running_time;
    private Integer centralId;
}
