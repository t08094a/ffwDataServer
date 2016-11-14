/**
 * 
 */
package com.leif.ffDataServer.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;

import com.leif.ffDataServer.domain.Person;

/**
 * @author leif
 *
 */
public interface PersonRepository extends MongoRepository<Person, String>
{
	List<Person> findByLastName(@Param("name") String lastName);
	
	List<Person> findByLastNameLike(@Param("name") String lastName);
	
	List<Person> findByFirstName(@Param("name") String firstName);
	
	@Query("{ 'lastName' : ?0, 'firstName' : ?1 }")
	List<Person> findByName(@Param("lastName") String lastName, @Param("firstName") String firstName);
}