/**
 * 
 */
package com.leif.ffDataServer.controllers;

import java.math.BigInteger;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.leif.ffDataServer.models.Person;
import com.leif.ffDataServer.repositories.PersonRepository;

/**
 * @author leif
 *
 */
@RestController
@RequestMapping("/person")
public class PersonController
{
	@Autowired
	private PersonRepository repository;
	
	@RequestMapping(method = RequestMethod.POST)
	public Person create(@RequestBody Person person)
	{
		return repository.save(person);
	}

	@RequestMapping(method = RequestMethod.GET, value = "{id}")
	public Person get(@PathVariable("id") BigInteger id)
	{
		return repository.findOne(id);
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public List<Person> getAll()
	{
		return repository.findAll();
	}

	@RequestMapping(method = RequestMethod.PUT, value = "{id}")
	public Person update(@PathVariable("id") BigInteger id, @RequestBody Person person)
	{
		Person update = repository.findOne(id);
		update.setBirthDate(person.getBirthDate());
		update.setCity(person.getCity());
		update.setFirstName(person.getFirstName());
		update.setGender(person.getGender());
		update.setLastName(person.getLastName());
		update.setPostalCode(person.getPostalCode());
		update.setStreet(person.getStreet());
		update.setStreetNumber(person.getStreetNumber());
		update.setStreetNumberAppendix(person.getStreetNumberAppendix());
				
		return repository.save(update);
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "{id}")
	public void delete(@PathVariable("id") BigInteger id)
	{
		repository.delete(id);
	}
}
