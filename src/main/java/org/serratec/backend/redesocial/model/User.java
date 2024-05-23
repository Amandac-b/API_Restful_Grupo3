package org.serratec.backend.redesocial.model;

import java.time.LocalDate;

import io.swagger.annotations.ApiModelProperty;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "usuario")

public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_user")
	private Long id;

	@ApiModelProperty (notes = "Nome do Usuário")
	private String nome;
	@ApiModelProperty (notes = "Sobrenome do Usuário")
	private String sobrenome; 
	@ApiModelProperty (notes = "Email do Usuário")
	private String email;
	@ApiModelProperty (notes = "Senha do Usuário")
	private String senha;
	@ApiModelProperty (notes = "Data de Nascimento do Usuário")
	private LocalDate dataNascimento;

//	@ManyToMany(cascade = CascadeType.ALL)
//	@JoinTable (name = "user_relatioship",
//	joinColumns = @JoinColumn(name = "id_user"),
//	inverseJoinColumns = @JoinColumn (name = "id_relationship"))
//	private List<Relationship> relationship;

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