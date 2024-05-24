package org.serratec.backend.redesocial.service;

import org.serratec.backend.redesocial.model.Relationship;
import org.serratec.backend.redesocial.repository.RelationshipRepository;
import org.springframework.stereotype.Service;

@Service
public class RelationshipService {
    private final RelationshipRepository relationshipRepository;

    public RelationshipService(RelationshipRepository relationshipRepository) {
        this.relationshipRepository = relationshipRepository;
    }

    public Relationship saveRelationship(Relationship relationship) {
        return relationshipRepository.save(relationship);
    }

}