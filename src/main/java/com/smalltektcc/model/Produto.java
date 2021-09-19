package com.smalltektcc.model;


import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "produto")
public class Produto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="idproduto")
	private Long idproduto;
	
	private String nome;
	private String descricao;
	private int quantidade;
	private double preco;
	
	
	public Long getIdproduto() {
		return idproduto;
	}
	public void setIdproduto(Long idproduto) {
		this.idproduto = idproduto;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public int getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	public double getPreco() {
		return preco;
	}
	public void setPreco(double preco) {
		this.preco = preco;
	}
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "idproduto", insertable = false, updatable = false, nullable = false)
	private Set<PedidoProduto> pedidos_produtos;


	public Set<PedidoProduto> getPedidos_produtos() {
		return pedidos_produtos;
	}
	public void setPedidos_produtos(Set<PedidoProduto> pedidos_produtos) {
		this.pedidos_produtos = pedidos_produtos;
	}
	
	
	
}
