/**
 * 
 */
package com.leif.ffDataServer.models;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.leif.ffDataServer.helper.AgeCalculator;
import com.leif.ffDataServer.models.stock.Inventory;

/**
 * @author leif
 *
 */
@Document(collection = "firefighters")
@TypeAlias("firefighter")
public class FireFighter extends AbstractDocument
{
	@DBRef
	private Person				person;

	@NotEmpty
	private MemberStatus		memberStatus;

	@NotEmpty
	private LocalDate			entry;
	private LocalDate			exit;
	private List<Availibility>	availibility;

	private List<Inventory>		inventories;

	public FireFighter()
	{
	}

	/**
	 * @param person
	 * @param entry
	 */
	public FireFighter(Person person, LocalDate entry)
	{
		this(person, entry, null);
	}

	/**
	 * @param person
	 * @param entry
	 * @param exit
	 */
	@PersistenceConstructor
	public FireFighter(Person person, LocalDate entry, LocalDate exit)
	{
		this.person = person;
		this.entry = entry;
		this.exit = exit;
	}

	/**
	 * @return
	 * @see com.leif.ffDataServer.models.Person#getFirstName()
	 */
	public String getFirstName()
	{
		return person.getFirstName();
	}

	/**
	 * @param firstName
	 * @see com.leif.ffDataServer.models.Person#setFirstName(java.lang.String)
	 */
	public void setFirstName(String firstName)
	{
		person.setFirstName(firstName);
	}

	/**
	 * @return
	 * @see com.leif.ffDataServer.models.Person#getLastName()
	 */
	public String getLastName()
	{
		return person.getLastName();
	}

	/**
	 * @param lastName
	 * @see com.leif.ffDataServer.models.Person#setLastName(java.lang.String)
	 */
	public void setLastName(String lastName)
	{
		person.setLastName(lastName);
	}

	/**
	 * @return
	 * @see com.leif.ffDataServer.models.Person#getGender()
	 */
	public Gender getGender()
	{
		return person.getGender();
	}

	/**
	 * @param gender
	 * @see com.leif.ffDataServer.models.Person#setGender(com.leif.ffDataServer.models.Gender)
	 */
	public void setGender(Gender gender)
	{
		person.setGender(gender);
	}

	/**
	 * @return
	 * @see com.leif.ffDataServer.models.Person#getBirthDate()
	 */
	public LocalDate getBirthDate()
	{
		return person.getBirthDate();
	}

	/**
	 * @param birthDate
	 * @see com.leif.ffDataServer.models.Person#setBirthDate(java.time.LocalDate)
	 */
	public void setBirthDate(LocalDate birthDate)
	{
		person.setBirthDate(birthDate);
	}

	/**
	 * @return
	 * @see com.leif.ffDataServer.models.Person#getStreet()
	 */
	public String getStreet()
	{
		return person.getStreet();
	}

	/**
	 * @param street
	 * @see com.leif.ffDataServer.models.Person#setStreet(java.lang.String)
	 */
	public void setStreet(String street)
	{
		person.setStreet(street);
	}

	/**
	 * @return
	 * @see com.leif.ffDataServer.models.Person#getStreetNumber()
	 */
	public Integer getStreetNumber()
	{
		return person.getStreetNumber();
	}

	/**
	 * @param streetNumber
	 * @see com.leif.ffDataServer.models.Person#setStreetNumber(java.lang.Integer)
	 */
	public void setStreetNumber(Integer streetNumber)
	{
		person.setStreetNumber(streetNumber);
	}

	/**
	 * @return
	 * @see com.leif.ffDataServer.models.Person#getStreetNumberAppendix()
	 */
	public String getStreetNumberAppendix()
	{
		return person.getStreetNumberAppendix();
	}

	/**
	 * @param streetNumberAppendix
	 * @see com.leif.ffDataServer.models.Person#setStreetNumberAppendix(java.lang.String)
	 */
	public void setStreetNumberAppendix(String streetNumberAppendix)
	{
		person.setStreetNumberAppendix(streetNumberAppendix);
	}

	/**
	 * @return
	 * @see com.leif.ffDataServer.models.Person#getPostalCode()
	 */
	public Integer getPostalCode()
	{
		return person.getPostalCode();
	}

	/**
	 * @param postalCode
	 * @see com.leif.ffDataServer.models.Person#setPostalCode(java.lang.Integer)
	 */
	public void setPostalCode(Integer postalCode)
	{
		person.setPostalCode(postalCode);
	}

	/**
	 * @return
	 * @see com.leif.ffDataServer.models.Person#getCity()
	 */
	public String getCity()
	{
		return person.getCity();
	}

	/**
	 * @param city
	 * @see com.leif.ffDataServer.models.Person#setCity(java.lang.String)
	 */
	public void setCity(String city)
	{
		person.setCity(city);
	}

	/**
	 * @return
	 * @see com.leif.ffDataServer.models.Person#getContacts()
	 */
	public List<Contact> getContacts()
	{
		return person.getContacts();
	}

	/**
	 * @return
	 * @see com.leif.ffDataServer.models.Person#getAge()
	 */
	public int getAge()
	{
		return person.getAge();
	}

	/**
	 * @return the entry
	 */
	public LocalDate getEntry()
	{
		return entry;
	}

	/**
	 * @param entry
	 *            the entry to set
	 */
	public void setEntry(LocalDate entry)
	{
		this.entry = entry;
	}

	/**
	 * @return the exit
	 */
	public LocalDate getExit()
	{
		return exit;
	}

	/**
	 * @param exit
	 *            the exit to set
	 */
	public void setExit(LocalDate exit)
	{
		this.exit = exit;

		if (memberStatus == MemberStatus.Active)
		{
			int years = getServiceTime().getYears();

			if (years < 10)
			{
				setMemberStatus(MemberStatus.Supporting);
			} else
			{
				setMemberStatus(MemberStatus.Passive);
			}
		}
	}

	/**
	 * Gets the service time from the beginning to the end, or if
	 * <see ref="Exit" /> is not set until now. (de: Dienstzeit)
	 * 
	 * @return
	 */
	public Period getServiceTime()
	{
		return AgeCalculator.calculateTimespan(entry, exit);
	}

	/**
	 * @return the memberStatus
	 */
	public MemberStatus getMemberStatus()
	{
		return memberStatus;
	}

	/**
	 * @param memberStatus
	 *            the memberStatus to set
	 */
	public void setMemberStatus(MemberStatus memberStatus)
	{
		this.memberStatus = memberStatus;

		switch (memberStatus)
		{
			case Active:
				if (exit != null)
				{
					setExit(null);
				}

				break;
			case Passive:
			case Supporting:
				if (exit == null)
				{
					setExit(LocalDate.now());
				}
				break;
			default:
				break;
		}
	}

	/**
	 * @return the inventories
	 */
	public List<Inventory> getInventories()
	{
		if(inventories == null)
		{
			inventories = new ArrayList<>();
		}
		
		return inventories;
	}

	@Override
	public String toString()
	{
		return String.format("Firefighter [id=%s, lastName='%s', firstName='%s']", id, person.getLastName(),
				person.getFirstName());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((person == null) ? 0 : person.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (!(obj instanceof FireFighter))
			return false;
		FireFighter other = (FireFighter) obj;
		if (person == null)
		{
			if (other.person != null)
				return false;
		} else if (!person.equals(other.person))
			return false;
		return true;
	}

}
