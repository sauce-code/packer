package com.saucecode.packer.gui;

import javafx.beans.property.SimpleStringProperty;

/**
 * Represents a table item.
 * 
 * @author Torben Kr&uuml;ger
 *
 */
public class TableItem {

	/**
	 * The name.
	 */
	private final SimpleStringProperty name;

	/**
	 * The categories.
	 */
	private final SimpleStringProperty category;

	/**
	 * Creates a new table item.
	 * 
	 * @param name
	 *            name
	 * @param categories
	 *            categories
	 */
	public TableItem(String name, String category) {
		super();
		this.name = new SimpleStringProperty(name);
		this.category = new SimpleStringProperty(category);
	}

	/**
	 * Returns the name.
	 * 
	 * @return name
	 */
	public String getName() {
		return name.get();
	}

	/**
	 * Returns the category.
	 * 
	 * @return category
	 */
	public String getCategory() {
		return category.get();
	}

}
