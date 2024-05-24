package org.serratec.backend.redesocial.repository;

import org.serratec.backend.redesocial.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
	
}
