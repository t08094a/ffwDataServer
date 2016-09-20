/**
 * 
 */
package com.leif.ffDataServer.domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import com.leif.ffDataServer.helper.AgeCalculator;

/**
 * @author leif
 *
 */
@Document(collection = "persons")
@TypeAlias("person")
public class Person extends AbstractDocument
{
	@NotEmpty
	@Indexed
	private String			firstName;

	@NotEmpty
	@Indexed
	private String			lastName;

	private Gender			gender		= Gender.Male;
	private LocalDate		birthDate	= LocalDate.now();

	@NotEmpty
	private String			street;

	@NotEmpty
	private Integer			streetNumber;

	private String			streetNumberAppendix;

	@NotEmpty
	private Integer			PostalCode;

	@NotEmpty
	private String			city;

	private List<Contact>	contacts	= new ArrayList<Contact>();

	public Person()
	{
	}

	public Person(String firstName, String lastName)
	{
		this.firstName = firstName;
		this.lastName = lastName;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName()
	{
		return firstName;
	}

	/**
	 * @param firstName
	 *            the firstName to set
	 */
	public void setFirstName(String firstName)
	{
		this.firstName = firstName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName()
	{
		return lastName;
	}

	/**
	 * @param lastName
	 *            the lastName to set
	 */
	public void setLastName(String lastName)
	{
		this.lastName = lastName;
	}

	/**
	 * @return the gender
	 */
	public Gender getGender()
	{
		return gender;
	}

	/**
	 * @param gender
	 *            the gender to set
	 */
	public void setGender(Gender gender)
	{
		this.gender = gender;
	}

	/**
	 * @return the birthDate
	 */
	public LocalDate getBirthDate()
	{
		return birthDate;
	}

	/**
	 * @param birthDate
	 *            the birthDate to set
	 */
	public void setBirthDate(LocalDate birthDate)
	{
		this.birthDate = birthDate;
	}

	/**
	 * @return the street
	 */
	public String getStreet()
	{
		return street;
	}

	/**
	 * @param street
	 *            the street to set
	 */
	public void setStreet(String street)
	{
		this.street = street;
	}

	/**
	 * @return the streetNumber
	 */
	public Integer getStreetNumber()
	{
		return streetNumber;
	}

	/**
	 * @param streetNumber
	 *            the streetNumber to set
	 */
	public void setStreetNumber(Integer streetNumber)
	{
		this.streetNumber = streetNumber;
	}

	/**
	 * @return the streetNumberAppendix
	 */
	public String getStreetNumberAppendix()
	{
		return streetNumberAppendix;
	}

	/**
	 * @param streetNumberAppendix
	 *            the streetNumberAppendix to set
	 */
	public void setStreetNumberAppendix(String streetNumberAppendix)
	{
		this.streetNumberAppendix = streetNumberAppendix;
	}

	/**
	 * @return the postalCode
	 */
	public Integer getPostalCode()
	{
		return PostalCode;
	}

	/**
	 * @param postalCode
	 *            the postalCode to set
	 */
	public void setPostalCode(Integer postalCode)
	{
		PostalCode = postalCode;
	}

	/**
	 * @return the city
	 */
	public String getCity()
	{
		return city;
	}

	/**
	 * @param city
	 *            the city to set
	 */
	public void setCity(String city)
	{
		this.city = city;
	}

	/**
	 * @return the contacts
	 */
	public List<Contact> getContacts()
	{
		return contacts;
	}

	/**
	 * @return the age
	 */
	public int getAge()
	{
		LocalDate currentDate = LocalDate.now();

		int age = AgeCalculator.calculateAge(birthDate, currentDate);
		return age;
	}

	@Override
	public String toString()
	{
		return String.format("Person [id=%s, lastName='%s', firstName='%s']", getId(), lastName, firstName);
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
		result = prime * result + ((birthDate == null) ? 0 : birthDate.hashCode());
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
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
		{
			return true;
		}
		if (!super.equals(obj))
		{
			return false;
		}
		if (!(obj instanceof Person))
		{
			return false;
		}
		Person other = (Person) obj;
		if (birthDate == null)
		{
			if (other.birthDate != null)
			{
				return false;
			}
		} else if (!birthDate.equals(other.birthDate))
		{
			return false;
		}
		if (firstName == null)
		{
			if (other.firstName != null)
			{
				return false;
			}
		} else if (!firstName.equals(other.firstName))
		{
			return false;
		}
		if (lastName == null)
		{
			if (other.lastName != null)
			{
				return false;
			}
		} else if (!lastName.equals(other.lastName))
		{
			return false;
		}
		return true;
	}
}
