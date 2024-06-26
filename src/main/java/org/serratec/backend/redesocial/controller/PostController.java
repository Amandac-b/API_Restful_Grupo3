package org.serratec.backend.redesocial.controller;

import java.net.URI;
import java.util.List;

import org.serratec.backend.redesocial.dto.PostDTO;
import org.serratec.backend.redesocial.model.Post;
import org.serratec.backend.redesocial.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/posts")
public class PostController {
    
	@Autowired
    private final PostService postService;
    
    private PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping
    public ResponseEntity<List<PostDTO>> getAllPosts() {
        List<PostDTO> posts = postService.findAll();
        return ResponseEntity.ok(posts);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostDTO> getPostById(@PathVariable Long id) {
        PostDTO post = postService.findById(id);
        return ResponseEntity.ok(post);
    }

    @PostMapping
    public ResponseEntity<PostDTO> createPost (@Valid @RequestBody Post post) {
        PostDTO postDTO  = postService.createPost(post);
        
        URI uri = ServletUriComponentsBuilder
        		.fromCurrentRequest()
        		.path("/{id}")
        		.buildAndExpand(postDTO.getId())
				.toUri();
        return ResponseEntity.created(uri).body(postDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable Long id) {
        postService.findAndDelete(id);
        
        return ResponseEntity.noContent().build();
    }
    
    @PutMapping("/{id}")
	public ResponseEntity<PostDTO> alterar(@PathVariable Long id, @Valid @RequestBody Post postAtualizado) {
		PostDTO postDTO = postService.save(id, postAtualizado);
		
		return ResponseEntity.ok(postDTO);
		
	}
}

	
