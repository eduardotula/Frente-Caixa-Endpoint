package com.loja65.frentecaixa.outbound.adapters.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Entity(name = "caixa")
@Table(name = "caixa")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CaixaEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "caixa_id",columnDefinition = "INTEGER")
    private Integer caixaId;

    @NotNull
    @Column(name = "localCaixa_id", columnDefinition = "INTEGER")
    private Integer localCaixaId;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "loja_id_fk")
    private LojaEntity loja;

    @Column(name = "status")
    private String status;

    @OneToMany(mappedBy = "caixa")
    private List<OperacaoCaixaEntity> operacaoesCaixa;

    @OneToMany(mappedBy = "caixa")
    private List<VendaEntity> vendas;

    @Column(name = "funcionario",columnDefinition = "VARCHAR(100)")
    private String funcionario;

    @Column(name = "createdAt",columnDefinition = "TIMESTAMP")
    private LocalDateTime createdAt;
}
