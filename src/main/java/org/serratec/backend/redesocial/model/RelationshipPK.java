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

	public User getFollower() {
		return follower;
	}

	public void setFollower(User follower) {
		this.follower = follower;
	}

	public User getFollowed() {
		return followed;
	}

	public void setFollowed(User followed) {
		this.followed = followed;
	}

	public RelationshipPK(User follower, User followed) {
		super();
		this.follower = follower;
		this.followed = followed;
	}

	public RelationshipPK() {
	
		super();
	}

}
