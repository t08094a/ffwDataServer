package com.leif.ffDataServer.domain.stock;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;

import com.leif.ffDataServer.domain.Firefighter;

public abstract class PersonalInventory extends Inventory
{
	@Indexed
	@DBRef
	private Firefighter owner;
	
	/**
	 * Default Ctor needed by Mongo. Do NOT use it directly.
	 */
	public PersonalInventory() 
	{
		super();
	}
	
	public PersonalInventory(int inventoryNumber, @NotEmpty InventoryCategory category, Firefighter owner)
	{
		super(inventoryNumber, category);
		
		this.owner = owner;
	}

	/**
	 * @return the owner
	 */
	public Firefighter getOwner()
	{
		return owner;
	}

	/**
	 * @param owner the owner to set
	 */
	public void setOwner(Firefighter owner)
	{
		OnPropertyChanged("owner", this.owner.toString(), owner.toString());
		
		this.owner = owner;
	}
}
