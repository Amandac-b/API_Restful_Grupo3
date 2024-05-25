package org.serratec.backend.redesocial.controller;

import java.net.URI;
import java.util.List;

import org.serratec.backend.redesocial.dto.CommentDTO;
import org.serratec.backend.redesocial.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/comment")
public class CommentController {

	@Autowired
	private CommentService commentService;

	@PostMapping
	public ResponseEntity<CommentDTO> createComment(@RequestBody CommentDTO commentDTO) {
		CommentDTO savedComment = commentService.inserir(commentDTO);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedComment.getId())
				.toUri();
		return ResponseEntity.created(uri).body(savedComment);
	}

	@GetMapping
	public ResponseEntity<List<CommentDTO>> findAll() {
		List<CommentDTO> comments = commentService.findAll();
		return ResponseEntity.ok(comments);
	}

	@GetMapping("/{id}")
	public ResponseEntity<CommentDTO> findById(@PathVariable Long id) {
		CommentDTO commentDTO = commentService.findById(id);
		return ResponseEntity.ok(commentDTO);
	}

	@PutMapping("/{id}")
	public ResponseEntity<CommentDTO> update(@PathVariable Long id, @RequestBody CommentDTO commentDTO) {
		CommentDTO updatedComment = commentService.save(id, commentDTO);
		return ResponseEntity.ok(updatedComment);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		commentService.delete(id);
		return ResponseEntity.noContent().build();
	}
}
