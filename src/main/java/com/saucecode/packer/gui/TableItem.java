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
	 * The description.
	 */
	private final SimpleStringProperty description;

	/**
	 * Creates a new table item.
	 * 
	 * @param name
	 *            name
	 * @param categories
	 *            categories
	 */
	public TableItem(String name, String category, String description) {
		super();
		this.name = new SimpleStringProperty(name);
		this.category = new SimpleStringProperty(category);
		this.description = new SimpleStringProperty(description);
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

	/**
	 * Returns the description.
	 * 
	 * @return description
	 */
	public String getDescription() {
		return description.get();
	}

}
