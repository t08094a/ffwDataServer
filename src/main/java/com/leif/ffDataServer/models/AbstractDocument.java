/**
 * 
 */
package com.leif.ffDataServer.models;

import org.springframework.data.annotation.Id;

/**
 * @author leif
 *
 */
public class AbstractDocument
{
	@Id
	protected String id;

	public String getId()
	{
		return id;
	}

	@Override
	public boolean equals(Object obj)
	{

		if (this == obj)
		{
			return true;
		}

		if (this.id == null || obj == null || !(this.getClass().equals(obj.getClass())))
		{
			return false;
		}

		AbstractDocument that = (AbstractDocument) obj;

		return this.id.equals(that.getId());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode()
	{
		return id == null ? 0 : id.hashCode();
	}
}
