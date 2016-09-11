/**
 * 
 */
package com.leif.ffDataServer.repositories;

import java.math.BigInteger;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.leif.ffDataServer.models.FireFighter;

/**
 * @author leif
 *
 */
@RepositoryRestResource(collectionResourceRel="firefighters",path="firefighters")
public interface FireFighterRepository extends MongoRepository<FireFighter, BigInteger> { }
