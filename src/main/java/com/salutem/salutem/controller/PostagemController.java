package com.salutem.salutem.controller;

import java.util.List;

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

import com.salutem.salutem.model.Postagem;
import com.salutem.salutem.repository.PostagemRepository;
import com.salutem.salutem.service.PostagemService;

@RestController
@CrossOrigin("*")
@RequestMapping("/postagem")
public class PostagemController {
		
	@Autowired
	private PostagemRepository repositoryPostagem;
	
	@Autowired
	private PostagemService servicesPostagem;
	
	@GetMapping
	ResponseEntity<List<Postagem>> getAll(){
		return ResponseEntity.status(200).body(repositoryPostagem.findAll());
	}
	
	@GetMapping("/{id}")
	ResponseEntity<Postagem> getById(@PathVariable long id){
		return repositoryPostagem.findById(id).map(ret -> ResponseEntity.status(200).body(ret)).orElse
				(ResponseEntity.notFound().build());
	}
	
	@GetMapping("/titulo/{titulo}")
	ResponseEntity<Postagem> getByTitulo (@PathVariable String titulo){
		return repositoryPostagem.findByTituloPostagem(titulo).map(ret -> ResponseEntity.status(200).body(ret))
				.orElse(ResponseEntity.notFound().build());
	}
	
	@PostMapping ("/{idGrupo}/{idUsuario}")
	ResponseEntity<Postagem> postPostagem (@RequestBody Postagem postagem, 
			@PathVariable long idGrupo, @PathVariable long idUsuario ){
		return servicesPostagem.criarPostagem(postagem, idGrupo, idUsuario);
	}
	
	@PutMapping("/{id}")
	ResponseEntity<Postagem> putPostagem (@PathVariable long id,@RequestBody Postagem postagem){
		return servicesPostagem.atualizarPostagem(id, postagem).map(resp-> ResponseEntity.status(200).body(resp))
				.orElse(ResponseEntity.status(400).build());
	}
	
	@DeleteMapping ("/apagar/{id}")
	ResponseEntity <Postagem> deletePostagem (@PathVariable long id){
		return servicesPostagem.deletarPostagem(id);
	}
}
