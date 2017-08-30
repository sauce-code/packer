package com.saucecode.packer.gui;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.Test;

public class PathsTest {

	/**
	 * Relative path to resources.
	 */
	private static String PATH_DIR = "src/main/resources/";

	/**
	 * Tests if a file exists, is no directory and is readable.
	 * 
	 * @param path
	 *            path to file
	 */
	private void testFile(String path) {
		File file = new File(PATH_DIR + path);
		assertTrue(file.exists());
		assertFalse(file.isDirectory());
		assertTrue(file.canRead());
	}

	/**
	 * Tests {@link Paths#LOGO}.
	 */
	@Test
	public void testLogo() {
		testFile(Paths.LOGO);
	}

	/**
	 * Tests {@link Paths#CSS}.
	 */
	@Test
	public void testCSS() {
		testFile(Paths.CSS);
	}

}
