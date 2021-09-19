package com.smalltektcc.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.smalltektcc.model.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Long>{
	public Pedido findByStatus(String status);
}
