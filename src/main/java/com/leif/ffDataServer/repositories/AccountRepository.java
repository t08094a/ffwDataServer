package com.leif.ffDataServer.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.leif.ffDataServer.domain.Account;

public interface AccountRepository extends MongoRepository<Account, String>
{
	public Account findByUsername(String username);
	
	public void deleteByUsername(String username);
}
