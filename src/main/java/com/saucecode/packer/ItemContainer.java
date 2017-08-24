package com.saucecode.packer;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Represents an item container. its a simple wrapper around {@link Item}.
 * 
 * @author Torben Kr&uuml;ger
 *
 */
@XmlRootElement(name = "items", namespace = "http://www.w3schools.com/saucecode")
// @XmlRootElement(name = "items", namespace =
// "http://www.w3schools.com/saucecode")
// @XmlRootElement(name = "items")
@XmlAccessorType(XmlAccessType.FIELD)
public class ItemContainer {

	/**
	 * The items.
	 */
	@XmlElement(name = "item")
	private ArrayList<Item> items;

	/**
	 * Returns the items.
	 * 
	 * @return
	 */
	public ArrayList<Item> getItems() {
		return items;
	}

	/**
	 * Sets the items.
	 * 
	 * @param items
	 *            items to set
	 */
	public void setItems(ArrayList<Item> items) {
		this.items = items;
	}

	@Override
	public String toString() {
		return "Items [item=" + items + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ItemContainer other = (ItemContainer) obj;
		if (items == null) {
			if (other.items != null)
				return false;
		} else if (!items.equals(other.items))
			return false;
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((items == null) ? 0 : items.hashCode());
		return result;
	}

}
