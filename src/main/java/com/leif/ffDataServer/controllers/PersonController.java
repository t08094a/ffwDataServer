/**
 * 
 */
package com.leif.ffDataServer.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.leif.ffDataServer.domain.Person;
import com.leif.ffDataServer.repositories.PersonRepository;

/**
 * @author leif
 *
 */
@RestController
@RequestMapping("/persons")
public class PersonController
{
	@Autowired
	private PersonRepository repository;

	@RequestMapping(method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED )
	public Person create(@RequestBody Person person)
	{
		return repository.save(person);
	}

	@RequestMapping(method = RequestMethod.GET, value = "{id}")
	@ResponseStatus(HttpStatus.OK)
	public Person get(@PathVariable("id") String id)
	{
		return repository.findOne(id);
	}

	@RequestMapping(method = RequestMethod.GET, value = "{lastName}/{firstName}")
	@ResponseStatus(HttpStatus.OK)
	public Person get(@PathVariable("lastName") String lastName, @PathVariable("firstName") String firstName)
	{
		List<Person> result = repository.findByName(lastName, firstName);
		if(result.isEmpty())
		{
			return null;
		}
		else
		{
			return result.get(0);
		}
	}

	@RequestMapping(method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public List<Person> getAll()
	{
		return repository.findAll();
	}

	@RequestMapping(method = RequestMethod.PUT, value = "{id}")
	@ResponseStatus(HttpStatus.OK)
	public Person update(@PathVariable("id") String id, @RequestBody Person person)
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
	@ResponseStatus(HttpStatus.OK)
	public void delete(@PathVariable("id") String id)
	{
		repository.delete(id);
	}
}