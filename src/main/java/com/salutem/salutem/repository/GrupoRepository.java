package com.salutem.salutem.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.salutem.salutem.model.Grupo;

@Repository
public interface GrupoRepository extends JpaRepository<Grupo, Long> {
	Optional<Grupo> findByTemaGrupo(String nomeGrupo);
	
	Optional<List<Grupo>> findAllByTemaGrupoContainingIgnoreCase(String nomeGrupo);
	
	Optional<List<Grupo>> findAllByTemaGrupo(String nomeGrupo);
}
