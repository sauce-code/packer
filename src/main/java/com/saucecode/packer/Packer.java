package com.saucecode.packer;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.xml.sax.SAXException;

import com.saucecode.packer.xml.Item;
import com.saucecode.packer.xml.Items;

/**
 * Represents a packer.
 * 
 * @author Torben Kr&uuml;ger
 *
 */
public class Packer implements IPacker {

	/**
	 * Path to default schema.
	 */
	static final String DEFAULT_SCHEMA_PATH = "src/main/resources/items.xsd";

	/**
	 * Path to default library.
	 */
	static final String DEFAULT_LIBRARY_PATH = "src/main/resources/library.xml";

	/**
	 * All items.
	 */
	private final ArrayList<Item> items = new ArrayList<Item>();

	/**
	 * All unique categories.
	 */
	private final ArrayList<String> categories = new ArrayList<String>();

	/**
	 * All unique sets.
	 */
	private final ArrayList<String> sets = new ArrayList<String>();

	/**
	 * All selected sets.
	 */
	private final ArrayList<String> selectedSets = new ArrayList<String>();

	/**
	 * Path to library.
	 */
	private String path;

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
			for (String set : item.getSets().getSet()) {
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
	public void read() throws JAXBException, SAXException, IOException {

		// clear lists
		items.clear();
		categories.clear();
		sets.clear();

		// read schema file
		File schemaFile = new File(DEFAULT_SCHEMA_PATH);
		Source xmlFile = new StreamSource(new File(DEFAULT_LIBRARY_PATH));
		SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
		Schema schema = schemaFactory.newSchema(schemaFile);

		// validate library
		Validator validator = schema.newValidator();
		validator.validate(xmlFile);

		// read xml file
		JAXBContext context = JAXBContext.newInstance(Items.class);
		Unmarshaller um = context.createUnmarshaller();
		Items items = (Items) um.unmarshal(new FileReader(path));

		// buffer items
		this.items.addAll(items.getItem());

		// buffer sets
		for (Item item : this.items) {
			for (String set : item.getSets().getSet()) {
				if (!sets.contains(set)) {
					sets.add(set);
				}
			}
		}

		// buffer categories
		this.items.forEach(e -> {
			if (!categories.contains(e.getCategory())) {
				categories.add(e.getCategory());
			}
		});

	}

	@Override
	public void write(File file) throws IOException {
		// TODO schöner machen
		ArrayList<String> lines = new ArrayList<String>();
		lines.add("Packliste erstellt am " + LocalDateTime.now());
		getSelectedCategories().forEach(c -> {
			lines.add("");
			lines.add(c);
			getSelectedItems().forEach(i -> {
				if (i.getCategory().equals(c)) {
					lines.add("- " + i.getName());
				}
			});
		});
		Files.write(java.nio.file.Paths.get(file.getPath()), lines, Charset.defaultCharset());
	}

	@Override
	public String toString() {
		return "Packer [path=" + path + ", items=" + items + ", categories=" + categories + ", sets=" + sets
				+ ", selectedSets=" + selectedSets + "]";
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

}
