package com.loja65.outbound.adapters.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity(name = "venda")
@Table(name = "venda")
public class VendaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "venda_id",columnDefinition = "INTEGER")
    private Integer vendaId;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "caixa_id_fk")
    private CaixaEntity caixa;

    @NotNull
    @Column(name = "localVendaId",columnDefinition = "INTEGER")
    private Integer localVendaId;

    @Column(name = "quantidade",columnDefinition = "INTEGER")
    private Integer quantidade;

    @Column(name = "valorDinheiro",columnDefinition = "DECIMAL(18,2)")
    private Double valorDinheiro;

    @Column(name = "valorCartao",columnDefinition = "DECIMAL(18,2)")
    private Double valorCartao;

    @Column(name = "valorTotal",columnDefinition = "DECIMAL(18,2)")
    private Double valorTotal;

    @Column(name = "tipoPagamento")
    private String tipoPagamento;

    @ManyToOne()
    @JoinColumn(name = "produto_id_fk")
    private ProdutoEntity produto;

    @Column(name = "createdAt",columnDefinition = "DATETIME")
    private LocalDateTime createdAt;
}
