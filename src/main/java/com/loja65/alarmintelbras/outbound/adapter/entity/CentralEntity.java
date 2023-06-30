package com.loja65.alarmintelbras.outbound.adapter.entity;

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
@Entity(name = "central")
@Table(name = "central")
public class CentralEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "central_id")
    private Integer centralId;

    @NotNull
    @Column(name = "descricao", columnDefinition = "VARCHAR(100)")
    private String descricao;

    @NotNull
    @Column(name = "central_mac", columnDefinition = "VARCHAR(12)")
    private String centralMac;

    @NotNull
    @Column(name = "password", columnDefinition = "VARCHAR(4)")
    private String password;

    @NotNull
    @Column(name = "activation_time", columnDefinition = "TIME")
    private LocalTime activationTime;

    @NotNull
    @Column(name = "deactivation_time", columnDefinition = "TIME")
    private LocalTime deactivationTime;

    @OneToMany(mappedBy = "central")
    private List<CentralScheduleJobEntity> centralScheduleJobList;

}
