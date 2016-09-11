/**
 * 
 */
package com.leif.ffDataServer.models;

/**
 * @author leif
 *
 */
public enum Weekday
{
	Monday(0), 
	Tuesday(1), 
	Wednesday(2), 
	Thursday(3), 
	Friday(4), 
	Saturday(5), 
	Sunday(6),
	
	Weekdays(Monday, Tuesday, Wednesday, Thursday, Friday),
	Weekend(Saturday, Sunday);

	private final int id;

	Weekday(int bit)
	{
		this.id = 1 << bit;
	}
	
	Weekday(Weekday... es)
	{
		int mask = 0;
		for(Weekday w: es)
		{
			mask |= w.getMask();
		}
		
		this.id = mask;
	}
	
	int getMask()
	{
		return this.id;
	}
}
