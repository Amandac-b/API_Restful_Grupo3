package org.serratec.backend.redesocial.controller;

import java.net.URI;
import java.util.List;

import org.serratec.backend.redesocial.dto.CommentDTO;
import org.serratec.backend.redesocial.model.Comment;
import org.serratec.backend.redesocial.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
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

@RestController
@RequestMapping("/comments")
public class CommentController {

    @Autowired
    private CommentService commentService;

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

    @PostMapping
    public ResponseEntity<CommentDTO> createNewComment(@RequestBody Comment comment){
        CommentDTO commentDTO = commentService.create(comment);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(commentDTO.getId())
                .toUri();

        return ResponseEntity.created(uri).body(commentDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CommentDTO> editComment(@PathVariable Long id, @RequestBody Comment comment){

        CommentDTO commentDTO = commentService.update(id, comment);
        return ResponseEntity.ok().body(commentDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> findAndDelete(@PathVariable Long id) {

        commentService.deleteItem(id);

        return ResponseEntity.noContent().build();
    }

}
