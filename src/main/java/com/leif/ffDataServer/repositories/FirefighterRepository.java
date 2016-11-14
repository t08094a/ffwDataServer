/**
 * 
 */
package com.leif.ffDataServer.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.leif.ffDataServer.domain.Firefighter;

/**
 * @author leif
 *
 */
public interface FirefighterRepository extends MongoRepository<Firefighter, String>
{
	@Query("{ 'memberStatus' : 'Active' }")
	List<Firefighter> findAllActive();
}
