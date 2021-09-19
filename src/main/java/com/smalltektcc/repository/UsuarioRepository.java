package com.smalltektcc.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.smalltektcc.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	public Usuario findByEmail(String email);
}




