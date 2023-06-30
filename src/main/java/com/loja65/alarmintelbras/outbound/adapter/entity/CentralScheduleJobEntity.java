package com.loja65.alarmintelbras.outbound.adapter.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "centralScheduleJob")
@Table(name = "central_schedule_job")
public class CentralScheduleJobEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "central_schedule_job_id")
    private Integer centralScheduleJobId;

    @NotNull
    @Column(name = "job_descricao", columnDefinition = "VARCHAR(300)")
    private String description;
    @NotNull
    @Column(name = "running_time", columnDefinition = "TIME")
    private LocalTime runningTime;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "central_id_fk")
    private CentralEntity central;
}
