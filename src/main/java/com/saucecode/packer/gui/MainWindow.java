package com.saucecode.packer.gui;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.controlsfx.control.StatusBar;

import com.saucecode.packer.IObserver;
import com.saucecode.packer.Packer;
import com.saucecode.packer.xml.Item;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * The main window for packer application.
 * 
 * @author Torben Kr&uuml;ger
 *
 */
public class MainWindow extends Application implements IObserver {

	/**
	 * The main stage.
	 */
	private Stage stage;

	/**
	 * The icon used for this program.
	 */
	private final Image logo = new Image(Paths.LOGO);

	/**
	 * The packer for reading the items.
	 */
	private final Packer packer = new Packer();

	/**
	 * The TableItems being shown.
	 */
	private final ObservableList<TableItem> tableItems = FXCollections.observableArrayList();

	/**
	 * Statusbar, showing useless information.
	 */
	private final StatusBar statusBar = new StatusBar();

	/**
	 * Shows all selected items.
	 */
	private final TableView<TableItem> table = new TableView<TableItem>();

	/**
	 * Creates the menu bar.
	 * 
	 * @return initialized menu bar
	 */
	private MenuBar initMenuBar() {

		// =========================================================================================
		// ==== HELP MENU
		// =========================================================================================

		MenuItem about = new MenuItem("Inf_o");
		about.setOnAction(e -> new AboutAlert(logo).showAndWait());

		Menu menuHelp = new Menu("_Hilfe", null, about);

		// =========================================================================================
		// ======= FILE MENU
		// =========================================================================================

		MenuItem save = new MenuItem("Speichern _unter...");
		save.setOnAction(e -> {
			if (packer.getSelectedItems().size() == 0) {
				new ErrorAlert("Die Packliste ist leer!").showAndWait();
			} else {
				FileChooser fileChooser = new FileChooser();
				fileChooser.setTitle("Speichern unter");
				FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Textdateien (*.txt)", "*.txt");
				fileChooser.getExtensionFilters().add(extFilter);
				fileChooser.setInitialFileName("*.txt");
				File file = fileChooser.showSaveDialog(stage);
				if (file != null) {
					try {
						packer.write(file);
					} catch (IOException ex) {
						new ExceptionAlert(ex).showAndWait();
					}
				}
			}
		});

		MenuItem exit = new MenuItem("B_eenden");
		exit.setAccelerator(KeyCombination.keyCombination("Alt+F4"));
		exit.setOnAction(e -> Platform.exit());

		Menu menuFile = new Menu("_Datei", null, save, new SeparatorMenuItem(), exit);

		// =========================================================================================
		// ======= BUILDING MENUBAR
		// =========================================================================================

		MenuBar ret = new MenuBar(menuFile, menuHelp);
		ret.setUseSystemMenuBar(true);
		ret.useSystemMenuBarProperty().set(true);
		return ret;
	}

	@Override
	public void start(Stage primaryStage) throws Exception {

		try {
			packer.read();
		} catch (Exception e) {
			new ExceptionAlert(e).showAndWait();
			System.exit(0);
		}
		
		packer.attatch(this);

		stage = primaryStage;
		VBox checkBoxes = new VBox();

		for (String set : packer.getSets()) {
			CheckBox checkBox = new CheckBox(set);
			checkBox.setPadding(new Insets(3.0, 6.0, 3.0, 6.0));
			checkBoxes.getChildren().add(checkBox);
			checkBox.selectedProperty().addListener(new ChangeListener<Boolean>() {
				@Override
				public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
					if (newValue) {
						packer.select(set);
					} else {
						packer.unSelect(set);
					}
				}
			});
		}

		table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		TableColumn<TableItem, String> name = new TableColumn<TableItem, String>("Name");
		name.setCellValueFactory(new PropertyValueFactory<TableItem, String>("name"));
		TableColumn<TableItem, String> category = new TableColumn<TableItem, String>("Kategorie");
		category.setCellValueFactory(new PropertyValueFactory<TableItem, String>("category"));
		table.setItems(tableItems);
		table.getColumns().add(name);
		table.getColumns().add(category);
		table.getSortOrder().add(category);
		table.setPrefSize(400.0, 600.0);

		alert();

		BorderPane border = new BorderPane(table, initMenuBar(), null, statusBar, checkBoxes);

		Scene scene = new Scene(border);
		primaryStage.setTitle(MetaInfo.TITLE);
		primaryStage.setScene(scene);
		primaryStage.setResizable(true);
		primaryStage.getIcons().add(logo);

		primaryStage.show();
	}

	/**
	 * runs the application.
	 * 
	 * @param args
	 *            parameters, unused
	 */
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void alert() {
		// refresh table
		tableItems.clear();
		List<Item> items = packer.getSelectedItems();
		items.forEach(e -> tableItems.add(new TableItem(e.getName(), e.getCategory())));
		table.sort();

		// refresh status bar
		StringBuilder sb = new StringBuilder();
		if (packer.getSelectedCategories().size() == 0) {
			sb.append("");
		} else {
			sb.append(packer.getSelectedItems().size());
			sb.append(' ');
			sb.append((packer.getSelectedItems().size() == 1) ? "Gegenstand" : "Gegenstände");
			sb.append(" in ");
			sb.append(packer.getSelectedCategories().size());
			sb.append(' ');
			sb.append((packer.getSelectedCategories().size() == 1) ? "Kategorie" : "Kategorien");
		}
		statusBar.setText(sb.toString());
	}

}
