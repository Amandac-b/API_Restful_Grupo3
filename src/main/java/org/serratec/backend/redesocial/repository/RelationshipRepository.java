package org.serratec.backend.redesocial.repository;

import org.serratec.backend.redesocial.model.Relationship;
import org.serratec.backend.redesocial.model.RelationshipPK;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RelationshipRepository extends JpaRepository<Relationship, RelationshipPK> {

}
