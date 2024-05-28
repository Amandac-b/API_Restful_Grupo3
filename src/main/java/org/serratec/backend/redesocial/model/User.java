package org.serratec.backend.redesocial.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import org.serratec.backend.redesocial.dto.UserInserirDTO;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

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

public class User implements UserDetails, Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_user")
	private Long id;

	private String nome;
	private String sobrenome;
	private String email;
	private String senha;
	private List<String> rules;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	private LocalDate dataNascimento;

	@JsonIgnore
	@OneToMany(mappedBy = "relationshipPK.follower", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Relationship> seguidores = new ArrayList<>();

	@JsonIgnore
	@OneToMany(mappedBy = "relationshipPK.followed", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Relationship> seguidos = new ArrayList<>();

	@JsonManagedReference
	@OneToMany (mappedBy = "publicador", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Collection<Post> posts;

	public User(UserInserirDTO userInserirDTO) {
		super();
		this.nome = userInserirDTO.getNome();
		this.sobrenome = userInserirDTO.getSobrenome();
		this.email = userInserirDTO.getEmail();
		this.dataNascimento = userInserirDTO.getDataNascimento();
		this.senha = userInserirDTO.getSenha();
		this.rules = List.of("ROLE_POST", "ROLE_USER", "ROLE_COMMENT");

	}

	public User() {
	}

	public User(Long id, String nome, String sobrenome, String email, String senha, LocalDate dataNascimento) {
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

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> authorities = new ArrayList<>();
		for(String rule : rules) {
			authorities.add(new SimpleGrantedAuthority(rule));
		}
		return authorities;
	}

	@Override
	public String getPassword() {
		return senha;
	}

	@Override
	public String getUsername() {
		return email;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
}