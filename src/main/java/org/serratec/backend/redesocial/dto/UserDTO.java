package org.serratec.backend.redesocial.dto;

import java.time.LocalDate;

import org.serratec.backend.redesocial.model.User;

import com.fasterxml.jackson.annotation.JsonFormat;

public class UserDTO {

		private Long id;
		private String nome;
		private String sobrenome;
		
		@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
		private LocalDate dataNascimento;
		
		public UserDTO() {
		}

		public UserDTO(Long id, String nome, String sobrenome, LocalDate dataNascimento) {
			super();
			this.id = id;
			this.nome = nome;
			this.sobrenome = sobrenome;
			this.dataNascimento = dataNascimento;
		}
		
		public UserDTO(User user) {
			this.id = user.getId();
			this.nome = user.getNome();
			this.sobrenome = user.getSobrenome();
			this.dataNascimento = user.getDataNascimento();
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

		public LocalDate getDataNascimento() {
			return dataNascimento;
		}

		public void setDataNascimento(LocalDate dataNascimento) {
			this.dataNascimento = dataNascimento;
		}
		
		
}