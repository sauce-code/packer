package com.saucecode.packer;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.xml.bind.JAXBException;

import org.xml.sax.SAXException;

import com.saucecode.packer.xml.Item;

/**
 * Declares all public methods for packer.
 * 
 * @author Torben Kr&uuml;ger
 * 
 * @since Packer 1.0
 *
 */
public interface IPacker {

	/**
	 * Returns all available items.
	 * 
	 * @return all available items
	 * 
	 * @since Packer 1.0
	 */
	public List<Item> getItems();

	/**
	 * Returns all selected items.
	 * 
	 * @return all selected items
	 * 
	 * @since Packer 1.0
	 */
	public List<Item> getSelectedItems();

	/**
	 * Returns all categories.
	 * 
	 * @return all categories
	 * 
	 * @since Packer 1.0
	 */
	public List<String> getCategories();

	/**
	 * Returns all selected categories.<br>
	 * A category is selected, if <b>at least 1 element</b> of this category is
	 * selected.
	 * 
	 * @return all selected categories
	 * 
	 * @since Packer 1.0
	 */
	public List<String> getSelectedCategories();

	/**
	 * Returns all available sets.
	 * 
	 * @return all available sets
	 * 
	 * @since Packer 1.0
	 */
	public List<String> getSets();

	/**
	 * Returns all selected sets.
	 * 
	 * @return all selected sets
	 * 
	 * @since Packer 1.0
	 */
	public List<String> getSelectedSets();

	/**
	 * Selects a set.
	 * 
	 * @param set
	 *            set to select
	 * @return {@code true}, if successful
	 * 
	 * @since Packer 1.0
	 */
	public boolean select(String set);

	/**
	 * Unselects a set.
	 * 
	 * @param set
	 *            set to unselect
	 * @return {@code true}, if successful
	 * 
	 * @since Packer 1.0
	 */
	public boolean unSelect(String set);

	/**
	 * Reads the library and buffers it.
	 * 
	 * @throws JAXBException
	 *             if the library file has defective syntax
	 * @throws SAXException
	 *             if the read library is not valid
	 * @throws IOException
	 *             if schema file or library could not bee read
	 * 
	 * @since Packer 1.0
	 */
	public void read() throws JAXBException, SAXException, IOException;

	/**
	 * Writes the customized packing list into a file.
	 * 
	 * @param file
	 *            destination
	 * @throws IOException
	 *             if an error occurred during writing
	 * 
	 * @since Packer 1.0
	 */
	public void write(File file) throws IOException;

}
