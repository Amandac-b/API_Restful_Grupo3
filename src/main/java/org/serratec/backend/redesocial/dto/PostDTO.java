package org.serratec.backend.redesocial.dto;

import java.time.LocalDate;

import org.serratec.backend.redesocial.model.Post;

public class PostDTO {
	private Long id;
	private String conteudo;
	private LocalDate dataCriacao;

	public PostDTO() {
	}

	public PostDTO(Long id, String conteudo, LocalDate dataCriacao) {
		this.id = id;
		this.conteudo = conteudo;
		this.dataCriacao = dataCriacao;
	}
	
	public PostDTO(Post post) {
		this.id = post.getId();
		this.conteudo = post.getConteudo();
		this.dataCriacao = post.getDataCriacao();
	}
	
	
	

	// Getters e Setters
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
