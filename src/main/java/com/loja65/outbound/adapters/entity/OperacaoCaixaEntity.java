package com.loja65.outbound.adapters.entity;

import com.loja65.domain.enums.OperacaoCaixaEnum;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity(name = "operacaoCaixa")
@Table(name = "operacaoCaixa")
public class OperacaoCaixaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "operacaoCaixa_id",columnDefinition = "INTEGER")
    private Integer operacaoCaixaId;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "caixa_id_fk")
    private CaixaEntity caixa;
    @NotNull
    @Column(name = "localOperacaoCaixa_id",columnDefinition = "INTEGER")
    private Integer localOperacaoCaixaId;

    @Enumerated(EnumType.STRING)
    @Column(name = "operacao")
    private OperacaoCaixaEnum operacao;

    @Column(name = "trocoCaixa",columnDefinition = "DECIMAL(18,2)")
    private Double trocoCaixa;

    @Column(name = "valorCart",columnDefinition = "DECIMAL(18,2)")
    private Double valorCart;

    @Column(name = "valorDinheiro",columnDefinition = "DECIMAL(18,2)")
    private Double valorDinheiro;

    @Column(name = "createdAt",columnDefinition = "DATETIME")
    private LocalDateTime createdAt;
}
