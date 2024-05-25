package org.serratec.backend.redesocial.model;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

public class RelationshipPK {
	@ManyToOne
	@JoinColumn(name = "id_seguidor")
	private User follower;

	@ManyToOne
	@JoinColumn(name = "id_seguido")
	private User followed;

}
