package com.saucecode.packer.out;

import java.io.File;
import java.io.IOException;
import java.util.List;

import com.saucecode.packer.xml.Item;

/**
 * Interface for writers.
 * 
 * @author Torben Kr&uuml;ger
 *
 */
public interface IWriter {

	/**
	 * Writes a packing list into a file.
	 * 
	 * @param file
	 *            file to be written
	 * @param selectedItems
	 *            all selected items
	 * @param selectedCategories
	 *            all selected categories
	 * @param selectedSets
	 *            all selected sets
	 * @throws IOException
	 *             if an error occurred during writing file
	 */
	public void write(File file, List<Item> selectedItems, List<String> selectedCategories, List<String> selectedSets)
			throws IOException;

}
