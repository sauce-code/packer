package com.saucecode.packer.gui;

import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.StageStyle;

/**
 * A about info alert box.
 * 
 * @author Torben Kr&uuml;ger
 *
 */
public class AboutAlert extends Alert {

	/**
	 * Creates a new About Alert.
	 * 
	 * @param logo
	 *            the icon, which shall be used
	 */
	public AboutAlert(Image logo) {
		super(AlertType.INFORMATION);
		ImageView image = new ImageView(logo);
		setGraphic(image);
		initStyle(StageStyle.UTILITY);
		setTitle("Info");
		setHeaderText(MetaInfo.TITLE + " " + MetaInfo.VERSION);
		//@formatter:off
		setContentText(
				"Depot: " + MetaInfo.REPOSITORY + '\n'  +
				"Lizenz: " + MetaInfo.LICENSE + '\n'  +
				'\n'  +
				"Autor: " + MetaInfo.AUTHOR + '\n' + 
				"Email: " + MetaInfo.EMAIL + '\n' +
				'\n'  +
				"Logo: " + MetaInfo.LOGO_ARTIST + '\n' +
				"HTML Generaor: " + MetaInfo.HTML_GENERAOR  + '\n' +
				"HTML Formatierer: " + MetaInfo.HTML_FORMATTER  + '\n' +
				""); 
		//@formatter:on
	}

}
