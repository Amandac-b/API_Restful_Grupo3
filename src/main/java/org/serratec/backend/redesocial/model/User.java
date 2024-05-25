package org.serratec.backend.redesocial.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "usuario")

public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_user")
	private Long id;

	private String nome;
	private String sobrenome;
	private String email;
	private String senha;
	private LocalDate dataNascimento;
	@JsonIgnore
	@OneToMany(mappedBy = "relationshipPK.follower", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<Relationship> seguidores = new ArrayList<>();
	@JsonIgnore
	@OneToMany(mappedBy = "relationshipPK.followed", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<Relationship> seguidos = new ArrayList<>();

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