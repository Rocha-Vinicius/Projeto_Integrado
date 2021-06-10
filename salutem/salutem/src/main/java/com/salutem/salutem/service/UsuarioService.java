package com.salutem.salutem.service;

import java.nio.charset.Charset;
import java.util.Optional;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.salutem.salutem.model.LoginUsuario;
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
			verificaIdUsuario.get().setEmailUsuario(atualizacaoUsuario.getEmailUsuario());
			verificaIdUsuario.get().setSenhaUsuario(atualizacaoUsuario.getSenhaUsuario());
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
	
	public Optional<LoginUsuario> logarUsuario(Optional<LoginUsuario> usuario){
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		Optional<Usuario> user = repositoryU.findByEmailUsuario(usuario.get().getEmailUsuario());
		if(user.isPresent()) {
			if (encoder.matches(user.get().getSenhaUsuario(), usuario.get().getSenhaUsuario())){
					String auth = usuario.get().getEmailUsuario() + ":" + usuario.get().getSenhaUsuario();
					byte[] encoderAuth = Base64.encodeBase64(auth.getBytes(Charset.forName("US-ASCII")));
					String authHeader = "Basic " + new String(encoderAuth);
					usuario.get().setTokenUsuario(authHeader);
					usuario.get().setEmailUsuario(user.get().getEmailUsuario());
					return usuario;
				}
		}
		return null;
		
		
	}
	
}
