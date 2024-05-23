package org.serratec.backend.redesocial.controller;

import java.util.List;
import java.util.Optional;

import org.serratec.backend.redesocial.model.Relationship;
import org.serratec.backend.redesocial.repository.RelationshipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/relationships")
public class RelationshipController {

    @Autowired
    private RelationshipRepository relationshipRepository;

    // Listar
    @GetMapping
    public ResponseEntity<List<Relationship>> listar() {
        List<Relationship> relationships = relationshipRepository.findAll();
        return ResponseEntity.ok(relationships);
    }

    // Buscar por ID
    @GetMapping("/{id}")
    public ResponseEntity<Relationship> buscar(@PathVariable Long id) {
        Optional<Relationship> relationshipOpt = relationshipRepository.findById(id);
        return relationshipOpt.map(ResponseEntity::ok)
                              .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Inserir
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Relationship inserir(@Valid @RequestBody Relationship relationship) {
        return relationshipRepository.save(relationship);
    }

    // Atualizar
    @PutMapping("/{id}")
    public ResponseEntity<Relationship> alterar(@PathVariable Long id, @Valid @RequestBody Relationship relationship) {
        if (!relationshipRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        relationship.setId(id);
        Relationship updatedRelationship = relationshipRepository.save(relationship);
        return ResponseEntity.ok(updatedRelationship);
    }

    // Deletar
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        if (!relationshipRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        relationshipRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}