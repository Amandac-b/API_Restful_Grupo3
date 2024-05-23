package org.serratec.backend.redesocial.dto;

import java.time.LocalDate;

public class UserDTO {

		private Long id;
		private String nome;
		private String sobrenome;
		private String email;
		private String senha;
		private LocalDate dataNascimento;
		
		public UserDTO() {
		}

		public UserDTO(Long id, String nome, String sobrenome, String email, String senha, LocalDate dataNascimento) {
			super();
			this.id = id;
			this.nome = nome;
			this.sobrenome = sobrenome;
			this.email = email;
			this.senha = senha;
			this.dataNascimento = dataNascimento;
		}

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getNome() {
			return nome;
		}

		public void setNome(String nome) {
			this.nome = nome;
		}

		public String getSobrenome() {
			return sobrenome;
		}

		public void setSobrenome(String sobrenome) {
			this.sobrenome = sobrenome;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public String getSenha() {
			return senha;
		}

		public void setSenha(String senha) {
			this.senha = senha;
		}

		public LocalDate getDataNascimento() {
			return dataNascimento;
		}

		public void setDataNascimento(LocalDate dataNascimento) {
			this.dataNascimento = dataNascimento;
		}
		
		
		
		
}
