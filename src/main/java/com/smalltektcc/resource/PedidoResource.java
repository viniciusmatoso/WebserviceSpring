package com.smalltektcc.resource;

import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
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

import com.smalltektcc.event.RecursoCriadoEvent;
import com.smalltektcc.model.Pedido;
import com.smalltektcc.model.PedidoProduto;
import com.smalltektcc.repository.PedidoProdutoRepository;
import com.smalltektcc.repository.PedidoRepository;


@RestController
@RequestMapping("/pedido")
public class PedidoResource {
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private PedidoProdutoRepository ppRepository;
	
	@GetMapping("/{id}")
	public ResponseEntity<Pedido> buscarPeloId(@PathVariable Long id) {
		Pedido pedido = pedidoRepository.findOne(id);
		return pedido != null ? ResponseEntity.ok(pedido) : ResponseEntity.notFound().build();
	}
	
	@GetMapping("/todos")
	public List<Pedido> buscarTodos() {
		List<Pedido> pedidoList = pedidoRepository.findAll();
		if (pedidoList != null)
			return pedidoList;
		else
			return (List<Pedido>) ResponseEntity.notFound().build();
	}
	
	@PostMapping
	public ResponseEntity<Pedido> criar(@Valid @RequestBody Pedido pedido, HttpServletResponse response) {
		Pedido pedidoSalvo = pedidoRepository.save(pedido);
		return ResponseEntity.status(HttpStatus.CREATED).body(pedidoSalvo);
	}
	
	@PutMapping("/{id}")
	public Pedido updateNote(@PathVariable(value = "id") Long id, @Valid @RequestBody Pedido pedidoDetails) {

		Pedido pedido = pedidoRepository.findOne(id);
		pedido.setStatus(pedidoDetails.getStatus());
		
		pedido.setPedidos_produtos(pedidoDetails.getPedidos_produtos());
		
		Pedido updatedPedido = pedidoRepository.save(pedido);
		
		//criei esse trecho de código abaixo, para apagar os idpedidos nulos, pois quando ele atualiza a lista cria chaves estrangeiras nulas
		List<PedidoProduto> pedidoList = ppRepository.findAll();
		for(int i = 0; i < pedidoList.size(); i++) {
			if(pedidoList.get(i).getIdpedido() == null) {
				ppRepository.delete(pedidoList.get(i));
			}
		}
		
		return updatedPedido;
	}
	
	// Delete a Note
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteNote(@PathVariable(value = "id") Long id) {
		Pedido pedido = pedidoRepository.findOne(id);

		pedidoRepository.delete(pedido);

		return ResponseEntity.ok().build();
	}
}
