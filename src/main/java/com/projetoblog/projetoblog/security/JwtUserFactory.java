package com.projetoblog.projetoblog.security;

import com.projetoblog.projetoblog.enums.PerfilEnum;
import com.projetoblog.projetoblog.models.UserModel;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.List;

public class JwtUserFactory {

	private JwtUserFactory() {
	}


	public static JwtUser create(UserModel cadastro) {
		return new JwtUser((long) cadastro.getId(), cadastro.getEmail(), cadastro.getPassword(),
				mapToGrantedAuthorities(PerfilEnum.ROLE_ADMIN));
	}


	private static List<GrantedAuthority> mapToGrantedAuthorities(PerfilEnum perfilEnum) {
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		authorities.add(new SimpleGrantedAuthority(perfilEnum.toString()));
		return authorities;
	}

}
