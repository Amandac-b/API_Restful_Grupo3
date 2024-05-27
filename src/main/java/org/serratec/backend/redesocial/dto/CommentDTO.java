package org.serratec.backend.redesocial.dto;

import java.time.LocalDate;

import org.serratec.backend.redesocial.model.Comment;

public class CommentDTO {

	private Long id;
	private String texto;
	private LocalDate dataCriacao;

	public CommentDTO() {
		super();
	}

	public CommentDTO(Long id, String texto, LocalDate dataCriacao) {
		super();
		this.id = id;
		this.texto = texto;
		this.dataCriacao = dataCriacao;
	}
	
	public CommentDTO(Comment comment) {
		super();
		this.id = comment.getId();
		this.texto = comment.getTexto();
		this.dataCriacao = comment.getDataCriacao();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public LocalDate getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(LocalDate dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

}