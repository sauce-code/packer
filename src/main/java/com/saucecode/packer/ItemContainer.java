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

}
