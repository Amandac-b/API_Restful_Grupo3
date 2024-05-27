package org.serratec.backend.redesocial.repository;

import org.serratec.backend.redesocial.model.Relationship;
import org.serratec.backend.redesocial.model.RelationshipPK;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RelationshipRepository extends JpaRepository<Relationship, RelationshipPK> {

	
	@Query(value = "select * from relationship where id_seguido = :id", nativeQuery=true)
    List<Relationship> findAllFollowersByUserId(Long id);

    @Query(value = "select * from relationship where id_seguidor = :id", nativeQuery=true)
    List<Relationship> findAllFollowingById(Long id);
    
    @Query(value = "select * from relationship where id_seguido = :idSeguido and id_seguidor = :idSeguidor", nativeQuery=true)
    Optional<Relationship> findRelationshipById(Long idSeguidor, Long idSeguido);
    
    @Modifying
    @Query(value = "delete from relationship where id_seguidor= :idSeguidor and id_seguido = :idSeguido", nativeQuery=true)
    void findAndDelete (Long idSeguidor, Long idSeguido);
    
}
