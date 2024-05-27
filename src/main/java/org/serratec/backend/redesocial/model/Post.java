package org.serratec.backend.redesocial.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Post {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	private Long id;
	private String conteudo;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	private LocalDate dataCriacao;

	@OneToMany (mappedBy = "post", cascade = CascadeType.ALL, fetch = FetchType.LAZY )
	@JsonManagedReference 
	private List<Comment> comentarios;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonBackReference 
	@JoinColumn (name = "user_id")
	private User publicador;
	
    public Post() {
	}

	public Post(Long id, String conteudo, LocalDate dataCriacao) {
		super();
		this.id = id;
		this.conteudo = conteudo;
		this.dataCriacao = dataCriacao;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getConteudo() {
		return conteudo;
	}

	public void setConteudo(String conteudo) {
		this.conteudo = conteudo;
	}

	public LocalDate getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(LocalDate dataCriacao) {
		this.dataCriacao = dataCriacao;
	}
	

	
}

	