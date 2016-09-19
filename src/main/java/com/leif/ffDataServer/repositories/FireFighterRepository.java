/**
 * 
 */
package com.leif.ffDataServer.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.leif.ffDataServer.models.FireFighter;

/**
 * @author leif
 *
 */
@Repository
//@RepositoryRestResource(collectionResourceRel = "firefighters", path = "firefighters")
public interface FireFighterRepository extends MongoRepository<FireFighter, String>
{
	@Query("{ 'memberStatus' : 'Active' }")
	List<FireFighter> findAllActive();
}
