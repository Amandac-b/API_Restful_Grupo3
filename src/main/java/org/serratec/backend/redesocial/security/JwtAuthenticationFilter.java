package org.serratec.backend.redesocial.security;

import java.io.IOException;
import java.util.ArrayList;

import org.serratec.backend.redesocial.model.User;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

// UsernamePasswordAuthenticationFilter --> Isso significa que este filtro pode ser usado para autenticar usuários baseados em nome de usuário e senha.

public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
	
	
	//authenticationManager é responsável por gerenciar a autenticação
	//jwtUtil é uma utilidade para gerar e manipular tokens JWT.
	
	private AuthenticationManager authenticationManager;
	private JwtUtil jwtUtil;
	
	// construtor da classe, que inicializa authenticationManager e jwtUtil
	
	public JwtAuthenticationFilter(AuthenticationManager authenticationManager, JwtUtil jwtUtil) {
		this.authenticationManager = authenticationManager;
		this.jwtUtil = jwtUtil;
	}
	
	//Este método tenta autenticar o usuário usando ObjectMapper
	//UsernamePasswordAuthenticationToken --> cria um token de autenticação usando o email e a senha do usuário
	//authenticationManager --> faz a autenticão para verificar se o token é valido se não retorna uma mensagem de erro
	
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		try {
			User login = new ObjectMapper().readValue(request.getInputStream(), User.class);
			UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
					login.getEmail(), login.getPassword(), new ArrayList<>());
			Authentication auth = authenticationManager.authenticate(authToken);
			return auth;
		} catch (IOException e) {
			throw new RuntimeException("Falha ao autenticar usuario", e);
		}
	}
	
	//Este método é chamado quando a autenticação é bem-sucedida 
	//Pega o nome de Usuario e retorna após a autenticação bem-sucedida
	//Gera um token usando jwtUtil
	
	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		String username = ((UserDetails) authResult.getPrincipal()).getUsername();
		String token = jwtUtil.generateToken(username);
		response.addHeader(HttpHeaders.AUTHORIZATION, "Bearer " + token);
		response.addHeader(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, "Authorization");
	}
}
