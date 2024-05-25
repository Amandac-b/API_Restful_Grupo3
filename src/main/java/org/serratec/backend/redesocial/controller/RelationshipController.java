package org.serratec.backend.redesocial.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/relationships")
public class RelationshipController {
//
//	@Autowired
//	RelationshipService relationshipService;
//
//	// Listar
//	@GetMapping
//	public ResponseEntity<List<Relationship>> listar() {
//		List<Relationship> relationships = relationshipService.findAll();
//		return ResponseEntity.ok(relationships);
//	}
//
//	// Buscar por ID
//	@GetMapping("/{id}")
//	public ResponseEntity<Relationship> buscar(@PathVariable Long id) {
//		Optional<Relationship> relationshipOpt = relationshipService.findById(id);
//		return relationshipOpt.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
//	}
//
//	// Inserir
//	@PostMapping
//	@ResponseStatus(HttpStatus.CREATED)
//	public Relationship inserir(@Valid @RequestBody Relationship relationship) {
//		return relationshipService.save(relationship);
//	}
//
//	// Atualizar
//	@PutMapping("/{id}")
//	public ResponseEntity<Relationship> alterar(@PathVariable Long id, @Valid @RequestBody Relationship relationship) {
//		if (!relationshipService.existsById(id)) {
//			return ResponseEntity.notFound().build();
//		}
//		relationship.setId(id);
//		Relationship updatedRelationship = relationshipService.save(relationship);
//		return ResponseEntity.ok(updatedRelationship);
//	}
//
//	// Deletar
//	@DeleteMapping("/{id}")
//	public ResponseEntity<Void> deletar(@PathVariable Long id) {
//		if (!relationshipService.existsById(id)) {
//			return ResponseEntity.notFound().build();
//		}
//		relationshipService.deleteById(id);
//		return ResponseEntity.noContent().build();
//	}
//
//	
//	
}