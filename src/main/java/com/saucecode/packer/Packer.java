package com.saucecode.packer;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
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

import org.apache.commons.io.FilenameUtils;
import org.xml.sax.SAXException;

import com.saucecode.packer.out.HTMLWriter;
import com.saucecode.packer.out.TXTWriter;
import com.saucecode.packer.xml.Item;
import com.saucecode.packer.xml.Library;

/**
 * Represents a packer.
 * 
 * @author Torben Kr&uuml;ger
 *
 */
public class Packer extends Observable implements IPacker {

	/**
	 * Path to default schema.
	 */
	static final String DEFAULT_SCHEMA_PATH = "src/main/resources/library.xsd";

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
		super();
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
		if (selectedSets.contains(set)) {
			return false;
		} else {
			selectedSets.add(set);
			alertAll();
			return true;
		}
	}

	@Override
	public boolean unSelect(String set) {
		if (selectedSets.remove(set)) {
			alertAll();
			return true;
		} else {
			return false;
		}
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
		JAXBContext context = JAXBContext.newInstance(Library.class);
		Unmarshaller um = context.createUnmarshaller();
		Library library = (Library) um.unmarshal(new FileReader(path));

		// buffer objects
		items.addAll(library.getItems().getItem());
		sets.addAll(library.getSets().getSet());
		categories.addAll(library.getCategories().getCategory());

	}

	@Override
	public void write(File file) throws IOException {
		switch (FilenameUtils.getExtension(file.getName()).toLowerCase()) {
		case "txt":
			new TXTWriter().write(file, getSelectedItems(), getSelectedCategories(), getSelectedSets());
			break;
		case "htm":
		case "html":
			new HTMLWriter().write(file, getSelectedItems(), getSelectedCategories(), getSelectedSets());
			break;
		default:
			throw new IOException("nicht unterstützter Dateityp");
		}
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

	@Override
	public String toString() {
		return "Packer [items=" + items + ", categories=" + categories + ", sets=" + sets + ", selectedSets="
				+ selectedSets + ", path=" + path + "]";
	}

}
