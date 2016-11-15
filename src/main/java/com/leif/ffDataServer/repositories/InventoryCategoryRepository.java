package com.leif.ffDataServer.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.leif.ffDataServer.domain.stock.InventoryCategory;

@RepositoryRestResource(exported = false)
public interface InventoryCategoryRepository extends MongoRepository<InventoryCategory, String>
{
	InventoryCategory findByName(String name);
}
