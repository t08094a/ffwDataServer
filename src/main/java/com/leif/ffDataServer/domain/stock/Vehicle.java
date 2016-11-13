package com.leif.ffDataServer.domain.stock;

import java.time.LocalDate;

import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "inventory")
@TypeAlias("vehicle")
@CompoundIndex(unique=true, def="{'inventoryNumber' : 1, 'category' : 1}")
public class Vehicle extends GeneralInventory
{
	private String licensePlate;
	private String radioGivenName;
	private LocalDate registrationDate;
	private LocalDate lastTuev;
	private LocalDate lastMaintenance;
	
	public Vehicle(Integer inventoryNumber, InventoryCategory category)
	{
		super(inventoryNumber, category);
	}

	/**
	 * @return the licensePlate
	 */
	public String getLicensePlate()
	{
		return licensePlate;
	}

	/**
	 * @param licensePlate the licensePlate to set
	 */
	public void setLicensePlate(String licensePlate)
	{
		this.licensePlate = licensePlate;
	}

	/**
	 * @return the radioGivenName
	 */
	public String getRadioGivenName()
	{
		return radioGivenName;
	}

	/**
	 * @param radioGivenName the radioGivenName to set
	 */
	public void setRadioGivenName(String radioGivenName)
	{
		this.radioGivenName = radioGivenName;
	}

	/**
	 * @return the registrationDate
	 */
	public LocalDate getRegistrationDate()
	{
		return registrationDate;
	}

	/**
	 * @param registrationDate the registrationDate to set
	 */
	public void setRegistrationDate(LocalDate registrationDate)
	{
		this.registrationDate = registrationDate;
	}

	/**
	 * @return the lastTuev
	 */
	public LocalDate getLastTuev()
	{
		return lastTuev;
	}

	/**
	 * @param lastTuev the lastTuev to set
	 */
	public void setLastTuev(LocalDate lastTuev)
	{
		this.lastTuev = lastTuev;
	}
	
	public LocalDate getNextTuev()
	{
		if(lastTuev == null)
		{
			if(registrationDate != null)
			{
				return registrationDate.plusYears(3);
			}
			else
			{
				return LocalDate.now();
			}
		} 
		else
		{
			return lastTuev.plusYears(2);
		}
	}

	/**
	 * @return the lastMaintenance
	 */
	public LocalDate getLastMaintenance()
	{
		return lastMaintenance;
	}

	/**
	 * @param lastMaintenance the lastMaintenance to set
	 */
	public void setLastMaintenance(LocalDate lastMaintenance)
	{
		this.lastMaintenance = lastMaintenance;
	}
	
	public LocalDate getNextMaintenance()
	{
		if(lastMaintenance == null)
		{
			if(registrationDate != null)
			{
				return registrationDate.plusYears(2);
			}
			else
			{
				return LocalDate.now();
			}
		} 
		else
		{
			return lastMaintenance.plusYears(2);
		}
	}

	@Override
	public String toString()
	{
		return String.format("Vehicle %s", radioGivenName);
	}	
}
