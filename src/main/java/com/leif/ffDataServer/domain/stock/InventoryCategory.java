/**
 * 
 */
package com.leif.ffDataServer.domain.stock;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import com.leif.ffDataServer.domain.AbstractDocument;

/**
 * @author leif
 *
 */
@Document(collection = "inventoryCategory")
@TypeAlias("inventoryCategory")
public class InventoryCategory extends AbstractDocument
{
	@Indexed(unique = true)
	@NotEmpty
	private String name;
	
	private String description;
	
	private Integer inspectionInterval = 365;
	
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
	 * @return the description
	 */
	public String getDescription()
	{
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description)
	{
		this.description = description;
	}
	
	/**
	 * @return the inspectionInterval in days
	 */
	public Integer getInspectionInterval()
	{
		return inspectionInterval;
	}

	/**
	 * @param inspectionInterval the inspectionInterval to set in days.
	 */
	public void setInspectionInterval(Integer inspectionInterval)
	{
		this.inspectionInterval = inspectionInterval;
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
	public List<InventoryCategory> getSubCategories()
	{
		if(subCategory == null)
		{
			subCategory = new ArrayList<>();
		}
		
		return subCategory;
	}
	
	public boolean IsRoot()
	{
		return parent == null;
	}
	
	public boolean HasSubCategory()
	{
		return !(subCategory == null || subCategory.isEmpty());
	}
	
	@Override
	public String toString()
	{
		return "Category: " + name;
	}
}
