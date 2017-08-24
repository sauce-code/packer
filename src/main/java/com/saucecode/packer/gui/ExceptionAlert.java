package com.saucecode.packer.gui;

import java.io.PrintWriter;
import java.io.StringWriter;

import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;

/**
 * An Exception Alert, showing the whole stack trace.
 * 
 * @author Torben Kr&uuml;ger
 *
 */
public class ExceptionAlert extends Alert {

	/**
	 * Creates a new Exception alert.
	 * 
	 * @param e
	 *            exception to be shown
	 */
	public ExceptionAlert(Exception e) {
		super(AlertType.ERROR);
		setTitle("Fehler");
		setHeaderText("Ein Fehler ist aufgetreten.");
		setContentText(e.getMessage());

		// Create expandable Exception.
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		e.printStackTrace(pw);
		String exceptionText = sw.toString();

		TextArea textArea = new TextArea(exceptionText);
		textArea.setEditable(false);
		textArea.setWrapText(true);

		textArea.setMaxWidth(Double.MAX_VALUE);
		textArea.setMaxHeight(Double.MAX_VALUE);
		GridPane.setVgrow(textArea, Priority.ALWAYS);
		GridPane.setHgrow(textArea, Priority.ALWAYS);

		GridPane expContent = new GridPane();
		expContent.setMaxWidth(Double.MAX_VALUE);
		expContent.add(textArea, 0, 0);

		// Set expandable Exception into the dialog pane.
		getDialogPane().setExpandableContent(expContent);
	}

}
