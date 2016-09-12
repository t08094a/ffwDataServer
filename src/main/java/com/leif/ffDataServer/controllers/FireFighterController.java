package com.leif.ffDataServer.controllers;

import java.math.BigInteger;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.leif.ffDataServer.models.FireFighter;
import com.leif.ffDataServer.repositories.FireFighterRepository;

@RestController
@RequestMapping("/firefighter")
public class FireFighterController
{
	@Autowired
	private FireFighterRepository repository;

	@RequestMapping(method = RequestMethod.POST)
	public FireFighter create(@RequestBody FireFighter firefighter)
	{
		return repository.save(firefighter);
	}

	@RequestMapping(method = RequestMethod.GET, value = "{id}")
	public FireFighter get(@PathVariable("id") BigInteger id)
	{
		return repository.findOne(id);
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public List<FireFighter> getAll()
	{
		return repository.findAll();
	}

	@RequestMapping(method = RequestMethod.PUT, value = "{id}")
	public FireFighter update(@PathVariable("id") BigInteger id, @RequestBody FireFighter firefighter)
	{
		FireFighter update = repository.findOne(id);
		update.setBirthDate(firefighter.getBirthDate());
		update.setCity(firefighter.getCity());
		update.setEntry(firefighter.getEntry());
		update.setExit(firefighter.getExit());
		update.setFirstName(firefighter.getFirstName());
		update.setGender(firefighter.getGender());
		update.setLastName(firefighter.getLastName());
		update.setMemberStatus(firefighter.getMemberStatus());
		update.setPostalCode(firefighter.getPostalCode());
		update.setStreet(firefighter.getStreet());
		update.setStreetNumber(firefighter.getStreetNumber());
		update.setStreetNumberAppendix(firefighter.getStreetNumberAppendix());
				
		return repository.save(update);
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "{id}")
	public void delete(@PathVariable("id") BigInteger id)
	{
		repository.delete(id);
	}
}
