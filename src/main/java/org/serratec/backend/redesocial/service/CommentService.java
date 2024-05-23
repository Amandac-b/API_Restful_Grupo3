package org.serratec.backend.redesocial.service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.serratec.backend.redesocial.dto.CommentDTO;
import org.serratec.backend.redesocial.model.Comment;
import org.serratec.backend.redesocial.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentService {
	@Autowired
	private CommentRepository commentRepository;

	public List<CommentDTO> findAll() {
		List<Comment> comments = commentRepository.findAll();
		return comments.stream()
				.map(comment -> new CommentDTO(comment.getId(), comment.getTexto(), comment.getDataCriacao()))
				.collect(Collectors.toList());
	}

	public CommentDTO findById(Long id) {
		Comment comment = commentRepository.findById(id).orElseThrow(() -> new RuntimeException("Comment not found"));
		return new CommentDTO(comment.getId(), comment.getTexto(), comment.getDataCriacao());
	}

	public CommentDTO save(CommentDTO commentDTO) {
		try {
			Comment comment = new Comment(null, commentDTO.getTexto(), LocalDate.now());
			comment = commentRepository.save(comment);
			return new CommentDTO(comment.getId(), comment.getTexto(), comment.getDataCriacao());
		} catch (Exception e) {
			throw new RuntimeException("Error in Creating Comment");
		}
	}

	public void delete(Long id) {
		commentRepository.deleteById(id);
	}
}