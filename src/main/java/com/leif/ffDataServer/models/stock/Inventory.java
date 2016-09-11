/**
 * 
 */
package com.leif.ffDataServer.models.stock;

import java.time.LocalDate;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import com.leif.ffDataServer.models.AbstractDocument;
import com.leif.ffDataServer.models.FireFighter;

/**
 * The moveable and not moveable inventory. (de: Inventar)
 * @author leif
 *
 */
@Document(collection = "inventory")
@TypeAlias("inventory")
public abstract class Inventory extends AbstractDocument
{
	@NotEmpty
	@Indexed
	private int inventoryNumber;
	
	@NotEmpty
	@Indexed
	private InventoryCategory category;
	
	@Indexed
	private FireFighter owner;
	
	private History history;
	
	public Inventory(int inventoryNumber, @NotEmpty InventoryCategory category)
	{
		this(inventoryNumber, category, null);
	}
	
	public Inventory(int inventoryNumber, @NotEmpty InventoryCategory category, FireFighter owner)
	{
		this.inventoryNumber = inventoryNumber;
		this.category = category;
		this.owner = owner;
	}
	
	/**
	 * @return the inventoryNumber
	 */
	public int getInventoryNumber()
	{
		return inventoryNumber;
	}

	/**
	 * @param inventoryNumber the inventoryNumber to set
	 */
	public void setInventoryNumber(int inventoryNumber)
	{
		if(inventoryNumber <= 0)
		{
			throw new IllegalArgumentException("inventoryNumber must be greater than 0");
		}
		
		OnPropertyChanged("inventoryNumber", String.valueOf(this.inventoryNumber), String.valueOf(inventoryNumber));
		
		this.inventoryNumber = inventoryNumber;
	}

	/**
	 * @return the owner
	 */
	public FireFighter getOwner()
	{
		return owner;
	}

	/**
	 * @param owner the owner to set
	 */
	public void setOwner(FireFighter owner)
	{
		OnPropertyChanged("owner", this.owner.toString(), owner.toString());
		
		this.owner = owner;
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
