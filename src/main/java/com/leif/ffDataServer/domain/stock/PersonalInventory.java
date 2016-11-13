package com.leif.ffDataServer.domain.stock;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.mongodb.core.index.Indexed;

import com.leif.ffDataServer.domain.FireFighter;

public abstract class PersonalInventory extends Inventory
{
	@Indexed
	private FireFighter owner;
	
	public PersonalInventory(int inventoryNumber, @NotEmpty InventoryCategory category, FireFighter owner)
	{
		super(inventoryNumber, category);
		
		this.owner = owner;
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
}
