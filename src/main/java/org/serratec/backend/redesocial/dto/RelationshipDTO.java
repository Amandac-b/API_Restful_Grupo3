package org.serratec.backend.redesocial.dto;

import java.time.LocalDateTime;

public class RelationshipDTO {
	private Long followerId; // ID do usuário que está seguindo outro usuário.
	private Long followedId; // ID do usuário que está sendo seguido.
	private LocalDateTime followedAt; // data e hora em que o usuário começou a seguir o outro usuário.

	public RelationshipDTO() {
		super();
	}

	public RelationshipDTO(Long followerId, Long followedId, LocalDateTime followedAt) {
		super();
		this.followerId = followerId;
		this.followedId = followedId;
		this.followedAt = followedAt;
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

	public LocalDateTime getFollowedAt() {
		return followedAt;
	}

	public void setFollowedAt(LocalDateTime followedAt) {
		this.followedAt = followedAt;
	}

}
