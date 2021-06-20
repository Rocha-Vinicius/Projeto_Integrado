package com.salutem.salutem.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.salutem.salutem.model.Grupo;
import com.salutem.salutem.model.Postagem;
import com.salutem.salutem.model.Usuario;
import com.salutem.salutem.repository.GrupoRepository;
import com.salutem.salutem.repository.PostagemRepository;
import com.salutem.salutem.repository.UsuarioRepository;

@Service
public class PostagemService {
	@Autowired
	PostagemRepository repositoryP;
	
	@Autowired
	GrupoRepository repositoryG;
	
	@Autowired
	UsuarioRepository repositoryU;
	
	public ResponseEntity<Postagem> criarPostagem(Postagem novaPostagem, String grupoPostagem, String usuarioPostagem){
		Optional<Grupo> grupoExistente = repositoryG.findByTemaGrupo(grupoPostagem);
		Optional<Usuario> usuarioExistente = repositoryU.findByEmailUsuario(usuarioPostagem);
		if(grupoExistente.isPresent() && usuarioExistente.isPresent()) {
			novaPostagem.setUsuarioPostagem(usuarioExistente.get());
			novaPostagem.setGrupoPostagem(grupoExistente.get());
			repositoryP.save(novaPostagem);
			return ResponseEntity.status(201).body(novaPostagem);
		}else {
			return ResponseEntity.status(204).build();
		}
	}
}
