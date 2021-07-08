package com.salutem.salutem.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.salutem.salutem.model.Grupo;
import com.salutem.salutem.repository.GrupoRepository;
import com.salutem.salutem.service.GrupoService;

@RestController
@CrossOrigin("*")
@RequestMapping("/grupo")
public class GrupoController {
	@Autowired
	private GrupoService services;
	
	@Autowired
	private GrupoRepository repository;
	
	@GetMapping("/todos")
	public ResponseEntity<List<Grupo>> getAll(){
		List<Grupo> listaGrupos = repository.findAll();
		if(listaGrupos.isEmpty()) {
			return ResponseEntity.status(204).build();
		}else {
			return ResponseEntity.status(HttpStatus.CREATED).body(listaGrupos);
		}
	}
	
	@GetMapping("/id/{idGrupo}")
	public ResponseEntity<Optional<Grupo>> getById(@PathVariable(value = "idGrupo") Long idGrupo){
		 Optional<Grupo> grupoExistente = repository.findById(idGrupo);
		 if(grupoExistente.isEmpty()) {
			 return ResponseEntity.status(204).build();
		 }else {
			 return ResponseEntity.status(HttpStatus.ACCEPTED).body(grupoExistente);
		 }
	}
	
	@GetMapping("/pesquisar")
	public ResponseEntity<Optional<List<Grupo>>> getAllByTemaGrupo(@RequestParam String temaGrupo){
		Optional<List<Grupo>> listaGrupos = repository.findAllByTemaGrupoContaining(temaGrupo);
		if(listaGrupos.isEmpty()) {
			return ResponseEntity.status(204).build();
		}else {
			return ResponseEntity.status(HttpStatus.CREATED).body(listaGrupos);
		}
	}
	
	@PutMapping("/alterar/{idGrupo}")
	public ResponseEntity<Grupo> putGrupo(@PathVariable(value = "idGrupo") Long idGrupo, 
			@RequestBody Grupo atualizacaoGrupo){
		return services.alterarGrupo(idGrupo, atualizacaoGrupo)
				.map(grupoAlterado -> ResponseEntity.status(201).body(grupoAlterado))
				.orElse(ResponseEntity.status(204).build());
	}
	
	@DeleteMapping("/deletar/{idGrupo}")
	public ResponseEntity<Object> deleteGrupo(@PathVariable(value = "idGrupo") Long idGrupo){
		return services.deletarGrupo(idGrupo);
	}
}
