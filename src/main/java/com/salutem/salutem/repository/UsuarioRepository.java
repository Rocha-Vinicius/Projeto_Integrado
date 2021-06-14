package com.salutem.salutem.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.salutem.salutem.model.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

	public List<Object> findAllByNomeUsuarioContaining(String nomeUsuario);
	
	public List<Object> findAllByEmailUsuarioContaining(String emailUsuario);
	
	
	
	public Optional<Object> findByNomeUsuario(String nomeUsuario);
	
	public Optional<Usuario> findByEmailUsuario(String emailUsuario);
}

