package com.salutem.salutem.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.salutem.salutem.model.Usuario;
import com.salutem.salutem.repository.UsuarioRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{

	@Autowired
	private UsuarioRepository repositoryU;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<Usuario> user = repositoryU.findByEmailUsuario(username);
		user.orElseThrow(() -> new UsernameNotFoundException(username + "não encontrado"));
		
		return user.map(UserDetailsImpl::new).get();
	}
	

}
