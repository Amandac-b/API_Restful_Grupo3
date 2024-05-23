package org.serratec.backend.redesocial.model;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
public class Relationship {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@ManyToOne
	private User follower;

	@ManyToOne
	private User followed;

	@Temporal(TemporalType.TIMESTAMP)
	private Date createdAt;

	public Relationship(Long id, User follower, User followed, Date createdAt) {
		super();
		this.id = id;
		this.follower = follower;
		this.followed = followed;
		this.createdAt = createdAt;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(java.util.Date date) {
		this.createdAt = date;
	}

}
