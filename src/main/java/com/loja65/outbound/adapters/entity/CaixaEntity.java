package com.loja65.outbound.adapters.entity;

import com.loja65.domain.model.Caixa;
import com.loja65.domain.model.Loja;
import com.loja65.domain.model.OperacaoCaixa;
import com.loja65.domain.model.Venda;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GeneratorType;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Entity(name = "caixa")
@Table(name = "caixa")
@Getter
@Setter
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

    @Column(name = "createdAt",columnDefinition = "DATETIME")
    private LocalDateTime createdAt;
}
