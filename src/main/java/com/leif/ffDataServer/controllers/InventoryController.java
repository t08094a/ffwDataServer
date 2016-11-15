package com.leif.ffDataServer.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.leif.ffDataServer.domain.stock.Inventory;
import com.leif.ffDataServer.repositories.InventoryRepository;

public abstract class InventoryController<T extends Inventory>
{
	private Logger					logger	= LoggerFactory.getLogger(InventoryController.class);
	private InventoryRepository<T>	repository;

	public InventoryController(InventoryRepository<T> repository)
	{
		System.out.println(">>> InventoryController::ctor");
		logger.debug(">>> ctor InventoryController");

		this.repository = repository;
	}

	@RequestMapping(method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<T> create(@RequestBody T json)
	{
		logger.debug("create() with body {} of type {}", json, json.getClass());

		T created = repository.save(json);

		if(created == null)
		{
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		else 
		{
			return new ResponseEntity<>(created, HttpStatus.CREATED);
		}
	}

	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	public ResponseEntity<T> get(@PathVariable("id") String id)
	{
		T found = repository.findOne(id);
		
		if(found == null)
		{
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		else 
		{
			return new ResponseEntity<>(found, HttpStatus.OK);
		}
	}

	@RequestMapping(method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<List<T>> getAll()
	{
		System.out.println(">>> InventoryController::getAll");
		logger.debug(">>> InventoryController::getAll");

		List<T> found = repository.findAll();
		
		if(found == null)
		{
			System.out.println(">>> InventoryController::getAll >>> nothing found");
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		else 
		{
			System.out.println(">>> InventoryController::getAll >>> found");
			return new ResponseEntity<>(found, HttpStatus.OK);
		}
	}

	@RequestMapping(value = "{id}", method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<T> update(@PathVariable String id, @RequestBody T json)
	{
		logger.debug("update() of {}.id#{} with body {}", json.getClass(), id, json);

		T entity = repository.findOne(id);
		
		if(entity == null)
		{
			logger.warn("the inventory of type '{}' and id {} does not exist", json.getClass(), id);
						
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		try
		{
			BeanUtils.copyProperties(entity, json);
		} 
		catch (Exception e)
		{
			logger.warn("while copying properties", e);
			throw e;
		}

		logger.debug("merged entity: {}", entity);

		T updated = repository.save(entity);
		logger.debug("updated enitity: {}", updated);

		if(updated == null)
		{
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		else 
		{
			return new ResponseEntity<>(updated, HttpStatus.OK);
		}
	}

	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	public ResponseEntity<T> delete(@PathVariable String id)
	{
		repository.delete(id);

		return new ResponseEntity<>(HttpStatus.OK);
	}
}
