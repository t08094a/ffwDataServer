/**
 * 
 */
package com.leif.ffDataServer.models.stock;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * @author leif
 *
 */
public class History
{
	private HashSet<HistoryItem> items;
	
	public Set<HistoryItem> getItems()
	{
		if(items == null)
		{
			items = new HashSet<HistoryItem>();
		}
		
		return items;
	}
	
	public void createItem(@NotEmpty LocalDate changeDate, @NotEmpty String modifier, @NotEmpty String property, @NotEmpty String oldValue, @NotEmpty String newValue)
	{
		HistoryItem item = new HistoryItem(changeDate, modifier, property, oldValue, newValue);
		
		getItems().add(item);
	}
	
	private class HistoryItem implements IHistoryItem
	{
		private LocalDate	changeDate;
		private String		modifier;
		private String		oldValue;
		private String		newValue;
		private String		property;
		
		/**
		 * @param changeDate
		 * @param modifier
		 * @param property
		 * @param oldValue
		 * @param newValue 
		 */
		public HistoryItem(LocalDate changeDate, String modifier, String property, String oldValue, String newValue)
		{
			this.changeDate = changeDate;
			this.modifier = modifier;
			this.oldValue = oldValue;
			this.newValue = newValue;
			this.property = property;
		}

		@Override
		public LocalDate getChangeDate()
		{
			return this.changeDate;
		}

		@Override
		public String getModifier()
		{
			return this.modifier;
		}

		@Override
		public String getOldValue()
		{
			return this.oldValue;
		}

		@Override
		public String getNewValue()
		{
			return this.newValue;
		}

		@Override
		public String getProperty()
		{
			return this.property;
		}

		/* (non-Javadoc)
		 * @see java.lang.Object#toString()
		 */
		@Override
		public String toString()
		{
			return String.format("[%s, by %s] %s: '%s' => '%s'",
					changeDate, modifier, property, oldValue, newValue);
		}

		/* (non-Javadoc)
		 * @see java.lang.Object#hashCode()
		 */
		@Override
		public int hashCode()
		{
			final int prime = 31;
			int result = 1;
			result = prime * result + getOuterType().hashCode();
			result = prime * result + ((changeDate == null) ? 0 : changeDate.hashCode());
			result = prime * result + ((modifier == null) ? 0 : modifier.hashCode());
			result = prime * result + ((newValue == null) ? 0 : newValue.hashCode());
			result = prime * result + ((oldValue == null) ? 0 : oldValue.hashCode());
			result = prime * result + ((property == null) ? 0 : property.hashCode());
			return result;
		}

		/* (non-Javadoc)
		 * @see java.lang.Object#equals(java.lang.Object)
		 */
		@Override
		public boolean equals(Object obj)
		{
			if (this == obj)
			{
				return true;
			}
			if (obj == null)
			{
				return false;
			}
			if (!(obj instanceof HistoryItem))
			{
				return false;
			}
			HistoryItem other = (HistoryItem) obj;
			if (!getOuterType().equals(other.getOuterType()))
			{
				return false;
			}
			if (changeDate == null)
			{
				if (other.changeDate != null)
				{
					return false;
				}
			} else if (!changeDate.equals(other.changeDate))
			{
				return false;
			}
			if (modifier == null)
			{
				if (other.modifier != null)
				{
					return false;
				}
			} else if (!modifier.equals(other.modifier))
			{
				return false;
			}
			if (newValue == null)
			{
				if (other.newValue != null)
				{
					return false;
				}
			} else if (!newValue.equals(other.newValue))
			{
				return false;
			}
			if (oldValue == null)
			{
				if (other.oldValue != null)
				{
					return false;
				}
			} else if (!oldValue.equals(other.oldValue))
			{
				return false;
			}
			if (property == null)
			{
				if (other.property != null)
				{
					return false;
				}
			} else if (!property.equals(other.property))
			{
				return false;
			}
			return true;
		}

		private History getOuterType()
		{
			return History.this;
		}
	}
}
