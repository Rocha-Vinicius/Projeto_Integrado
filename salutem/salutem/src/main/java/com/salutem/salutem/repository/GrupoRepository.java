package com.salutem.salutem.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.salutem.salutem.model.GrupoModel;

@Repository
public interface GrupoRepository extends JpaRepository<GrupoModel, Long> {
	Optional<GrupoModel> findByTemaGrupo(String nomeGrupo);
	
	Optional<List<GrupoModel>> findAllByTemaGrupoContaining(String nomeGrupo);
	
	Optional<List<GrupoModel>> findAllByTemaGrupo(String nomeGrupo);
}
