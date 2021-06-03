package com.salutem.salutem.controller;

import java.util.List;
import java.util.Optional;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.salutem.salutem.model.GrupoModel;
import com.salutem.salutem.repository.GrupoRepository;
import com.salutem.salutem.service.GrupoService;

@RestController
@RequestMapping("/grupo")
public class GrupoController {
	@Autowired
	private GrupoService services;
	
	@Autowired
	private GrupoRepository repository;
	
	@GetMapping("/todos")
	public ResponseEntity<List<GrupoModel>> getAll(){
		List<GrupoModel> listaGrupos = repository.findAll();
		if(listaGrupos.isEmpty()) {
			return ResponseEntity.status(204).build();
		}else {
			return ResponseEntity.status(HttpStatus.CREATED).body(listaGrupos);
		}
	}
	
	@GetMapping("/id/{id_grupo}")
	public ResponseEntity<Optional<GrupoModel>> getById(@PathVariable(value = "id_grupo") Long idGrupo){
		 Optional<GrupoModel> grupoExistente = repository.findById(idGrupo);
		 if(grupoExistente.isEmpty()) {
			 return ResponseEntity.status(204).build();
		 }else {
			 return ResponseEntity.status(HttpStatus.ACCEPTED).body(grupoExistente);
		 }
	}
	
	@GetMapping("/pesquisar")
	public ResponseEntity<Optional<List<GrupoModel>>> getAllByTemaGrupo(@RequestParam String temaGrupo){
		Optional<List<GrupoModel>> listaGrupos = repository.findAllByTemaGrupoContaining(temaGrupo);
		if(listaGrupos.isEmpty()) {
			return ResponseEntity.status(204).build();
		}else {
			return ResponseEntity.status(HttpStatus.CREATED).body(listaGrupos);
		}
	}
	
	@PostMapping("/cadastrar")
	public ResponseEntity<GrupoModel> postGrupo(@RequestBody GrupoModel novoGrupo){
		return services.cadastrarNovoGrupo(novoGrupo)
				.map(grupoCriado -> ResponseEntity.status(201).body(grupoCriado))
				.orElse(ResponseEntity.status(208).build());
	}
	
	@PutMapping("/alterar/{id_grupo}")
	public ResponseEntity<GrupoModel> putGrupo(@PathVariable(value = "id_grupo") Long idGrupo, 
			@RequestBody GrupoModel atualizacaoGrupo){
		return services.alterarGrupo(idGrupo, atualizacaoGrupo)
				.map(grupoAlterado -> ResponseEntity.status(201).body(grupoAlterado))
				.orElse(ResponseEntity.status(204).build());
	}
	
	@DeleteMapping("/deletar/{id_grupo}")
	public ResponseEntity<Object> deleteGrupo(@PathVariable(value = "id_grupo") Long idGrupo){
		return services.deletarGrupo(idGrupo);
	}
}
