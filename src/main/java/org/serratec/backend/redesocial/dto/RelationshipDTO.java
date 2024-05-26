package org.serratec.backend.redesocial.dto;

import java.time.LocalDate;

import org.serratec.backend.redesocial.model.Relationship;

public class RelationshipDTO {
	private Long followerId; // ID do usuário que está seguindo outro usuário.
	private Long followedId; // ID do usuário que está sendo seguido.
	private LocalDate followedAt; // data e hora em que o usuário começou a seguir o outro usuário.

	public RelationshipDTO() {
		super();
	}

	public RelationshipDTO(Long followerId, Long followedId, LocalDate followedAt) {
		super();
		this.followerId = followerId;
		this.followedId = followedId;
		this.followedAt = followedAt;
	}
	
	public RelationshipDTO(Relationship relationship) {
		super();
		this.followerId = relationship.getRelationshipPK().getFollower().getId();
		this.followedId = relationship.getRelationshipPK().getFollowed().getId();
		this.followedAt = relationship.getCreatedAt();
	}

	public Long getFollowerId() {
		return followerId;
	}

	public void setFollowerId(Long followerId) {
		this.followerId = followerId;
	}

	public Long getFollowedId() {
		return followedId;
	}

	public void setFollowedId(Long followedId) {
		this.followedId = followedId;
	}

	public LocalDate getFollowedAt() {
		return followedAt;
	}

	public void setFollowedAt(LocalDate followedAt) {
		this.followedAt = followedAt;
	}

}
