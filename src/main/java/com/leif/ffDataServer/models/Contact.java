/**
 * 
 */
package com.leif.ffDataServer.models;

import org.springframework.data.annotation.Id;

/**
 * @author leif
 *
 */
public final class Contact
{
	@Id
	private String		id;

	private ContactType	type;

	/**
	 * the contact name, e.g. Work, Home.
	 */
	private String		name;
	private String		value;

	private Boolean		favourite;

	public Contact()
	{
	}

	/**
	 * @param type
	 * @param name
	 * @param favourite
	 */
	public Contact(ContactType type, String name, String value, Boolean favourite)
	{
		this.type = type;
		this.name = name;
		this.value = value;
		this.favourite = favourite;
	}

	/**
	 * @return the type
	 */
	public ContactType getType()
	{
		return type;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(ContactType type)
	{
		this.type = type;
	}

	/**
	 * @return the name
	 */
	public String getName()
	{
		return name;
	}

	/**
	 * the contact name, e.g. Work, Home.
	 * 
	 * @param name
	 *            the name to set
	 */
	public void setName(String name)
	{
		this.name = name;
	}

	/**
	 * @return the value
	 */
	public String getValue()
	{
		return value;
	}

	/**
	 * @param value the value to set
	 */
	public void setValue(String value)
	{
		this.value = value;
	}

	/**
	 * @return the favourite
	 */
	public Boolean getFavourite()
	{
		return favourite;
	}

	/**
	 * @param favourite
	 *            the favourite to set
	 */
	public void setFavourite(Boolean favourite)
	{
		this.favourite = favourite;
	}

	/**
	 * @return the id
	 */
	public String getId()
	{
		return id;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return String.format("Contact [type=%s, name=%s, favourite=%s]", type, name, favourite);
	}
}
