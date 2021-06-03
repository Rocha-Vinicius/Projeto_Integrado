package com.salutem.salutem.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.salutem.salutem.model.GrupoModel;
import com.salutem.salutem.repository.GrupoRepository;

@Service
public class GrupoService {
	@Autowired
	private GrupoRepository repositoryG;
	
	public Optional<GrupoModel> cadastrarNovoGrupo(GrupoModel novoGrupo){
		Optional<GrupoModel> grupoExistente = repositoryG.findByTemaGrupo(novoGrupo.getTemaGrupo());
		if(grupoExistente.isPresent()) {
			return Optional.empty();
		}else {
			return Optional.ofNullable(repositoryG.save(novoGrupo));
		}
	}
	
	public Optional<GrupoModel> alterarGrupo(Long idGrupo, GrupoModel atualizaGrupo){
		Optional<GrupoModel> grupoExistente = repositoryG.findById(idGrupo);
		
		if(grupoExistente.isPresent()) {
			grupoExistente.get().setCategoriaGrupo(atualizaGrupo.getCategoriaGrupo());
			grupoExistente.get().setDescricaoGrupo(atualizaGrupo.getDescricaoGrupo());
			return Optional.ofNullable(repositoryG.save(grupoExistente.get()));
		}else {
			return Optional.empty();
		}
	}
	
	public ResponseEntity<Object> deletarGrupo(Long idGrupo){
		Optional<GrupoModel> grupoExistente = repositoryG.findById(idGrupo);
		if(grupoExistente.isPresent()) {
			repositoryG.deleteById(idGrupo);
			return ResponseEntity.status(200).body("Item removido");
		}else {
			return ResponseEntity.status(404).build();
		}
	}
}
