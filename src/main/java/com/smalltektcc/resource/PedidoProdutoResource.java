package com.smalltektcc.resource;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.smalltektcc.model.Pedido;
import com.smalltektcc.model.PedidoProduto;
import com.smalltektcc.repository.PedidoProdutoRepository;
import com.smalltektcc.repository.PedidoRepository;

@RestController
@RequestMapping("/pedido_produto")
public class PedidoProdutoResource {

	@Autowired
	private PedidoProdutoRepository ppRepository;
	
	@Autowired
	private PedidoRepository pedRepository;
	
	@GetMapping("/{id}")
	public ResponseEntity<PedidoProduto> buscarPeloId(@PathVariable Long id) {
		PedidoProduto pedidoProduto = ppRepository.findOne(id);
		return pedidoProduto != null ? ResponseEntity.ok(pedidoProduto) : ResponseEntity.notFound().build();
	}
	
	@GetMapping("/buscaPedidoCarrinho/{status}")
	public Pedido buscarPeloStatus(@PathVariable String status){
		return pedRepository.findByStatus(status);
	}
	
	
	@PostMapping
	public ResponseEntity<PedidoProduto> criar(@Valid @RequestBody PedidoProduto pedidoProduto, HttpServletResponse response) {
		PedidoProduto pedidoProdutoSalvo = ppRepository.save(pedidoProduto);
		return ResponseEntity.status(HttpStatus.CREATED).body(pedidoProdutoSalvo);
	}
	
	@PutMapping("/{id}")
	public PedidoProduto updateNote(@PathVariable(value = "id") Long id, @Valid @RequestBody PedidoProduto pedidoprodutoDetails) {

		PedidoProduto pedidoProduto = ppRepository.findOne(id);
		pedidoProduto.setIdpedido(pedidoprodutoDetails.getIdpedido());
		pedidoProduto.setIdproduto(pedidoprodutoDetails.getIdproduto());
		
		PedidoProduto updatedPedidoProduto = ppRepository.save(pedidoProduto);
		return updatedPedidoProduto;
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteNote(@PathVariable(value = "id") Long id) {
		PedidoProduto pedidoProduto = ppRepository.findOne(id);

		ppRepository.delete(pedidoProduto);

		return ResponseEntity.ok().build();
	}
	
}
