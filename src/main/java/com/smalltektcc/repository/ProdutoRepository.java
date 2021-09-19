package com.smalltektcc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.smalltektcc.model.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long>{
	public Produto findByNome(String nome);
	public List<Produto> findByIdprodutoIn(List<Long> idsProdutos);
}
