package com.salutem.salutem.service;

import java.nio.charset.Charset;
import java.util.Optional;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.salutem.salutem.model.Grupo;
import com.salutem.salutem.model.LoginUsuario;
import com.salutem.salutem.model.Usuario;
import com.salutem.salutem.repository.GrupoRepository;
import com.salutem.salutem.repository.UsuarioRepository;

@Service
public class UsuarioService {
	@Autowired
	public GrupoRepository repositoryG;
	
	@Autowired
	public UsuarioRepository repositoryU;
	
	/**
	 * 
	 * @param novoUsuario
	 * @return
	 */
	
	public Optional<Usuario> cadastrarUsuario (Usuario novoUsuario){
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		Optional<Usuario> verificaemailUsuario = repositoryU.findByEmailUsuario(novoUsuario.getEmailUsuario());
		if(verificaemailUsuario.isPresent()) {
			return Optional.empty();
		} else {
			String senhaEncoder = encoder.encode(novoUsuario.getSenhaUsuario());
			novoUsuario.setSenhaUsuario(senhaEncoder);
			return Optional.ofNullable(repositoryU.save(novoUsuario));
		}
	}
	
	
	public Optional<Object> buscarUsuarioPorId(Usuario idUsuario){
		Optional<Usuario> buscaPorId = repositoryU.findById(idUsuario.getIdUsuario());
		if(buscaPorId.isPresent()) {
			return Optional.ofNullable(repositoryU);
		} else {
			return Optional.empty();
		}
	}
	
	public Optional<Usuario> atualizarUsuario(long idUsuario, Usuario atualizacaoUsuario){
		Optional<Usuario> verificaIdUsuario = repositoryU.findById(idUsuario);
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		if(verificaIdUsuario.isPresent()) {
			String senhaEncoder = encoder.encode(atualizacaoUsuario.getSenhaUsuario());
			atualizacaoUsuario.setSenhaUsuario(senhaEncoder);
			verificaIdUsuario.get().setEmailUsuario(atualizacaoUsuario.getEmailUsuario());
			verificaIdUsuario.get().setSenhaUsuario(atualizacaoUsuario.getSenhaUsuario());
			verificaIdUsuario.get().setNomeUsuario(atualizacaoUsuario.getNomeUsuario());
			verificaIdUsuario.get().setUrlImagemUsuario(atualizacaoUsuario.getUrlImagemUsuario());
			return Optional.ofNullable(repositoryU.save(verificaIdUsuario.get()));
		}else {
			return Optional.empty();
		}
	}
	
	public ResponseEntity<Usuario> deletarIdUsuario(long idUsuario){
		Optional<Usuario> verificaIdUsuario = repositoryU.findById(idUsuario);
		if(verificaIdUsuario.isPresent()) {
			repositoryU.deleteById(idUsuario);
			return ResponseEntity.status(200).build();
		}else {
			repositoryU.deleteById(idUsuario);
			return ResponseEntity.status(404).build();
		}
	}
	
	public Optional<LoginUsuario> logarUsuario(Optional<LoginUsuario> usuario){
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		Optional<Usuario> user = repositoryU.findByEmailUsuario(usuario.get().getEmailUsuario());
		if(user.isPresent()) {
			if (encoder.matches(usuario.get().getSenhaUsuario(), user.get().getSenhaUsuario())){
					String auth = usuario.get().getEmailUsuario() + ":" + usuario.get().getSenhaUsuario();
					byte[] encoderAuth = Base64.encodeBase64(auth.getBytes(Charset.forName("US-ASCII")));
					String authHeader = "Basic " + new String(encoderAuth);
					usuario.get().setTokenUsuario(authHeader);
					usuario.get().setNomeUsuario(user.get().getNomeUsuario());
					usuario.get().setEmailUsuario(user.get().getEmailUsuario());
					usuario.get().setIdUsuario(user.get().getIdUsuario());
					usuario.get().setCrmUsuario(user.get().getCrmUsuario());
					usuario.get().setUrlImagemUsuario(user.get().getUrlImagemUsuario());
					return usuario;
				}
		}
		return null;
	}
	
	public ResponseEntity<Grupo> cadastrarGrupo(Grupo novoGrupo, long idCriador){
		Optional<Grupo> grupoExistente = repositoryG.findByTemaGrupo(novoGrupo.getTemaGrupo());
		Optional<Usuario> usuarioExistente = repositoryU.findById(idCriador);
		if(grupoExistente.isPresent()) {
			return ResponseEntity.status(404).build();
		}else {
			Optional.ofNullable(repositoryG.save(novoGrupo));
			usuarioExistente.get().getListaGrupoUsuario().add(novoGrupo);
			repositoryU.save(usuarioExistente.get());
			return ResponseEntity.status(201).body(novoGrupo);
		}
		
	}
	
}
