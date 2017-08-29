package com.saucecode.packer;

import static j2html.TagCreator.body;
import static j2html.TagCreator.div;
import static j2html.TagCreator.each;
import static j2html.TagCreator.filter;
import static j2html.TagCreator.h1;
import static j2html.TagCreator.h2;
import static j2html.TagCreator.head;
import static j2html.TagCreator.html;
import static j2html.TagCreator.li;
import static j2html.TagCreator.link;
import static j2html.TagCreator.title;
import static j2html.TagCreator.ul;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

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
import org.w3c.tidy.Tidy;
import org.xml.sax.SAXException;

import com.saucecode.packer.xml.Item;
import com.saucecode.packer.xml.Items;

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
		ArrayList<String> lines = new ArrayList<String>();
		switch (FilenameUtils.getExtension(file.getName())) {
		case "txt":
			// first line
			StringBuilder sb = new StringBuilder();
			sb.append("Packliste für ");
			Iterator<String> iter = getSelectedSets().iterator();
			int current = 0;
			while (iter.hasNext()) {
				sb.append(iter.next());
				current++;
				if (current == getSelectedSets().size() - 1) {
					sb.append(" und ");
				} else if (current != getSelectedSets().size()) {
					sb.append(", ");
				}
			}
			lines.add(sb.toString());

			// second line
			Date date = new Date();
			SimpleDateFormat day = new SimpleDateFormat("d. MMMM YYYY", Locale.GERMANY);
			SimpleDateFormat time = new SimpleDateFormat("HH:mm", Locale.GERMANY);
			lines.add("erstellt am " + day.format(date).toString() + " um " + time.format(date).toString() + " Uhr");

			// all categories + items
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
			break;
		case "htm":
		case "html":
			lines.add(
			//@formatter:off
			html(
			head(
					title("Packliste"),
					link().withRel("stylesheet").withHref("/css/main.css").withType("text/css")
			),
			body(
	                h1("Packliste"),
	                each(getSelectedCategories(), category ->
	                	div(
	                		h2(category),
	                		ul(
	                			each(filter(getSelectedItems(), item -> item.getCategory().equals(category)), item ->
	                				li(item.getName())
	                			)
	                		)
	                	)
	                )
	            )
			).render());
			//@formatter:on
			Tidy tidy = new Tidy();
			tidy.setQuiet(true);
			tidy.setShowWarnings(false);
			tidy.setSmartIndent(true);
			tidy.setWraplen(Integer.MAX_VALUE);
			tidy.setInputEncoding(Charset.defaultCharset().name());
			tidy.setOutputEncoding(Charset.defaultCharset().name());
			FileWriter fw = new FileWriter(file);
			tidy.parse(new ByteArrayInputStream(lines.get(0).getBytes(Charset.defaultCharset())), fw);
			break;
		default:
			throw new IOException("nicht unterstützter Dateityp");
		}
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
