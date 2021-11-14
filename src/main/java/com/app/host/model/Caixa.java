package com.app.host.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "CAIXA")
public class Caixa implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Integer id;
	@Column(name = "FUNCIONARIO",columnDefinition = "VARCHAR(100)")
	private String funcionario;
	@Column(name = "STATUS",columnDefinition = "BOOLEAN")
	private Boolean status; //Indica o status do caixa true = aberto false = fechado
	@Column(name = "DATAHORA",columnDefinition = "TIMESTAMP")
	private LocalDateTime dataHora;
	
	@JsonManagedReference
	@OneToMany(mappedBy ="caixa")
	private List<Vendas> vendas = new ArrayList<Vendas>();
	
	@JsonBackReference
	@ManyToOne
	@JoinColumn(name="FK_LOJA_ID")
	private Loja loja;
	
	public Caixa() {
	}

	public Caixa(Integer id, String funcionario, Boolean status, LocalDateTime dataHora, List<Vendas> vendas,
			Loja loja) {
		super();
		this.id = id;
		this.funcionario = funcionario;
		this.status = status;
		this.dataHora = dataHora;
		this.vendas = vendas;
		this.loja = loja;
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getFuncionario() {
		return funcionario;
	}


	public void setFuncionario(String funcionario) {
		this.funcionario = funcionario;
	}


	public LocalDateTime getDataHora() {
		return dataHora;
	}


	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public void setDataHora(LocalDateTime dataHora) {
		this.dataHora = dataHora;
	}
	
	public void addVendas(Vendas vendas) {
		this.vendas.add(vendas);
		vendas.setCaixa(this);
	}
	public void removeVendas(Vendas vendas) {
		this.vendas.remove(vendas);
		vendas.setCaixa(null);
	}
	
	
	public List<Vendas> getVendas() {
		return vendas;
	}
	
	
	public void setVendas(List<Vendas> vendas) {
		this.vendas = vendas;
	}
	
	
	public Loja getLoja() {
		return loja;
	}

	public void setLoja(Loja loja) {
		this.loja = loja;
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
		Caixa other = (Caixa) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
}
