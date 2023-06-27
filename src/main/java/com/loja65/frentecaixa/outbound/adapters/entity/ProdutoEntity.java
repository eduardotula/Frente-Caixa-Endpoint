package com.loja65.frentecaixa.outbound.adapters.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "produto")
@Table(name = "produto")
public class ProdutoEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "produto_id",columnDefinition = "INTEGER")
    private Integer produtoId;

    @NotNull
    @Column(name = "codBarra",columnDefinition = "VARCHAR(14)")
    private String codBarra;

    @Column(name = "descricao",columnDefinition = "VARCHAR(80)")
    private String descricao;

    @Column(name = "valorultvenda",columnDefinition = "NUMERIC(18,2)")
    private Double valorUltVenda;

    @Column(name = "valor",columnDefinition = "NUMERIC(18,2)")
    private Double valor;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "loja_id_fk")
    private LojaEntity loja;

    @Column(name = "dataultvenda",columnDefinition = "TIMESTAMP")
    private LocalDateTime dataUltVenda;

    @Column(name = "createdAt",columnDefinition = "TIMESTAMP")
    private LocalDateTime createdAt;
}
