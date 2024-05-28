package org.serratec.backend.redesocial.security;

import java.io.IOException;

import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

//A classe JwtAuthorizationFilter estende BasicAuthenticationFilter para autorizar solicitações com base em um token JWT

public class JwtAuthorizationFilter extends BasicAuthenticationFilter {

	//jwtUtil --> faz a manipulação de tokens JWT
	//userDetailsService --> carrega detalhes do Usuario
	
	private JwtUtil jwtUtil;
	private UserDetailsService userDetailsService;

	//Construtor que inicializa os filtros
	
	public JwtAuthorizationFilter(AuthenticationManager authenticationManager, JwtUtil jwtUtil,
			UserDetailsService userDetailsService) {
		super(authenticationManager);
		this.jwtUtil = jwtUtil;
		this.userDetailsService = userDetailsService;
	}

	/*O método doFilterInternal verifica o cabeçalho de autorização da solicitação HTTP.
	  Se o cabeçalho existir e começar com "Bearer ", ele tenta obter a autenticação do token JWT
	  Se a autenticação for bem-sucedida, ela é configurada no contexto de segurança. A cadeia de filtros continua.*/
	
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		String header = request.getHeader(HttpHeaders.AUTHORIZATION);
		if (header != null && header.startsWith("Bearer ")) {
			UsernamePasswordAuthenticationToken auth = getAuthentication(header.substring(7));
			if (auth != null) {
				SecurityContextHolder.getContext().setAuthentication(auth);
			}
		}
		chain.doFilter(request, response);
	}

	//getAuthentication --> valida o token JWT
	/*Se o token for válido, ele obtém o nome de usuário do token e carrega os detalhes do Usuario,
	  retornando UsernamePasswordAuthenticationToken*/
	
	private UsernamePasswordAuthenticationToken getAuthentication(String token) {
		if (jwtUtil.isValidToken(token)) {
			String username = jwtUtil.getUserName(token);
			UserDetails user = userDetailsService.loadUserByUsername(username);
			return new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
		}
		return null;
	}

}
