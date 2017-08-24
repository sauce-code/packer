package com.saucecode.packer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

/**
 * Represents a packer.
 * 
 * @author Torben Kr&uuml;ger
 *
 */
public class Packer implements IPacker {

	/**
	 * Path to default library.
	 */
	protected static final String DEFAULT_LIBRARY_PATH = "src/main/resources/library.xml";

	/**
	 * All items.
	 */
	protected final ArrayList<Item> items = new ArrayList<Item>();

	/**
	 * All unique categories.
	 */
	protected final ArrayList<String> categories = new ArrayList<String>();

	/**
	 * All unique sets.
	 */
	protected final ArrayList<String> sets = new ArrayList<String>();

	/**
	 * All selected sets.
	 */
	protected final ArrayList<String> selectedSets = new ArrayList<String>();

	/**
	 * Path to library.
	 */
	protected String path;

	/**
	 * Creates a new packer with default library.
	 */
	public Packer() {
		this(DEFAULT_LIBRARY_PATH);
	}

	/**
	 * Creates a new packer with a specified library.
	 * 
	 * @param path
	 *            path to library
	 */
	public Packer(String path) {
		this.path = path;
	}

	@Override
	public List<Item> getItems() {
		return items;
	}

	@Override
	public List<Item> getSelectedItems() {
		ArrayList<Item> selectedItems = new ArrayList<Item>();
		for (Item item : items) {
			boolean contains = false;
			for (String set : item.getSets()) {
				if (selectedSets.contains(set)) {
					contains = true;
				}
			}
			if (contains) {
				selectedItems.add(item);
			}
		}
		return selectedItems;
	}

	@Override
	public List<String> getCategories() {
		return categories;
	}

	@Override
	public List<String> getSelectedCategories() {
		ArrayList<String> selectedCategories = new ArrayList<String>();
		getSelectedItems().forEach(e -> {
			if (!selectedCategories.contains(e.getCategory())) {
				selectedCategories.add(e.getCategory());
			}
		});
		return selectedCategories;
	}

	@Override
	public List<String> getSets() {
		return sets;
	}

	@Override
	public List<String> getSelectedSets() {
		return selectedSets;
	}

	@Override
	public boolean select(String set) {
		return selectedSets.contains(set) ? false : selectedSets.add(set);
	}

	@Override
	public boolean unSelect(String set) {
		return selectedSets.remove(set);
	}

	@Override
	public void read() throws FileNotFoundException, JAXBException {
		items.clear();
		categories.clear();
		sets.clear();

		JAXBContext context = JAXBContext.newInstance(ItemsContainer.class);
		Unmarshaller um = context.createUnmarshaller();
        ItemsContainer itemContainer = (ItemsContainer) um.unmarshal(new FileReader(path));
        items.addAll(itemContainer.getItems());
		
		for (Item item : items) {
			for (String set : item.getSets()) {
				if (!sets.contains(set)) {
					sets.add(set);
				}
			}
		}
		items.forEach(e -> {
			if (!categories.contains(e.getCategory())) {
				categories.add(e.getCategory());
			}
		});
	}

	@Override
	public void write(File file) throws IOException {
		ArrayList<String> lines = new ArrayList<String>();
		items.forEach(e -> lines.add(e.getName() + ", " + e.getCategory()));
		Files.write(java.nio.file.Paths.get(file.getPath()), lines, Charset.forName("UTF-8"));
	}

	@Override
	public String toString() {
		return "Packer [path=" + path + ", items=" + items + ", categories=" + categories + ", sets=" + sets
				+ ", selectedSets=" + selectedSets + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((categories == null) ? 0 : categories.hashCode());
		result = prime * result + ((items == null) ? 0 : items.hashCode());
		result = prime * result + ((path == null) ? 0 : path.hashCode());
		result = prime * result + ((selectedSets == null) ? 0 : selectedSets.hashCode());
		result = prime * result + ((sets == null) ? 0 : sets.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Packer other = (Packer) obj;
		if (categories == null) {
			if (other.categories != null)
				return false;
		} else if (!categories.equals(other.categories))
			return false;
		if (items == null) {
			if (other.items != null)
				return false;
		} else if (!items.equals(other.items))
			return false;
		if (path == null) {
			if (other.path != null)
				return false;
		} else if (!path.equals(other.path))
			return false;
		if (selectedSets == null) {
			if (other.selectedSets != null)
				return false;
		} else if (!selectedSets.equals(other.selectedSets))
			return false;
		if (sets == null) {
			if (other.sets != null)
				return false;
		} else if (!sets.equals(other.sets))
			return false;
		return true;
	}

}
