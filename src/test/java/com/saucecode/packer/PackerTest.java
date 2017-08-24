package com.saucecode.packer;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.File;

import org.junit.Test;

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
	 * Tests {@link Packer#DEFAULT_LIBRARY_PATH}.
	 */
	@Test
	public void testLogo() {
		testFile(Packer.DEFAULT_LIBRARY_PATH);
	}

}
