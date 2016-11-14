package com.leif.ffDataServer.domain.stock;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.mongodb.core.index.Indexed;

import com.leif.ffDataServer.domain.Firefighter;

public abstract class PersonalInventory extends Inventory
{
	@Indexed
	private Firefighter owner;
	
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
