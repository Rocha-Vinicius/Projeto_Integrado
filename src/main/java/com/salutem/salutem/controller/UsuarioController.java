package com.salutem.salutem.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.salutem.salutem.model.Grupo;
import com.salutem.salutem.model.LoginUsuario;
import com.salutem.salutem.model.Usuario;
import com.salutem.salutem.repository.UsuarioRepository;
import com.salutem.salutem.service.UsuarioService;

@RestController
@CrossOrigin("*")
@RequestMapping("/usuario")
public class UsuarioController {
	
	@Autowired
	private UsuarioRepository repositoryU;
	
	@Autowired
	private UsuarioService serviceU;
	
	@GetMapping("/{id}")
	public ResponseEntity<Usuario> getById (@PathVariable long id){
		return repositoryU.findById(id).map(resp -> ResponseEntity.status(200).body(resp))
				.orElse(ResponseEntity.status(404).build());
	}
	
	@PostMapping("/cadastrar")
	 public ResponseEntity<Usuario> cadastrarUsuario (@Valid @RequestBody Usuario emailUsuario){
		return serviceU.cadastrarUsuario(emailUsuario)
				.map(verificaemailUsuario -> ResponseEntity.status(201).body(verificaemailUsuario))
				.orElse(ResponseEntity.status(400).build());
	}
	
	@PostMapping("/logar")
	 public ResponseEntity<LoginUsuario> logarUsuario (@Valid @RequestBody Optional<LoginUsuario> loginUsuario){
		return serviceU.logarUsuario(loginUsuario)
				.map(verificarLoginUsuario -> ResponseEntity.status(201).body(verificarLoginUsuario))
				.orElse(ResponseEntity.status(400).build());
	}
	
	@PostMapping("/cadastrar/grupo/{idUsuario}")
	public ResponseEntity<Grupo> criarGrupo(@Valid @RequestBody Grupo novoGrupo, @PathVariable long idUsuario){
		return serviceU.cadastrarGrupo(novoGrupo, idUsuario);
	}
	
	@GetMapping
	public ResponseEntity<List<Usuario>> pegarTodasCategorias(){
		List<Usuario> listaDeUsuario = repositoryU.findAll();

		if(listaDeUsuario.isEmpty()) {
			return ResponseEntity.status(204).build();
		}else {
			return ResponseEntity.status(200).body(listaDeUsuario) ;
		}
		
	}
	
	@GetMapping("/buscar/{nomeUsuario}")
	public ResponseEntity<Object> buscarUsuarioPorNome (@PathVariable String nomeUsuario ){
		Optional<Object> listaDeUsuario = repositoryU.findByNomeUsuario(nomeUsuario);
		if(listaDeUsuario.isEmpty()) {
			return ResponseEntity.status(400).body("Usuario Inexistente");
		}else {
			return ResponseEntity.status(200).body(listaDeUsuario);
		}
	}
	
	@PutMapping("/atualizar/{idUsuario}")
	public ResponseEntity<Usuario> atualizarUsuario (@PathVariable long idUsuario, 
			@Valid @RequestBody Usuario atualizacaoUsuario){
		return serviceU.atualizarUsuario(idUsuario, atualizacaoUsuario)
				.map(usuarioAtualizado -> ResponseEntity.status(201).body(usuarioAtualizado))
				.orElse(ResponseEntity.status(400).build());
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Usuario> deletarUsuario (@PathVariable long id){
		return serviceU.deletarIdUsuario(id);
	}

}
