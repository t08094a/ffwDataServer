/**
 * 
 */
package com.leif.ffDataServer.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.leif.ffDataServer.domain.Firefighter;
import com.leif.ffDataServer.domain.Person;

/**
 * @author leif
 *
 */
@RepositoryRestResource(exported = false)
public interface FirefighterRepository extends MongoRepository<Firefighter, String>
{
	@Query("{ 'memberStatus' : 'Active' }")
	List<Firefighter> findAllActive();
	
	Firefighter findByPerson(Person person);
}
