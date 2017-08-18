package com.saucecode.packer.core;

import javafx.beans.property.SimpleStringProperty;

public class TableItem {

	private final SimpleStringProperty name;
	
	private final SimpleStringProperty categories;
	
	public TableItem(String name, String categories) {
		super();
		this.name = new SimpleStringProperty(name);
		this.categories = new SimpleStringProperty(categories);
	}

	public String getName() {
		return name.get();
	}

	public String getCategories() {
		return categories.get();
	}
	
}
