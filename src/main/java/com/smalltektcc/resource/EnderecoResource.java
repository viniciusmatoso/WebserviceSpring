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
import com.smalltektcc.model.Endereco;
import com.smalltektcc.model.Usuario;
import com.smalltektcc.repository.EnderecoRepository;
import com.smalltektcc.repository.UsuarioRepository;

@RestController
@RequestMapping("/endereco")
public class EnderecoResource {
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	
	@GetMapping("/{id}")
	public ResponseEntity<Endereco> buscarPeloId(@PathVariable Long id) {
		Endereco endereco = enderecoRepository.findOne(id);
		return endereco != null ? ResponseEntity.ok(endereco) : ResponseEntity.notFound().build();
	}
	
	@GetMapping("/todos")
	public List<Endereco> buscarTodos() {
		List<Endereco> enderecoList = enderecoRepository.findAll();
		if (enderecoList != null)
			return enderecoList;
		else
			return (List<Endereco>) ResponseEntity.notFound().build();
	}
	
	@PostMapping
	public ResponseEntity<Endereco> criar(@Valid @RequestBody Endereco endereco, HttpServletResponse response) {
		Endereco enderecoSalvo = enderecoRepository.save(endereco);
		publisher.publishEvent(new RecursoCriadoEvent(this, response, enderecoSalvo.getIdendereco()));
		return ResponseEntity.status(HttpStatus.CREATED).body(enderecoSalvo);
	}
	
	
	@PutMapping("/{id}")
	public Endereco updateNote(@PathVariable(value = "id") Long idendereco, @Valid @RequestBody Endereco enderecoDetails) {

		Endereco endereco = enderecoRepository.findOne(idendereco);
		
		endereco.setRua(enderecoDetails.getRua());
		endereco.setBairro(enderecoDetails.getBairro());
		endereco.setNumero(enderecoDetails.getNumero());
		endereco.setCep(enderecoDetails.getCep());
		

		Endereco updatedEndereco = enderecoRepository.save(endereco);
		return updatedEndereco;
	}
	
	// Delete a Note
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteNote(@PathVariable(value = "id") Long id) {
		Endereco endereco = enderecoRepository.findOne(id);

		enderecoRepository.delete(endereco);

		return ResponseEntity.ok().build();
	}
}

