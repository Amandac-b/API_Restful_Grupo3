package org.serratec.backend.redesocial.config;

import java.util.Arrays;

import org.serratec.backend.redesocial.security.JwtAuthenticationFilter;
import org.serratec.backend.redesocial.security.JwtAuthorizationFilter;
import org.serratec.backend.redesocial.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
public class ConfigSeguranca {

	@Autowired
	UserDetailsService userDetailsService;

	@Autowired
	JwtUtil jwtUtil;

	@Bean
	 SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.csrf(csrf -> csrf.disable())
		.cors((cors) -> cors.configurationSource(corsConfigurationSource()))
		.httpBasic(Customizer.withDefaults()).authorizeHttpRequests(requests -> {
			requests.requestMatchers(HttpMethod.POST, "/login").permitAll(); 
			requests.requestMatchers(HttpMethod.POST, "/users").permitAll();
			requests.requestMatchers(HttpMethod.GET, "/swagger-ui/**", "/v3/api-docs/**").permitAll();
			requests.requestMatchers(HttpMethod.POST, "/posts").hasRole("POST");
			requests.requestMatchers(HttpMethod.POST, "/users").hasRole("USER");
			requests.requestMatchers(HttpMethod.POST, "/comments").hasRole("COMMENT")
				.anyRequest().authenticated();
		}).sessionManagement(session -> {
			session.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		});
		
		JwtAuthenticationFilter jwtAuthenticationFilter = new JwtAuthenticationFilter(
				authenticationManager(http.getSharedObject(AuthenticationConfiguration.class)), jwtUtil);
		jwtAuthenticationFilter.setFilterProcessesUrl("/login");
		
		http.addFilter(jwtAuthenticationFilter);
		http.addFilter(new JwtAuthorizationFilter(
				authenticationManager(http.getSharedObject(AuthenticationConfiguration.class)), jwtUtil, userDetailsService));
		
		return http.build();
	}

	@Bean
	CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration corsConfiguration = new CorsConfiguration();
		corsConfiguration.setAllowedOrigins(Arrays.asList("http://localhost:3000/"));
		corsConfiguration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS", "HEAD"));

		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", corsConfiguration.applyPermitDefaultValues());

		return source;
	}

	@Bean
	AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
			throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}

	@Bean
	BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

}