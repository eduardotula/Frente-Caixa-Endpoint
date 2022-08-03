package com.loja65.outbound.adapters.entity;

import com.loja65.outbound.port.ProdutoPort;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
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

    @Column(name = "valorUltVenda",columnDefinition = "DECIMAL(18,2)")
    private Double valorUltVenda;

    @Column(name = "DataUltVenda",columnDefinition = "DATETIME")
    private LocalDateTime DataUltVenda;

    @Column(name = "createdAt",columnDefinition = "DATETIME")
    private LocalDateTime createdAt;
}
