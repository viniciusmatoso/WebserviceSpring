package com.smalltektcc.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "feedback")
public class Feedback {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="idfeedback")
	private Long idfeedback;
	
	private String nota;
	private Long idusuario;
	
	@OneToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "idusuario", insertable = false, updatable = false, nullable = false)
	private Usuario usuario;
	
	public Long getIdfeedback() {
		return idfeedback;
	}
	public void setIdfeedback(Long idfeedback) {
		this.idfeedback = idfeedback;
	}
	public String getNota() {
		return nota;
	}
	public void setNota(String nota) {
		this.nota = nota;
	}
	public Long getIdusuario() {
		return idusuario;
	}
	public void setIdusuario(Long idusuario) {
		this.idusuario = idusuario;
	}
	
}
