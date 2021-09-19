package com.smalltektcc.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="pedido_produto")
public class PedidoProduto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="idpedido_produto")
	private Long id;
	
	@Column(name="idpedido")
	private Long idpedido;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idpedido", insertable = false, updatable = false, nullable = false)
	private Pedido pedido;
	
	@Column(name="idproduto")
	private Long idproduto;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idproduto", insertable = false, updatable = false, nullable = false)
	private Produto produto;

	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getIdpedido() {
		return idpedido;
	}
	public void setIdpedido(Long idpedido) {
		this.idpedido = idpedido;
	}
	public Long getIdproduto() {
		return idproduto;
	}
	public void setIdproduto(Long idproduto) {
		this.idproduto = idproduto;
	}
	
	
	
	
}
