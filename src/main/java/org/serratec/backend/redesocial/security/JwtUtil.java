package org.serratec.backend.redesocial.security;

import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;


//@Component, tornando-a um componente Spring gerenciado pelo contêiner de injeção de dependência

@Component
public class JwtUtil {

	//jwtSecret e jwtExpirationMiliseg --> são preenchidas com valores configurados nas propriedades da aplicação como JWT e o tempo de expiração
	
	@Value("${auth.jwt-secret}")
	private String jwtSecret;
	
	@Value("${auth.jwt-expiration-miliseg}")
	private Long jwtExpirationMiliseg;
	
	
	/*generateToken --> cria um token JWT e gera uma chave secreta a partir do jwtSecret que define o nome do
	Usuario como assunto do token, define a data de expiração e assina o token com a chave secreta*/
	
	
	public String generateToken(String username) {
		SecretKey secreKeySpec = Keys.hmacShaKeyFor(jwtSecret.getBytes());
		return Jwts.builder().setSubject(username)
				.setExpiration(new Date(System.currentTimeMillis() + this.jwtExpirationMiliseg))
				.signWith(secreKeySpec)
				.compact();
	}
	
	//isValidToken --> valida um token JWT e verifica se o nome de usuário e a data de expiração são válidos
	
	public boolean isValidToken(String token) {
		Claims claims = getClaims(token);
		if (claims != null) {
			String username = claims.getSubject();
			Date expirationDate = claims.getExpiration();
			Date now = new Date(System.currentTimeMillis());
			if (username != null && expirationDate != null && now.before(expirationDate)) {
				return true;
			}
		}
		return false;
	}
	
	//getUserName retorna o nome de usuário contido no token JWT, obtendo as reivindicações do token e retorna o assunto
	
	public String getUserName(String token) {
		Claims claims = getClaims(token);
		if (claims != null) {
			return claims.getSubject();
		}
		return null;
	}
	
	//getClaims --> extrai e retorna as reivindicações de um token JWT, analisando e validando o token usando o segredo configurado
	
	public Claims getClaims(String token) {
		return Jwts.parserBuilder()
				.setSigningKey(jwtSecret.getBytes())
				.build()
				.parseClaimsJws(token)
				.getBody();
	}
	
}
