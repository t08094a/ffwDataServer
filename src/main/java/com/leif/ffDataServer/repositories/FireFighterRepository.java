/**
 * 
 */
package com.leif.ffDataServer.repositories;

import java.math.BigInteger;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.leif.ffDataServer.models.FireFighter;

/**
 * @author leif
 *
 */
public interface FireFighterRepository extends MongoRepository<FireFighter, BigInteger> { }
