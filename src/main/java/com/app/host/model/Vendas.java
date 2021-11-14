package com.app.host.model;

import java.io.Serializable;
import java.time.LocalTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;


@Entity
@Table(name = "VENDAS")
public class Vendas implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Integer id;
	@Column(name = "C_BARRA", columnDefinition = "VARCHAR(20)")
	private String codBarra;
	@Column(name = "DESCRICAO", columnDefinition = "VARCHAR(20)")
	private String descricao;
	@Column(name = "QUANTI")
	private Integer quantidade;
	@Column(name = "TIPO_PAGA", columnDefinition = "CHAR(2)")
	private String tipoPaga;
	@Column(name = "V_UNI", columnDefinition = "DECIMAL(18,2)")
	private Double vUni;
	@Column(name = "V_CART",columnDefinition = "DECIMAL(18,2)")
	private Double vCart;
	@Column(name = "V_DINHE",columnDefinition = "DECIMAL(18,2)")
	private Double vDinhe;
	@Column(name = "V_TOTAL", columnDefinition = "DECIMAL(18,2)")
	private Double vTotal;
	@Column(name = "HORA",columnDefinition = "TIME")
	private LocalTime hora;

	@JsonBackReference
	@ManyToOne
	@JoinColumn(name="FK_CAIXA_ID")
	private Caixa caixa;
	
	public Vendas() {
		
	}

	
	public Vendas(Integer id, String codBarra, String descricao, Integer quantidade, String tipoPaga, Double vUni,
			Double vCart, Double vDinhe, Double vTotal, LocalTime hora, Caixa caixa) {
		super();
		this.id = id;
		this.codBarra = codBarra;
		this.descricao = descricao;
		this.quantidade = quantidade;
		this.tipoPaga = tipoPaga;
		this.vUni = vUni;
		this.vCart = vCart;
		this.vDinhe = vDinhe;
		this.vTotal = vTotal;
		this.hora = hora;
		this.caixa = caixa;
	}


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCodBarra() {
		return codBarra;
	}

	public void setCodBarra(String codBarra) {
		this.codBarra = codBarra;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public String getTipoPaga() {
		return tipoPaga;
	}

	public void setTipoPaga(String tipoPaga) {
		this.tipoPaga = tipoPaga;
	}

	public Double getvUni() {
		return vUni;
	}

	public void setvUni(Double vUni) {
		this.vUni = vUni;
	}

	public Double getvCart() {
		return vCart;
	}

	public void setvCart(Double vCart) {
		this.vCart = vCart;
	}

	public Double getvDinhe() {
		return vDinhe;
	}

	public void setvDinhe(Double vDinhe) {
		this.vDinhe = vDinhe;
	}

	public Double getvTotal() {
		return vTotal;
	}

	public void setvTotal(Double vTotal) {
		this.vTotal = vTotal;
	}

	public LocalTime getHora() {
		return hora;
	}

	public void setHora(LocalTime hora) {
		this.hora = hora;
	}


	public Caixa getCaixa() {
		return caixa;
	}


	public void setCaixa(Caixa caixa) {
		this.caixa = caixa;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Vendas other = (Vendas) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	
	
}
