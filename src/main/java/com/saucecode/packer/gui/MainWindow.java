package com.saucecode.packer.gui;

import java.util.ArrayList;

import com.saucecode.packer.core.Item;
import com.saucecode.packer.core.Packer;
import com.saucecode.packer.core.TableItem;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Toggle;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainWindow extends Application {

	/**
	 * The icon used for this program.
	 */
	private final Image logo = new Image(Paths.LOGO);

	private final Packer packer = new Packer();

	private final ObservableList<TableItem> tableItems = FXCollections.observableArrayList();

	@Override
	public void start(Stage primaryStage) throws Exception {
		VBox categoriesCheck = new VBox();

		for (String cat : packer.getCategories()) {
			CheckBox checkBox = new CheckBox(cat);
			categoriesCheck.getChildren().add(checkBox);
			checkBox.selectedProperty().addListener(new ChangeListener<Boolean>() {
				@Override
				public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
					ArrayList<String> categories = new ArrayList<String>();
					for (Node node : categoriesCheck.getChildren()) {
						if (((CheckBox) node).isSelected()) {
							categories.add(((CheckBox) node).getText());
						}
					}
					refreshTable(categories);
				}
			});
		}

		TableView<TableItem> table = new TableView<TableItem>();
		TableColumn<TableItem, String> name = new TableColumn<TableItem, String>("name");
		name.setCellValueFactory(new PropertyValueFactory<TableItem, String>("name"));
		TableColumn<TableItem, String> categories = new TableColumn<TableItem, String>("categories");
		categories.setCellValueFactory(new PropertyValueFactory<TableItem, String>("categories"));
		table.setItems(tableItems);
		table.getColumns().add(name);
		table.getColumns().add(categories);

		HBox hBox = new HBox(categoriesCheck, table);

		BorderPane border = new BorderPane(hBox);
		border.setTop(initMenuBar());

		Scene scene = new Scene(border);
		primaryStage.setTitle(MetaInfo.TITLE);
		primaryStage.setScene(scene);
		primaryStage.setResizable(true);
		primaryStage.getIcons().add(logo);

		primaryStage.show();
	}

	private MenuBar initMenuBar() {

		// =========================================================================================
		// ==== HELP MENU
		// =========================================================================================

		MenuItem about = new MenuItem("A_bout");
		about.setOnAction(e -> new AboutAlert(logo).showAndWait());

		Menu menuHelp = new Menu("_Help", null, new SeparatorMenuItem(), about);

		// =========================================================================================
		// ======= FILE MENU
		// =========================================================================================

		MenuItem exit = new MenuItem("E_xit");
		exit.setAccelerator(KeyCombination.keyCombination("Alt+F4"));
		exit.setOnAction(e -> Platform.exit());

		Menu menuFile = new Menu("_File", null, new SeparatorMenuItem(), exit);

		// =========================================================================================
		// ======= BUILDING MENUBAR
		// =========================================================================================

		MenuBar ret = new MenuBar(menuFile, menuHelp);
		ret.setUseSystemMenuBar(true);
		ret.useSystemMenuBarProperty().set(true);
		return ret;
	}

	public static void main(String[] args) {
		launch(args);
	}

	private void refreshTable(ArrayList<String> selectedCategories) {
		tableItems.clear();
		ArrayList<Item> items = packer.getItems();
		for (Item item : items) {
			boolean contains = false;
			for (String category : item.getCategories()) {
				if (selectedCategories.contains(category)) {
					contains = true;
				}
			}
			if (contains) {
				StringBuilder sb = new StringBuilder();
				for (String category : item.getCategories()) {
					if (selectedCategories.contains(category)) {
						sb.append(category + ", ");
					}
				}
				sb.delete(sb.length() - 2, sb.length() - 1);
				tableItems.add(new TableItem(item.getName(), sb.toString()));
			}
		}
	}

}
