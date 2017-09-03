package com.saucecode.packer.out;

import static j2html.TagCreator.*;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import org.w3c.tidy.Tidy;

import com.saucecode.packer.xml.Item;

/**
 * Writes pack lists to HTML files.
 * 
 * @author Torben Kr&uuml;ger
 *
 */
public class HTMLWriter implements IWriter {

	@Override
	public void write(File file, List<Item> selectedItems, List<String> selectedCategories, List<String> selectedSets)
			throws IOException {

		// first line
		StringBuilder sb = new StringBuilder();
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

		// second line
		Date date = new Date();
		SimpleDateFormat day = new SimpleDateFormat("d. MMMM YYYY", Locale.GERMANY);
		SimpleDateFormat time = new SimpleDateFormat("HH:mm", Locale.GERMANY);

		// generate code
		String html =
		//@formatter:off
		html(
		head(
				title("Packliste"),
				link().withRel("stylesheet").withHref("https://www.w3schools.com/w3css/4/w3.css").withType("text/css")
				// TODO add path to existing css
		),
		body(
                h1("Packliste"),
                text(sb.toString()),
                br(),
                text("erstellt am " + day.format(date).toString() + " um " + time.format(date).toString() + " Uhr"),
                each(selectedCategories, category ->
                	div(
                		h2(category),
                		ul(
                			each(filter(selectedItems, item -> item.getCategory().equals(category)), item ->
                				li(item.getName())
                			)
                		)
                	)
                )
            )
		).render();
		//@formatter:on

		// set up tidy
		Tidy tidy = new Tidy();
		tidy.setQuiet(true);
		tidy.setShowWarnings(false);
		tidy.setSmartIndent(true);
		tidy.setWraplen(Integer.MAX_VALUE);
		tidy.setInputEncoding(Charset.defaultCharset().name());
		tidy.setOutputEncoding(Charset.defaultCharset().name());

		// write formatted html code to file
		tidy.parse(new ByteArrayInputStream(html.getBytes(Charset.defaultCharset())), new FileWriter(file));
	}

}
