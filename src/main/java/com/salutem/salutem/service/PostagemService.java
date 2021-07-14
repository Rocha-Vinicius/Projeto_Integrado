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
	
	public ResponseEntity<Postagem> criarPostagem(Postagem novaPostagem, long idGrupo, long idUsuario){
		Optional<Grupo> grupoExistente = repositoryG.findById(idGrupo);
		Optional<Usuario> usuarioExistente = repositoryU.findById(idUsuario);
		if(grupoExistente.isPresent() && usuarioExistente.isPresent()) {
			novaPostagem.setUsuarioPostagem(usuarioExistente.get());
			novaPostagem.setGrupoPostagem(grupoExistente.get());
			repositoryP.save(novaPostagem);
			return ResponseEntity.status(201).body(novaPostagem);
		}else {
			return ResponseEntity.status(204).build();
		}
	}
	
	public ResponseEntity<Postagem> deletarPostagem(long id){
		Optional<Postagem> verificaPostagem = repositoryP.findById(id);
		if(verificaPostagem.isPresent()) {
			repositoryP.deleteById(id);
			return ResponseEntity.status(200).build();
		}else {
			return ResponseEntity.status(404).build();
		}
	}
	
	public Optional<Postagem> atualizarPostagem(long idPostagem, Postagem postagemAtualizada){
		Optional<Postagem> postagemExistente = repositoryP.findById(idPostagem);
		
		if(postagemExistente.isPresent()) {
			postagemExistente.get().setTituloPostagem(postagemAtualizada.getTituloPostagem());
			postagemExistente.get().setDescricaoPostagem(postagemAtualizada.getDescricaoPostagem());
			postagemExistente.get().setEspecialidadePostagem(postagemAtualizada.getEspecialidadePostagem());
			postagemExistente.get().setUrlImagemPostagem(postagemAtualizada.getUrlImagemPostagem());
			postagemExistente.get().setUsuarioPostagem(postagemAtualizada.getUsuarioPostagem());
			return Optional.ofNullable(repositoryP.save(postagemExistente.get()));
		}else {
			return Optional.empty();
		}
	}
}
