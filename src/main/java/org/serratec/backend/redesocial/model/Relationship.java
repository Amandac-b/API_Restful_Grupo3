package org.serratec.backend.redesocial.model;

import java.util.Date;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
public class Relationship {

	@EmbeddedId
	RelationshipPK relationshipPK;

	@Temporal(TemporalType.TIMESTAMP)
	private Date createdAt;

	public Relationship(RelationshipPK relationshipPK, Date createdAt) {
		super();
		this.relationshipPK = relationshipPK;
		this.createdAt = createdAt;
	}

	public RelationshipPK getRelationshipPK() {
		return relationshipPK;
	}

	public void setRelationshipPK(RelationshipPK relationshipPK) {
		this.relationshipPK = relationshipPK;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

}
