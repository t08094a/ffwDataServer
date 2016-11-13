package com.leif.ffDataServer.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.leif.ffDataServer.domain.stock.Inventory;

public interface InventoryRepository<T extends Inventory> extends MongoRepository<T, String>
{

}
