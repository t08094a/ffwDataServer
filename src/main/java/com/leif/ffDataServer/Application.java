package com.leif.ffDataServer;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.leif.ffDataServer.domain.Contact;
import com.leif.ffDataServer.domain.ContactType;
import com.leif.ffDataServer.domain.Firefighter;
import com.leif.ffDataServer.domain.Person;
import com.leif.ffDataServer.repositories.FirefighterRepository;
import com.leif.ffDataServer.repositories.PersonRepository;

@SpringBootApplication
public class Application implements CommandLineRunner
{
	@Autowired
	private PersonRepository		personRepository;

	@Autowired
	private FirefighterRepository	firefighterRepository;

	// private final Logger log = LoggerFactory.getLogger(this.getClass());

	public static void main(String[] args)
	{
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception
	{
		createDummyPersons();
		createDummyFirefighters();
	}

	private void createDummyPersons()
	{
		personRepository.deleteAll();

		Person p1 = new Person("Alice", "Smith");
		p1.setBirthDate(LocalDate.of(1981, 6, 30));
		p1.setStreet("Waldstraße");
		p1.setStreetNumber(46);
		p1.setPostalCode(91472);
		p1.setCity("Ipsheim");

		p1.getContacts().add(new Contact(ContactType.Mobile, "Home", "0174/9441938", true));
		p1.getContacts().add(new Contact(ContactType.Phone, "Home", "09846/976887", true));

		Person p2 = new Person("Bob", "Smith");
		p2.setBirthDate(LocalDate.of(1982, 4, 15));
		p2.setStreet("Waldstraße");
		p2.setStreetNumber(46);
		p2.setPostalCode(91472);
		p2.setCity("Ipsheim");

		personRepository.save(p1);
		personRepository.save(p2);
		
		System.out.println("Persons found with findAll():");
		System.out.println("-------------------------------");

		for (Person person : personRepository.findAll())
		{
			System.out.println(person);
		}
		System.out.println();

		// fetch an individual person
		System.out.println("Person found with findByFirstName('Alice'):");
		System.out.println("--------------------------------");
		System.out.println(personRepository.findByFirstName("Alice"));

		System.out.println("Customers found with findByLastName('Smith'):");
		System.out.println("--------------------------------");
		for (Person person : personRepository.findByLastName("Smith"))
		{
			System.out.println(person);
		}
	}
	
	private void createDummyFirefighters()
	{
		firefighterRepository.deleteAll();
		
		Person p1 = personRepository.findByFirstName("Alice").get(0);
				
		Firefighter f1 = new Firefighter(p1, LocalDate.of(1980, 1, 1));
		
		firefighterRepository.save(f1);
		
		System.out.println("Firefighters found with findAll():");
		System.out.println("-------------------------------");
		
		for (Firefighter firefighter : firefighterRepository.findAll())
		{
			System.out.println(firefighter);
		}
		System.out.println();
	}
}
