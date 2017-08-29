package com.saucecode.packer.out;

import java.io.File;
import java.io.IOException;
import java.util.List;

import com.saucecode.packer.xml.Item;

public interface IWriter {

	public void write(File file, List<Item> selectedItems, List<String> selectedCategories, List<String> selectedSets)
			throws IOException;

}
