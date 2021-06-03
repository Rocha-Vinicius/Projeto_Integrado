package com.salutem.salutem.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.salutem.salutem.model.Usuario;
import com.salutem.salutem.repository.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	public UsuarioRepository repositoryU;
	
	/**
	 * 
	 * @param novoUsuario
	 * @return
	 */
	
	public Optional<Object> cadastrarUsuario (Usuario novoUsuario){
		Optional<Object> verificaemailUsuario = repositoryU.findByEmailUsuario(novoUsuario.getEmailUsuario());
		if(verificaemailUsuario.isPresent()) {
			return Optional.empty();
		} else {
			return Optional.ofNullable(repositoryU.save(novoUsuario));
		}
	}
	
	
	public Optional<Object> buscarUsuarioPorId(Usuario id_Usuario){
		Optional<Usuario> buscaPorId = repositoryU.findById(id_Usuario.getId_Usuario());
		if(buscaPorId.isPresent()) {
			return Optional.ofNullable(repositoryU);
		} else {
			return Optional.empty();
		}
	}
	
	public Optional<Object> atualizarUsuario(Long id_Usuario, Usuario atualizacaoUsuario){
		Optional<Usuario> verificaIdUsuario = repositoryU.findById(id_Usuario);
		if(verificaIdUsuario.isPresent()) {
			verificaIdUsuario.get().setId_Usuario(atualizacaoUsuario.getId_Usuario());
			return Optional.ofNullable(repositoryU.save(verificaIdUsuario.get()));
		}else {
			return Optional.empty();
		}
	}
	
	public Optional<Object> deletarIdUsuario(Long id_Usuario){
		Optional<Usuario> verificaIdUsuario = repositoryU.findById(id_Usuario);
		if(verificaIdUsuario.isEmpty()) {
			return Optional.ofNullable(verificaIdUsuario);
		}else {
			repositoryU.deleteById(id_Usuario);
			return Optional.empty();
		}
	}
}
