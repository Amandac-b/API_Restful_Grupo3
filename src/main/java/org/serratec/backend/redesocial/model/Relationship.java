package org.serratec.backend.redesocial.model;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;

@Entity
public class Relationship {

	@EmbeddedId
	RelationshipPK relationshipPK;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	private LocalDate createdAt;

	public Relationship(RelationshipPK relationshipPK, LocalDate createdAt) {
		super();
		this.relationshipPK = relationshipPK;
		this.createdAt = createdAt;
	}

	
	public Relationship() {
		super();
	}


	public RelationshipPK getRelationshipPK() {
		return relationshipPK;
	}

	public void setRelationshipPK(RelationshipPK relationshipPK) {
		this.relationshipPK = relationshipPK;
	}

	public LocalDate getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDate createdAt) {
		this.createdAt = createdAt;
	}

}
