package com.loja65.outbound.adapters.entity;


import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "loja")
@Table(name = "loja")
public class LojaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "loja_id",columnDefinition = "INTEGER")
    private Integer lojaId;

    @Column(name = "nome",columnDefinition = "VARCHAR(100)")
    private String nome;

    @Column(name = "cnpj",columnDefinition = "VARCHAR(20)")
    private String cnpj;

    @Column(name = "rua",columnDefinition = "VARCHAR(100)")
    private String rua;

    @Column(name = "cidade",columnDefinition = "VARCHAR(100)")
    private String cidade;

    @Column(name = "cep",columnDefinition = "VARCHAR(20)")
    private String cep;

    @OneToMany(mappedBy = "loja")
    private List<CaixaEntity> caixas;

    @OneToMany(mappedBy = "loja")
    private List<ConsultaPrecoProdutoEntity> consultaPrecoProdutos;

    @Column(name = "createdAt",columnDefinition = "TIMESTAMP")
    private LocalDateTime createdAt;

    @Column(name = "lastUpdated", columnDefinition = "TIMESTAMP")
    private LocalDateTime lastUpdated;
}
