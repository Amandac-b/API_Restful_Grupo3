package org.serratec.backend.redesocial.repository;

import org.serratec.backend.redesocial.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
	
}



