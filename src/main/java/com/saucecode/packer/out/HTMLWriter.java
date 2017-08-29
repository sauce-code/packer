package com.saucecode.packer.out;

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
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;

import org.w3c.tidy.Tidy;

import com.saucecode.packer.xml.Item;

public class HTMLWriter implements IWriter {

	@Override
	public void write(File file, List<Item> selectedItems, List<String> selectedCategories, List<String> selectedSets)
			throws IOException {
		String code =
		//@formatter:off
		html(
		head(
				title("Packliste"),
				link().withRel("stylesheet").withHref("https://www.w3schools.com/w3css/4/w3.css").withType("text/css")
				// TODO add path to existing css
		),
		body(
                h1("Packliste"),
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
		Tidy tidy = new Tidy();
		tidy.setQuiet(true);
		tidy.setShowWarnings(false);
		tidy.setSmartIndent(true);
		tidy.setWraplen(Integer.MAX_VALUE);
		tidy.setInputEncoding(Charset.defaultCharset().name());
		tidy.setOutputEncoding(Charset.defaultCharset().name());
		FileWriter fw = new FileWriter(file);
		tidy.parse(new ByteArrayInputStream(code.getBytes(Charset.defaultCharset())), fw);
	}

}
