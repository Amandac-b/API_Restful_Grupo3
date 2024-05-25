package org.serratec.backend.redesocial.controller;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.serratec.backend.redesocial.dto.CommentDTO;
import org.serratec.backend.redesocial.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private CommentService service;

    @PostMapping
    public CommentDTO createComment(@RequestBody CommentDTO comment) {
        return service.save(comment);
    }

    @GetMapping
    public List<CommentDTO> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public CommentDTO findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PutMapping
    public CommentDTO update(@RequestBody CommentDTO comment) {
        return service.update(comment);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        service.delete(id);
    }
}