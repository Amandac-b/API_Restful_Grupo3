package org.serratec.backend.redesocial.dto;

import java.util.Date;

public class PostDTO {
	private Long id;
	private String conteudo;
	private Date dataCriacao;

	public PostDTO() {
	}

	public PostDTO(Long id, String conteudo, Date dataCriacao) {
		this.id = id;
		this.conteudo = conteudo;
		this.dataCriacao = dataCriacao;
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

	public Date getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(Date dataCriacao) {
		this.dataCriacao = dataCriacao;
	}
}
