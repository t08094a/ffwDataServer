/**
 * 
 */
package com.leif.ffDataServer.domain.stock;

import java.time.LocalDate;

/**
 * @author leif
 *
 */
public interface IHistoryItem
{
	public LocalDate getChangeDate();
	
	public String getModifier();
	
	public String getOldValue();
	
	public String getNewValue();
	
	public String getProperty();
}
