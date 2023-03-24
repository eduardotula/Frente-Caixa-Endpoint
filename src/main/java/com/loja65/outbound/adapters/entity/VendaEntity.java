package com.loja65.outbound.adapters.entity;

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

    @Column(name = "valordinheiro",columnDefinition = "NUMERIC(18,2)")
    private Double valorDinheiro;

    @Column(name = "valorcartao",columnDefinition = "NUMERIC(18,2)")
    private Double valorCartao;

    @Column(name = "valortotal",columnDefinition = "NUMERIC(18,2)")
    private Double valorTotal;

    @Column(name = "desconto",columnDefinition = "NUMERIC(18,2)")
    private Double desconto;

    @Column(name = "valorFinal",columnDefinition = "NUMERIC(18,2)")
    private Double valorFinal;

    @Column(name = "tipopagamento")
    private String tipoPagamento;

    @ManyToOne()
    @JoinColumn(name = "produto_id_fk")
    private ProdutoEntity produto;

    @Column(name = "createdAt",columnDefinition = "TIMESTAMP")
    private LocalDateTime createdAt;
}
