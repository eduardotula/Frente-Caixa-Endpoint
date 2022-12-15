package com.loja65.outbound.adapters.entity;

import com.loja65.domain.enums.OperacaoCaixaEnum;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
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

    @Column(name = "trocoCaixa",columnDefinition = "NUMERIC(18,2)")
    private Double trocoCaixa;

    @Column(name = "valorCart",columnDefinition = "NUMERIC(18,2)")
    private Double valorCart;

    @Column(name = "valorDinheiro",columnDefinition = "NUMERIC(18,2)")
    private Double valorDinheiro;

    @Column(name = "createdAt",columnDefinition = "TIMESTAMP")
    private LocalDateTime createdAt;
}
