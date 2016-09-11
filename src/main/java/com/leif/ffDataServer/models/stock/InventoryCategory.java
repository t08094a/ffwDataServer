/**
 * 
 */
package com.leif.ffDataServer.models.stock;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.mongodb.core.index.Indexed;

/**
 * @author leif
 *
 */
public class InventoryCategory
{
	@Indexed(unique = true)
	@NotEmpty
	private String name;
	
	private InventoryCategory parent;
	private List<InventoryCategory> subCategory;
	
	public InventoryCategory(@NotEmpty String name)
	{
		this.name = name;
	}
	
	/**
	 * @return the name
	 */
	public String getName()
	{
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(@NotEmpty String name)
	{
		this.name = name;
	}

	/**
	 * @return the parent
	 */
	public InventoryCategory getParent()
	{
		return parent;
	}

	/**
	 * @param parent the parent to set
	 */
	public void setParent(InventoryCategory parent)
	{
		this.parent = parent;
	}

	/**
	 * @return the subCategory
	 */
	public List<InventoryCategory> getSubCategory()
	{
		if(subCategory == null)
		{
			subCategory = new ArrayList<>();
		}
		
		return subCategory;
	}
	
	public boolean HasSubCategory()
	{
		return !(subCategory == null || subCategory.isEmpty());
	}
	
	@Override
	public String toString()
	{
		return name;
	}
}
