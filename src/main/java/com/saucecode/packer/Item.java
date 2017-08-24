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

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Item other = (Item) obj;
		if (category == null) {
			if (other.category != null)
				return false;
		} else if (!category.equals(other.category))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (sets == null) {
			if (other.sets != null)
				return false;
		} else if (!sets.equals(other.sets))
			return false;
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((category == null) ? 0 : category.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((sets == null) ? 0 : sets.hashCode());
		return result;
	}

}
