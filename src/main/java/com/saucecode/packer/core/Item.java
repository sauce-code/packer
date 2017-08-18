package com.saucecode.packer.core;

import java.util.ArrayList;

public class Item {

	private String name;

	private ArrayList<String> categories;

	public Item(String name, ArrayList<String> categories) {
		super();
		this.name = name;
		this.categories = categories;
	}

	public String getName() {
		return name;
	}

	public ArrayList<String> getCategories() {
		return categories;
	}

}
