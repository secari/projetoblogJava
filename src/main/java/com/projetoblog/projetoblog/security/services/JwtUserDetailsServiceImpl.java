package com.projetoblog.projetoblog.security.services;

import com.projetoblog.projetoblog.models.UserModel;
import com.projetoblog.projetoblog.security.JwtUserFactory;
import com.projetoblog.projetoblog.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class JwtUserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserService cadastroService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserModel cadastro = cadastroService.findByEmail(username);

		if (cadastro!=null) {
			return JwtUserFactory.create(cadastro);
		}

		throw new UsernameNotFoundException("Email não encontrado.");
	}

}
