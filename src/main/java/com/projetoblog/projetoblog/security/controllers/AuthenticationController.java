package com.projetoblog.projetoblog.security.controllers;


import com.projetoblog.projetoblog.models.UserModel;
import com.projetoblog.projetoblog.response.Response;
import com.projetoblog.projetoblog.security.utils.JwtTokenUtil;
import com.projetoblog.projetoblog.security.validate.JwtAuthenticationValidate;
import com.projetoblog.projetoblog.security.validate.TokenValidate;
import com.projetoblog.projetoblog.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Optional;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class AuthenticationController {

	private static final Logger log = LoggerFactory.getLogger(AuthenticationController.class);
	private static final String TOKEN_HEADER = "Authorization";
	private static final String BEARER_PREFIX = "Bearer ";

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private UserService cadastroService;



	@GetMapping
	public ResponseEntity<Response<UserModel>> perfil(HttpServletRequest request){

		Optional<String> token = Optional.ofNullable(request.getHeader(TOKEN_HEADER));
		if (token.isPresent() && token.get().startsWith(BEARER_PREFIX)) {
			token = Optional.of(token.get().substring(7));
		}
		Response<UserModel> response = new Response<UserModel>();

		if (!token.isPresent()) {
			response.getErrors().add("Token não informado.");
		} else if (!jwtTokenUtil.tokenValido(token.get())) {
			response.getErrors().add("Token inválido ou expirado.");
		}
		response.setData(cadastroService.findByEmail(jwtTokenUtil.getUsernameFromToken(token.get())));
		return ResponseEntity.ok(response);
	}

	/**
	 * Gera e retorna um novo token JWT.
	 * 
	 * @param authenticationValidate
	 * @param result
	 * @return ResponseEntity<Response<TokenValidate>>
	 * @throws AuthenticationException
	 */
	@PostMapping
	public ResponseEntity<Response<TokenValidate>> gerarTokenJwt(
            @Valid @RequestBody JwtAuthenticationValidate authenticationValidate, BindingResult result)
			throws AuthenticationException {
		Response<TokenValidate> response = new Response<TokenValidate>();

		if (result.hasErrors()) {
			log.error("Erro validando lançamento: {}", result.getAllErrors());
			result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}

		log.info("Gerando token para o email {}.", authenticationValidate.getEmail());
		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
				authenticationValidate.getEmail(), authenticationValidate.getSenha()));
		SecurityContextHolder.getContext().setAuthentication(authentication);

		UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationValidate.getEmail());
		String token = jwtTokenUtil.obterToken(userDetails);
		response.setData(new TokenValidate(token));

		HashMap<String, UserModel> cadsatro = new HashMap<String, UserModel>();
		cadsatro.put("perfil",cadastroService.findByEmail(userDetails.getUsername()));


		return ResponseEntity.ok(response);
	}

	/**
	 * Gera um novo token com uma nova data de expiração.
	 * 
	 * @param request
	 * @return ResponseEntity<Response<TokenValidate>>
	 */
	@PostMapping(value = "/refresh")
	public ResponseEntity<Response<TokenValidate>> gerarRefreshTokenJwt(HttpServletRequest request) {
		log.info("Gerando refresh token JWT.");
		Response<TokenValidate> response = new Response<TokenValidate>();
		Optional<String> token = Optional.ofNullable(request.getHeader(TOKEN_HEADER));
		
		if (token.isPresent() && token.get().startsWith(BEARER_PREFIX)) {
			token = Optional.of(token.get().substring(7));
        }
		
		if (!token.isPresent()) {
			response.getErrors().add("Token não informado.");
		} else if (!jwtTokenUtil.tokenValido(token.get())) {
			response.getErrors().add("Token inválido ou expirado.");
		}
		
		if (!response.getErrors().isEmpty()) { 
			return ResponseEntity.badRequest().body(response);
		}
		
		String refreshedToken = jwtTokenUtil.refreshToken(token.get());
		response.setData(new TokenValidate(refreshedToken));
		return ResponseEntity.ok(response);
	}

}
