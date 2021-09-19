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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.smalltektcc.event.RecursoCriadoEvent;
import com.smalltektcc.model.Usuario;
import com.smalltektcc.repository.UsuarioRepository;

@RestController
@RequestMapping("/usuario")
public class UsuarioResource {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@GetMapping("/")
	public ResponseEntity<?> listar(){
		
		return ResponseEntity.ok("Home");
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Usuario> buscarPeloId(@PathVariable Long id) {
		Usuario usuario = usuarioRepository.findOne(id);
		return usuario != null ? ResponseEntity.ok(usuario) : ResponseEntity.notFound().build();
	}
	
	@GetMapping("/todos")
	public List<Usuario> buscarTodos() {
		List<Usuario> usuarioList = usuarioRepository.findAll();
		if (usuarioList != null)
			return usuarioList;
		else
			return (List<Usuario>) ResponseEntity.notFound().build();
	}
	
	@PostMapping
	public ResponseEntity<Usuario> criar(@Valid @RequestBody Usuario usuario, HttpServletResponse response) {
		Usuario usuarioSalvo = usuarioRepository.save(usuario);
		publisher.publishEvent(new RecursoCriadoEvent(this, response, usuarioSalvo.getIdusuario()));
		return ResponseEntity.status(HttpStatus.CREATED).body(usuarioSalvo);
	}
	
	
	@PutMapping("/{id}")
	public Usuario updateNote(@PathVariable(value = "id") Long idusuario, @Valid @RequestBody Usuario usuarioDetails) {

		Usuario usuario = usuarioRepository.findOne(idusuario);
		
		usuario.setNome(usuarioDetails.getNome());
		usuario.setCpf(usuarioDetails.getCpf());
		usuario.setSexo(usuarioDetails.getSexo());
		usuario.setIdade(usuarioDetails.getIdade());
		usuario.setEmail(usuarioDetails.getEmail());
		usuario.setSenha(usuarioDetails.getSenha());
		

		Usuario updatedUsuario = usuarioRepository.save(usuario);
		return updatedUsuario;
	}
	
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteNote(@PathVariable(value = "id") Long id) {
		Usuario usuario = usuarioRepository.findOne(id);

		usuarioRepository.delete(usuario);

		return ResponseEntity.ok().build();
	}
}