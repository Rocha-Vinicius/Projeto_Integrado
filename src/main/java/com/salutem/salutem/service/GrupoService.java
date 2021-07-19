package com.salutem.salutem.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.salutem.salutem.model.Grupo;
import com.salutem.salutem.model.Usuario;
import com.salutem.salutem.repository.GrupoRepository;
import com.salutem.salutem.repository.UsuarioRepository;

@Service
public class GrupoService {
	@Autowired
	private GrupoRepository repositoryG;
	
	@Autowired
	private UsuarioRepository repositoryU;
	
	
	public ResponseEntity<Grupo> criarGrupo (Grupo novoGrupo, long idUsuario){
		ArrayList<Grupo> grupos = (ArrayList<Grupo>)repositoryG.findAll();
		for(Grupo grupo : grupos) {
			if(novoGrupo.getTemaGrupo().compareToIgnoreCase(grupo.getTemaGrupo()) == 0) {
				return ResponseEntity.status(302).build();
			}
		}
		Usuario usuario = repositoryU.getById(idUsuario);
		novoGrupo.getListaDeUsuarios().add(usuario);
		repositoryG.save(novoGrupo);
		usuario.getListaGrupoUsuario().add(novoGrupo);
		
		return ResponseEntity.status(201).body(novoGrupo);
	}
	
	public Optional<Grupo> alterarGrupo(long idGrupo, Grupo atualizaGrupo){
		Optional<Grupo> grupoExistente = repositoryG.findById(idGrupo);
		
		if(grupoExistente.isPresent()) {
			grupoExistente.get().setCategoriaGrupo(atualizaGrupo.getCategoriaGrupo());
			grupoExistente.get().setDescricaoGrupo(atualizaGrupo.getDescricaoGrupo());
			grupoExistente.get().setTemaGrupo(atualizaGrupo.getTemaGrupo());
			grupoExistente.get().setUrlImagemGrupo(atualizaGrupo.getUrlImagemGrupo());
			return Optional.ofNullable(repositoryG.save(grupoExistente.get()));
		}else {
			return Optional.empty();
		}
	}
	
	public Optional<Grupo> entrarGrupo ( long idGrupo, long idUsuario){
		Optional<Grupo> grupoEscolhido = repositoryG.findById(idGrupo);
		if(grupoEscolhido.get().getListaDeUsuarios()!= null) {
			List<Usuario> listaUsers = grupoEscolhido.get().getListaDeUsuarios();
			for (Usuario user: listaUsers) {
				if(user.getEmailUsuario().compareToIgnoreCase(repositoryU.getById(idUsuario).getEmailUsuario()) == 0) {
					return Optional.empty();
					}
				}
			}
		grupoEscolhido.get().getListaDeUsuarios().add(repositoryU.getById(idUsuario));
		return Optional.ofNullable(repositoryG.save(grupoEscolhido.get()));
	}
	
	public Optional<Grupo> entrarGrupo(Long idGrupo, Long idUsuario){
		Optional<Usuario> usuarioExistente = repositoryU.findById(idUsuario);
		Optional<Grupo> grupoExistente = repositoryG.findById(idGrupo);
		if (grupoExistente.get().getListaDeUsuarios().contains(usuarioExistente.get())) {
			return Optional.empty();
		} else {
			grupoExistente.get().getListaDeUsuarios().add(usuarioExistente.get());
			return Optional.ofNullable(repositoryG.save(grupoExistente.get()));
		}
	}
	
	public void sairGrupo(long idGrupo, long idUsuario) {
		Optional<Grupo> grupoEscolhido = repositoryG.findById(idGrupo);
		if(grupoEscolhido.isPresent()) {
			grupoEscolhido.get().getListaDeUsuarios().remove(repositoryU.getById(idUsuario));
			repositoryG.save(grupoEscolhido.get());
		}
	}
	
	public ResponseEntity<Grupo> sairGrupo(Long idGrupo, Long idUsuario) {
		Optional<Grupo> grupoEscolhido = repositoryG.findById(idGrupo);
		if(grupoEscolhido.isPresent()) {
			grupoEscolhido.get().getListaDeUsuarios().remove(repositoryU.getById(idUsuario));
			repositoryG.save(grupoEscolhido.get());
		}
		return ResponseEntity.status(200).build();
	}
	
	public ResponseEntity<Object> deletarGrupo(long idGrupo){
		Optional<Grupo> grupoExistente = repositoryG.findById(idGrupo);
		if(grupoExistente.isPresent()) {
			repositoryG.deleteById(idGrupo);
			return ResponseEntity.status(200).build();
		}else {
			return ResponseEntity.status(404).build();
		}
	}
}
