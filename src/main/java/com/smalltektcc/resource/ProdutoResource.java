package com.smalltektcc.resource;

import java.util.List;

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
import com.smalltektcc.model.Produto;
import com.smalltektcc.model.Usuario;
import com.smalltektcc.repository.ProdutoRepository;

@RestController
@RequestMapping("/produto")
public class ProdutoResource {
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@GetMapping
	public ResponseEntity<?> listar(){
		return ResponseEntity.ok("Teste");
	}
	
	@GetMapping("/buscar/{nome}")
	public Produto buscarPeloNome(@PathVariable String nome) {
		return produtoRepository.findByNome(nome);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Produto> buscarPeloId(@PathVariable Long id) {
		Produto produto = produtoRepository.findOne(id);
		return produto != null ? ResponseEntity.ok(produto) : ResponseEntity.notFound().build();
	}
	
	@GetMapping("/todos")
	public List<Produto> buscarTodos() {
		List<Produto> produtoList = produtoRepository.findAll();
		if (produtoList != null)
			return produtoList;
		else
			return (List<Produto>) ResponseEntity.notFound().build();
	}
	
	@PostMapping
	public ResponseEntity<Produto> criar(@Valid @RequestBody Produto produto, HttpServletResponse response) {
		Produto produtoSalvo = produtoRepository.save(produto);
		publisher.publishEvent(new RecursoCriadoEvent(this, response, produtoSalvo.getIdproduto()));
		return ResponseEntity.status(HttpStatus.CREATED).body(produtoSalvo);
	}
	
	// Update a Note
	@PutMapping("/{id}")
	public Produto updateNote(@PathVariable(value = "id") Long idproduto, @Valid @RequestBody Produto produtoDetails) {

		Produto produto = produtoRepository.findOne(idproduto);
		
		produto.setNome(produtoDetails.getNome());
		produto.setDescricao(produtoDetails.getDescricao());
		produto.setQuantidade(produtoDetails.getQuantidade());
		produto.setPreco(produtoDetails.getPreco());
		//produto.setIdpedido(produtoDetails.getIdpedido());
		
		
		Produto updatedProduto = produtoRepository.save(produto);
		return updatedProduto;
	}
	
	// Delete a Note
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteNote(@PathVariable(value = "id") Long idproduto) {
		Produto produto = produtoRepository.findOne(idproduto);

		produtoRepository.delete(produto);

		return ResponseEntity.ok().build();
	}
}
