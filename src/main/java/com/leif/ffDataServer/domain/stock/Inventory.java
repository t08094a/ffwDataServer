/**
 * 
 */
package com.leif.ffDataServer.domain.stock;

import java.time.LocalDate;
import java.time.ZoneId;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.mongodb.core.mapping.DBRef;

import com.leif.ffDataServer.domain.AbstractDocument;

/**
 * The moveable and not moveable inventory. (de: Inventar)
 * @author leif
 *
 */
public abstract class Inventory extends AbstractDocument
{
	@NotEmpty
	private Integer inventoryNumber;
	
	@NotEmpty
	@DBRef
	private InventoryCategory category;
	
	private LocalDate lastInspection;
	
	private History history;
	
	public Inventory(Integer inventoryNumber, @NotEmpty InventoryCategory category)
	{
		this.inventoryNumber = inventoryNumber;
		this.category = category;
	}
	
	/**
	 * @return the inventoryNumber
	 */
	public Integer getInventoryNumber()
	{
		return inventoryNumber;
	}

	/**
	 * @param inventoryNumber the inventoryNumber to set
	 */
	public void setInventoryNumber(Integer inventoryNumber)
	{
		if(inventoryNumber <= 0)
		{
			throw new IllegalArgumentException("inventoryNumber must be greater than 0");
		}
		
		OnPropertyChanged("inventoryNumber", String.valueOf(this.inventoryNumber), String.valueOf(inventoryNumber));
		
		this.inventoryNumber = inventoryNumber;
	}

	/**
	 * @return the category
	 */
	public InventoryCategory getCategory()
	{
		return category;
	}
	
	/**
	 * @param owner the owner to set
	 */
	public void setCategory(InventoryCategory category)
	{
		if(category == null)
		{
			throw new IllegalArgumentException("The category cannot be null");
		}
		
		OnPropertyChanged("category", this.category.toString(), category.toString());
		
		this.category = category;
	}
	
	/**
	 * @return the lastInspection
	 */
	public LocalDate getLastInspection()
	{
		return lastInspection;
	}

	/**
	 * @param lastInspection the lastInspection to set
	 */
	public void setLastInspection(LocalDate lastInspection)
	{
		this.lastInspection = lastInspection;
	}
	
	public LocalDate getNextInspectionDate()
	{
		if(category == null || lastInspection == null)
		{
			return LocalDate.now(ZoneId.of("+2"));
		}
		
		return lastInspection.plusDays(category.getInspectionInterval());
	}

	/**
	 * @return the history
	 */
	public History getHistory()
	{
		if(history == null)
		{
			history = new History();
		}
		
		return history;
	}
	
	protected void OnPropertyChanged(String property, String oldValue, String newValue)
	{
		LocalDate changeDate = LocalDate.now();
		String modifier = "unknown";
		
		getHistory().createItem(changeDate, modifier, property, oldValue, newValue);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return String.format("Inventory [%s, %s]", category, inventoryNumber);
	}
}
