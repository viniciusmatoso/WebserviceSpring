package com.smalltektcc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.smalltektcc.model.PedidoProduto;
import com.smalltektcc.model.Produto;

public interface PedidoProdutoRepository extends JpaRepository<PedidoProduto, Long>{	
	//public List<PedidoProduto> findByIdPedido(Long idpedido);
}
