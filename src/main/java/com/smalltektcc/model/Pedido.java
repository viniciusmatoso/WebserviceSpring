package com.smalltektcc.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.CascadeType;

//import org.hibernate.annotations.CascadeType;

@Entity
@Table(name = "pedido")
public class Pedido {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="idpedido")
	private Long idpedido;
	private String status;
	
	private Long idusuario;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idusuario", insertable = false, updatable = false, nullable = false)
	private Usuario usuario;
	
	
	public Long getIdpedido() {
		return idpedido;
	}
	
	public void setIdpedido(Long idpedido) {
		this.idpedido = idpedido;
	}
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}


	public Long getIdusuario() {
		return idusuario;
	}

	public void setIdusuario(Long idusuario) {
		this.idusuario = idusuario;
	}
	
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "idpedido")
	private Set<PedidoProduto> pedidos_produtos;

	public Set<PedidoProduto> getPedidos_produtos() {
		return pedidos_produtos;
	}

	public void setPedidos_produtos(Set<PedidoProduto> pedidos_produtos) {
		this.pedidos_produtos = pedidos_produtos;
	}

	
}
