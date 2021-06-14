package com.salutem.salutem.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.salutem.salutem.model.Grupo;
import com.salutem.salutem.repository.GrupoRepository;

@Service
public class GrupoService {
	@Autowired
	private GrupoRepository repositoryG;
	
	public Optional<Grupo> cadastrarNovoGrupo(Grupo novoGrupo){
		Optional<Grupo> grupoExistente = repositoryG.findByTemaGrupo(novoGrupo.getTemaGrupo());
		if(grupoExistente.isPresent()) {
			return Optional.empty();
		}else {
			return Optional.ofNullable(repositoryG.save(novoGrupo));
		}
	}
	
	public Optional<Grupo> alterarGrupo(Long idGrupo, Grupo atualizaGrupo){
		Optional<Grupo> grupoExistente = repositoryG.findById(idGrupo);
		
		if(grupoExistente.isPresent()) {
			grupoExistente.get().setCategoriaGrupo(atualizaGrupo.getCategoriaGrupo());
			grupoExistente.get().setDescricaoGrupo(atualizaGrupo.getDescricaoGrupo());
			return Optional.ofNullable(repositoryG.save(grupoExistente.get()));
		}else {
			return Optional.empty();
		}
	}
	
	public ResponseEntity<Object> deletarGrupo(Long idGrupo){
		Optional<Grupo> grupoExistente = repositoryG.findById(idGrupo);
		if(grupoExistente.isPresent()) {
			repositoryG.deleteById(idGrupo);
			return ResponseEntity.status(200).body("Item removido");
		}else {
			return ResponseEntity.status(404).build();
		}
	}
}
