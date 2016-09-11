/**
 * 
 */
package com.leif.ffDataServer.repositories;

import java.math.BigInteger;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.leif.ffDataServer.models.Person;

/**
 * @author leif
 *
 */
@RepositoryRestResource(collectionResourceRel="persons", path="persons")
public interface PersonRepository extends MongoRepository<Person, BigInteger> 
{
	List<Person> findByLastName(@Param("name") String lastName);
	
	List<Person> findByLastNameLike(@Param("name") String lastName);
	
	List<Person> findByFirstName(@Param("name") String firstName);
}