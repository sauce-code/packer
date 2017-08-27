package com.saucecode.packer;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;

import javax.xml.bind.JAXBException;

import org.junit.Test;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

/**
 * Tests the class {@link Packer}.
 * 
 * @author Torben Kr&uuml;ger
 *
 */
public class PackerTest {

	/**
	 * Tests if a file exists, is no directory and is readable.
	 * 
	 * @param path
	 *            path to file
	 */
	private void testFile(String path) {
		File file = new File(path);
		assertTrue(file.exists());
		assertFalse(file.isDirectory());
		assertTrue(file.canRead());
	}

	/**
	 * Tests {@link Packer#DEFAULT_SCHEMA_PATH}.
	 */
	@Test
	public void testSchemaPath() {
		testFile(Packer.DEFAULT_SCHEMA_PATH);
	}
	
	/**
	 * Tests {@link Packer#DEFAULT_LIBRARY_PATH}.
	 */
	@Test
	public void testLibraryPath() {
		testFile(Packer.DEFAULT_LIBRARY_PATH);
	}
	
	@Test(expected = JAXBException.class)
	public void testReadInvalid1() throws JAXBException, SAXException, IOException {
		Packer packer = new Packer("src/test/resources/invalid1.xml");
		packer.read();
	}
	
	@Test(expected = SAXParseException.class)
	public void testReadInvalid2() throws JAXBException, SAXException, IOException {
		Packer packer = new Packer("src/test/resources/invalid2.xml");
		packer.read();
	}
	
	@Test(expected = SAXException.class)
	public void testReadInvalid3() throws JAXBException, SAXException, IOException {
		Packer packer = new Packer("src/test/resources/invalid3.xml");
		packer.read();
	}
	
	@Test(expected = SAXException.class)
	public void testReadInvalid4() throws JAXBException, SAXException, IOException {
		Packer packer = new Packer("src/test/resources/invalid4.xml");
		packer.read();
	}
	
	@Test(expected = SAXException.class)
	public void testReadInvalid5() throws JAXBException, SAXException, IOException {
		Packer packer = new Packer("src/test/resources/invalid5.xml");
		packer.read();
	}
	
	@Test(expected = SAXException.class)
	public void testReadInvalid6() throws JAXBException, SAXException, IOException {
		Packer packer = new Packer("src/test/resources/invalid6.xml");
		packer.read();
	}

}
