package org.serratec.backend.redesocial.repository;

import java.util.List;
import java.util.Optional;

import org.serratec.backend.redesocial.model.Relationship;
import org.serratec.backend.redesocial.model.RelationshipPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RelationshipRepository extends JpaRepository<Relationship, RelationshipPK> {

	
	@Query(value = "select * from relationship where id_seguido = :id", nativeQuery=true)
    List<Relationship> findAllFollowersByUserId(Long id);

    @Query(value = "select * from relationship where id_seguidor = :id", nativeQuery=true)
    List<Relationship> findAllFollowingById(Long id);
    
    @Query(value = "select * from relationship where id_seguido = :idSeguido and id_seguidor = :idSeguidor", nativeQuery=true)
    Optional<Relationship> findRelationshipById(Long idSeguido, Long idSeguidor);
    
    @Modifying
    @Query(value = "delete from relationship where id_seguido = :idSeguido and id_seguidor = :idSeguidor", nativeQuery=true)
    void findAndDelete (Long idSeguido, Long idSeguidor);
    
}
