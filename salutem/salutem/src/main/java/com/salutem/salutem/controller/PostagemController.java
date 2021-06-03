package com.salutem.salutem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.salutem.salutem.model.PostagemModel;
import com.salutem.salutem.repository.PostagemRepository;

@RestController
@RequestMapping("/postagem")
public class PostagemController {
		
	@Autowired
	private PostagemRepository repositoryPostagem;
	
	@GetMapping
	ResponseEntity<List<PostagemModel>> getAll(){
		return ResponseEntity.status(200).body(repositoryPostagem.findAll());
	}
	
	@GetMapping("/{id}")
	ResponseEntity<PostagemModel> getById(@PathVariable long id){
		return repositoryPostagem.findById(id).map(ret -> ResponseEntity.status(200).body(ret)).orElse
				(ResponseEntity.notFound().build());
	}
	
	@GetMapping("/titulo/{titulo}")
	ResponseEntity<PostagemModel> getByTitulo (@PathVariable String titulo){
		return repositoryPostagem.findByTituloPostagem(titulo).map(ret -> ResponseEntity.status(200).body(ret))
				.orElse(ResponseEntity.notFound().build());
	}
	
	@PostMapping
	ResponseEntity<PostagemModel> postPostagem (@RequestBody PostagemModel postagem){
		return ResponseEntity.status(201).body(repositoryPostagem.save(postagem));
	}
	
	@PutMapping
	ResponseEntity<PostagemModel> putPostagem (@RequestBody PostagemModel postagem){
		return ResponseEntity.status(200).body(repositoryPostagem.save(postagem));
	}
	
	@DeleteMapping (params = "id")
	ResponseEntity <PostagemModel> deletePostagem (@RequestParam Long id){
		repositoryPostagem.deleteById(id);
		return ResponseEntity.status(200).body(null);
	}
}
