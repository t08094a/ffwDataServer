package com.leif.ffDataServer.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.leif.ffDataServer.domain.stock.Inventory;
import com.leif.ffDataServer.repositories.InventoryRepository;

public abstract class InventoryController<T extends Inventory>               
{
	@Autowired
	protected InventoryRepository<T> repository;

	@Autowired
	protected MongoOperations mongoOperations;
	
	public InventoryController()
	{
		System.out.println(">>> ctor InventoryController");
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public T create(@RequestBody T inventory)
	{
		return repository.save(inventory);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "{id}")
	public T get(@PathVariable("id") String id)
	{
		return repository.findOne(id);
	}
	
	@RequestMapping(method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public List<T> getAll()
	{
		System.out.println(">>> InventoryController::getAll");
		
		return repository.findAll();
	}    
}
