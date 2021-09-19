package com.smalltektcc.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "usuario")
public class Usuario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="idusuario")
	private Long idusuario;
	
	private String nome;
	private String cpf;
	private String sexo;
	private String idade;
	private String email;
	private String senha;
	private boolean tipo; 
	
	//id do endereco em usuario
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "idusuario")
	private Set<Endereco> enderecos;
	
	public Set<Endereco> getEnderecos() {
		return enderecos;
	}

	public void setEnderecos(Set<Endereco> enderecos) {
		this.enderecos = enderecos;
	}
	
	public Long getIdusuario() {
		return idusuario;
	}
	
	public void setIdusuario(Long idusuario) {
		this.idusuario = idusuario;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getCpf() {
		return cpf;
	}
	
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
	public String getSexo() {
		return sexo;
	}
	
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	
	public String getIdade() {
		return idade;
	}
	
	public void setIdade(String idade) {
		this.idade = idade;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getSenha() {
		return senha;
	}
	
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	public boolean isTipo() {
		return tipo;
	}

	public void setTipo(boolean tipo) {
		this.tipo = tipo;
	}

	//id do feedback em usuario
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "usuario")
	@JoinColumn(name = "idusuario")
	private Feedback feedbacks;

	public Feedback getFeedbacks() {
		return feedbacks;
	}

	public void setFeedbacks(Feedback feedbacks) {
		this.feedbacks = feedbacks;
	}
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "idusuario")
	private Set<Pedido> pedidos;

	public Set<Pedido> getPedidos() {
		return pedidos;
	}

	public void setPedidos(Set<Pedido> pedidos) {
		this.pedidos = pedidos;
	}

	
}
