package com.saucecode.packer;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Represents an item.
 * 
 * @author Torben Kr&uuml;ger
 *
 */
@XmlRootElement(name = "item")
@XmlAccessorType(XmlAccessType.FIELD)
public class Item {

	/**
	 * The name.
	 */
	private String name;

	/**
	 * The category.
	 */
	private String category;

	/**
	 * The sets.
	 */
	@XmlElementWrapper(name = "sets")
	@XmlElement(name = "set")
	private ArrayList<String> sets;

	/**
	 * Returns the name.
	 * 
	 * @return name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name.
	 * 
	 * @param name
	 *            name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Returns the category.
	 * 
	 * @return category
	 */
	public String getCategory() {
		return category;
	}

	/**
	 * Sets the category.
	 * 
	 * @param category
	 *            category to set
	 */
	public void setCategory(String category) {
		this.category = category;
	}

	/**
	 * Returns the sets.
	 * 
	 * @return sets
	 */
	public ArrayList<String> getSets() {
		return sets;
	}

	/**
	 * Sets the sets.
	 * 
	 * @param sets
	 *            sets to set
	 */
	public void setSets(ArrayList<String> sets) {
		this.sets = sets;
	}

	@Override
	public String toString() {
		return "Item [name=" + name + ", category=" + category + ", sets=" + sets + "]";
	}

}
