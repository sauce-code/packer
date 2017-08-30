package com.saucecode.packer.out;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

import com.saucecode.packer.xml.Item;

public class TXTWriter implements IWriter {

	@Override
	public void write(File file, List<Item> selectedItems, List<String> selectedCategories, List<String> selectedSets)
			throws IOException {

		List<String> lines = new LinkedList<String>();

		// first line
		StringBuilder sb = new StringBuilder();
		sb.append("Packliste für ");
		Iterator<String> iter = selectedSets.iterator();
		int current = 0;
		while (iter.hasNext()) {
			sb.append(iter.next());
			current++;
			if (current == selectedSets.size() - 1) {
				sb.append(" und ");
			} else if (current != selectedSets.size()) {
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
		selectedCategories.forEach(c -> {
			lines.add("");
			lines.add(c);
			selectedItems.forEach(i -> {
				if (i.getCategory().equals(c)) {
					lines.add("- " + i.getName());
				}
			});
		});

		// write file
		Files.write(java.nio.file.Paths.get(file.getPath()), lines, Charset.defaultCharset());
	}

}
