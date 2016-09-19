package com.leif.ffDataServer.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.leif.ffDataServer.domain.FireFighter;
import com.leif.ffDataServer.domain.Person;
import com.leif.ffDataServer.repositories.FireFighterRepository;
import com.leif.ffDataServer.repositories.PersonRepository;

@RestController
@RequestMapping("/firefighters")
public class FireFighterController
{
	@Autowired
	private FireFighterRepository repository;
	
	@Autowired
	private PersonRepository personRepository;
	
	@Autowired
	private MongoOperations mongoOperations;

	@RequestMapping(method = RequestMethod.POST)
	public FireFighter create(@RequestBody FireFighter firefighter)
	{
		return repository.save(firefighter);
	}

	@RequestMapping(method = RequestMethod.GET, value = "{id}")
	public FireFighter get(@PathVariable("id") String id)
	{
		return repository.findOne(id);
	}
	
	//@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<FireFighter>> get(@RequestParam("lastName") String lastName, @RequestParam(value="firstName", required=false) String firstName)
	{
		List<Person> persons = null;
		if(firstName != null && firstName.isEmpty() == false)
		{
			persons = personRepository.findByName(lastName, firstName);
		}
		else
		{
			persons = personRepository.findByLastName(lastName);
		}
		
		Criteria c = Criteria.where("person").in(persons);
		Query q = Query.query(c);
		
		List<FireFighter> result = mongoOperations.find(q, FireFighter.class);
		if(result.isEmpty())
		{
			return new ResponseEntity<List<FireFighter>>(HttpStatus.NO_CONTENT);
		}
		else
		{
			return new ResponseEntity<List<FireFighter>>(result, HttpStatus.OK);
		}
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public List<FireFighter> getAll()
	{
		return repository.findAll();
	}

	@RequestMapping(method = RequestMethod.PUT, value = "{id}")
	public FireFighter update(@PathVariable("id") String id, @RequestBody FireFighter firefighter)
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
	public void delete(@PathVariable("id") String id)
	{
		repository.delete(id);
	}
}