/**
 * 
 */
package com.leif.ffDataServer.repositories;

import java.math.BigInteger;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;

import com.leif.ffDataServer.models.Person;

/**
 * @author leif
 *
 */
public interface PersonRepository extends MongoRepository<Person, BigInteger> 
{
	List<Person> findByLastName(@Param("name") String lastName);
	
	List<Person> findByLastNameLike(@Param("name") String lastName);
	
	List<Person> findByFirstName(@Param("name") String firstName);
}