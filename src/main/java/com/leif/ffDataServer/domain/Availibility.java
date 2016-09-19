/**
 * 
 */
package com.leif.ffDataServer.domain;

import java.time.LocalDate;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * The availibility of a {@link PersonFireFighter} at different day times. (de:
 * Verfügbarkeit)
 * 
 * @author leif
 * 
 */
public class Availibility
{
	@NotEmpty
	private Weekday		day;
	
	@NotEmpty
	private LocalDate	start;
	
	@NotEmpty
	private LocalDate	end;

	public Availibility(Weekday day, LocalDate start, LocalDate end)
	{
		this.day = day;
		this.start = start;
		this.end = end;
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
		int result = 1;
		result = prime * result + ((day == null) ? 0 : day.hashCode());
		result = prime * result + ((end == null) ? 0 : end.hashCode());
		result = prime * result + ((start == null) ? 0 : start.hashCode());
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
		if (obj == null)
		{
			return false;
		}
		if (!(obj instanceof Availibility))
		{
			return false;
		}
		Availibility other = (Availibility) obj;
		if (day != other.day)
		{
			return false;
		}
		if (end == null)
		{
			if (other.end != null)
			{
				return false;
			}
		} else if (!end.equals(other.end))
		{
			return false;
		}
		if (start == null)
		{
			if (other.start != null)
			{
				return false;
			}
		} else if (!start.equals(other.start))
		{
			return false;
		}
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return String.format("Availibility [day=%s, start=%s, end=%s]", day, start, end);
	}
}
