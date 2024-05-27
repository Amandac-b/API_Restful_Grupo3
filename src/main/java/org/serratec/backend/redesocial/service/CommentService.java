package org.serratec.backend.redesocial.service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.serratec.backend.redesocial.dto.CommentDTO;
import org.serratec.backend.redesocial.exception.NotFoundException;
import org.serratec.backend.redesocial.model.Comment;
import org.serratec.backend.redesocial.repository.CommentRepository;
import org.serratec.backend.redesocial.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private PostRepository postRepository;

	public List<CommentDTO> findAll() {
		List<Comment> comments = commentRepository.findAll();
		return comments.stream()
				.map(comment -> new CommentDTO(comment.getId(), comment.getTexto(), comment.getDataCriacao()))
				.collect(Collectors.toList());
	}

    public CommentDTO findById(Long id) {
        Comment comment = commentRepository
                .findById(id).orElseThrow(() ->  new NotFoundException("Comentário não encontrado!"));
        return new CommentDTO(comment.getId(), comment.getTexto(), comment.getDataCriacao());
    }

    public CommentDTO create(Comment comment) {

        if(comment.getPost().getId() != null) {
            postRepository.findById(comment.getPost().getId())
                    .orElseThrow(() -> new NotFoundException("Não existe post para este comentário"));
        }

        comment.setDataCriacao(LocalDate.now());

        return new CommentDTO(commentRepository.save(comment));
    }

    public CommentDTO update(Long id, Comment comment) {
        Comment oldComment = commentRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Não existe comentário com esse id."));

        comment.setId(id);
        comment.setDataCriacao(oldComment.getDataCriacao());
        comment.setPost(oldComment.getPost());
        return new CommentDTO(commentRepository.save(comment));
    }

    public void deleteItem(Long id) {
        commentRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Não existe comentário com esse id."));

        commentRepository.deleteById(id);
    }
}