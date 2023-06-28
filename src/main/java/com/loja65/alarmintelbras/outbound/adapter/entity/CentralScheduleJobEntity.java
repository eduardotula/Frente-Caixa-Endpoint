package com.loja65.alarmintelbras.outbound.adapter.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "centralScheduleJob")
@Table(name = "central_schedule_job")
public class CentralScheduleJobEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "central_schedule_job_id",columnDefinition = "INTEGER")
    private Integer centralScheduleJobId;

    @NotNull
    @Column(name = "central_mac", columnDefinition = "VARCHAR(300)")
    private String description;
    @NotNull
    @Column(name = "running_time", columnDefinition = "TIMESTAMP")
    private LocalDateTime running_time;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "central_id_fk")
    private CentralEntity centralEntity;
}
