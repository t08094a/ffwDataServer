package com.leif.ffDataServer.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.leif.ffDataServer.domain.Account;

@Repository
public interface AccountRepository extends MongoRepository<Account, String>
{
	public Account findByUsername(String username);
}
