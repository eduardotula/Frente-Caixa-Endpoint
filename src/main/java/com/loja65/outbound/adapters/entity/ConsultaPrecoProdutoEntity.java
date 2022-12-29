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
@Table(name = "consulta_preco_produto")
@Entity(name = "consultaPrecoProduto")
public class ConsultaPrecoProdutoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "consulta_id",columnDefinition = "INTEGER")
    private Integer id;

    @Column(name = "new_value", columnDefinition = "NUMERIC(18,2)")
    private Double newValue;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "produto_id_fk")
    private ProdutoEntity produto;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "loja_id_fk")
    private LojaEntity loja;

    @Column(name = "created_at", columnDefinition = "TIMESTAMP")
    private LocalDateTime createdAt;

}
