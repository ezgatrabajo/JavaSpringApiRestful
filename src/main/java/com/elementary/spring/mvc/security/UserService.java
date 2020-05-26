package com.elementary.spring.mvc.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.elementary.spring.mvc.model.Usuario;
import com.elementary.spring.mvc.repository.UsuarioRepository;

@Service
public class UserService implements UserDetailsService{


	private UsuarioRepository repo;

	public UserService(UsuarioRepository userRepository) {
		this.repo = userRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario u = repo.findByUsername(username);
		UserPrincipal up = new UserPrincipal(u);
		return up;
	}


}
