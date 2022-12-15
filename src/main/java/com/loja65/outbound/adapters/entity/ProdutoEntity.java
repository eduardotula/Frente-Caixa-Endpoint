package com.loja65.outbound.adapters.entity;

import com.loja65.outbound.port.ProdutoPort;
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
    @Column(name = "codBarra",columnDefinition = "VARCHAR(14)", unique = true)
    private String codBarra;

    @Column(name = "descricao",columnDefinition = "VARCHAR(80)")
    private String descricao;

    @Column(name = "valorUltVenda",columnDefinition = "NUMERIC(18,2)")
    private Double valorUltVenda;

    @Column(name = "valor",columnDefinition = "NUMERIC(18,2)")
    private Double valor;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "loja_id_fk")
    private LojaEntity loja;

    @Column(name = "DataUltVenda",columnDefinition = "TIMESTAMP")
    private LocalDateTime DataUltVenda;

    @Column(name = "createdAt",columnDefinition = "TIMESTAMP")
    private LocalDateTime createdAt;
}
