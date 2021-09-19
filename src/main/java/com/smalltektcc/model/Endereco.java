package com.smalltektcc.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "endereco")
public class Endereco {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idendereco;
	private String rua;
	private String bairro;
	private int numero;
	private Long idusuario;
	private int cep;
	
	//id do endereco em usuario
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idusuario", insertable = false, updatable = false, nullable = false)
	private Usuario usuario;
	
	public Long getIdendereco() {
		return idendereco;
	}
	public void setIdendereco(Long idendereco) {
		this.idendereco = idendereco;
	}
	public String getRua() {
		return rua;
	}
	public void setRua(String rua) {
		this.rua = rua;
	}
	public String getBairro() {
		return bairro;
	}
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	public int getNumero() {
		return numero;
	}
	public void setNumero(int numero) {
		this.numero = numero;
	}
	public Long getIdusuario() {
		return idusuario;
	}
	public void setIdusuario(Long idusuario) {
		this.idusuario = idusuario;
	}
	
	public int getCep() {
		return cep;
	}
	public void setCep(int cep) {
		this.cep = cep;
	}
}
