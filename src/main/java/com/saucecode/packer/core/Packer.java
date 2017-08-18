package com.saucecode.packer.core;

import java.util.ArrayList;

import com.saucecode.packer.in.Reader;

public class Packer {

	public static final String LIBRARY_PATH_DEFAULT = "";
	
	private ArrayList<Item> items;

	private ArrayList<String> categories;

	public Packer() {
		this(LIBRARY_PATH_DEFAULT);
	}
	
	public Packer(String path) {
		Reader reader = new Reader(path);
		items = reader.read();
		categories = new ArrayList<String>();
		for (Item item : items) {
			for (String category : item.getCategories()) {
				if (!categories.contains(category)) {
					categories.add(category);
				}
			}
		}
	}

	public ArrayList<String> getCategories() {
		ArrayList<String> clone = new ArrayList<String>();
		categories.forEach(e -> clone.add(new String(e)));
		return clone;
	}

	@SuppressWarnings("unchecked")
	public ArrayList<Item> getItems() {
		return (ArrayList<Item>) items.clone();
	}

}
