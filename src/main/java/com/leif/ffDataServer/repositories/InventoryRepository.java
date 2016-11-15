package com.leif.ffDataServer.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.leif.ffDataServer.domain.stock.Inventory;

@RepositoryRestResource(exported = false)
public interface InventoryRepository<T extends Inventory> extends MongoRepository<T, String>
{

}
