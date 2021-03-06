package com.leif.ffDataServer.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.leif.ffDataServer.domain.Account;

@RepositoryRestResource(exported = false)
public interface AccountRepository extends MongoRepository<Account, String>
{
	public Account findByUsername(String username);
	
	public void deleteByUsername(String username);
}
