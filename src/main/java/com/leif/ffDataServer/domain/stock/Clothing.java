package com.leif.ffDataServer.domain.stock;

import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.mapping.Document;

import com.leif.ffDataServer.domain.Firefighter;

@Document(collection = "inventory")
@TypeAlias("clothing")
@CompoundIndex(unique=true, def="{'inventoryNumber' : 1, 'category' : 1}")
public class Clothing extends PersonalInventory
{
	private Integer size;
	
	/**
	 * Default Ctor needed by Mongo. Do NOT use it directly.
	 */
	public Clothing()
	{
		super();
	}
	
	public Clothing(int inventoryNumber, InventoryCategory category, Firefighter owner)
	{
		this(inventoryNumber, category, owner, 0);
	}

	public Clothing(int inventoryNumber, InventoryCategory category, Firefighter owner, Integer size)
	{
		super(inventoryNumber, category, owner);
		
		this.setSize(size);
	}

	/**
	 * @return the size
	 */
	public Integer getSize()
	{
		return size;
	}

	/**
	 * @param size the size to set
	 */
	public void setSize(Integer size)
	{
		this.size = size;
	}
}
