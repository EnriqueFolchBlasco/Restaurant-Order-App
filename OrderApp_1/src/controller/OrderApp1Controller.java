package controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.ResourceBundle;

import javafx.animation.TranslateTransition;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.util.Duration;
import models.Product;
import models.Table;

/*
╔═══╗╔╗─╔╗╔═══╗╔══╗╔═══╗╔╗╔╗╔═══╗───╔══╗╔══╗╔╗──╔══╗╔╗╔╗───╔══╗─╔╗──╔══╗╔══╗╔══╗╔══╗
║╔══╝║╚═╝║║╔═╗║╚╗╔╝║╔═╗║║║║║║╔══╝───║╔═╝║╔╗║║║──║╔═╝║║║║───║╔╗║─║║──║╔╗║║╔═╝║╔═╝║╔╗║
║╚══╗║╔╗─║║╚═╝║─║║─║║─║║║║║║║╚══╗───║╚═╗║║║║║║──║║──║╚╝║───║╚╝╚╗║║──║╚╝║║╚═╗║║──║║║║
║╔══╝║║╚╗║║╔╗╔╝─║║─║║╔╝║║║║║║╔══╝───║╔═╝║║║║║║──║║──║╔╗║───║╔═╗║║║──║╔╗║╚═╗║║║──║║║║
║╚══╗║║─║║║║║║─╔╝╚╗║╚╝─║║╚╝║║╚══╗───║║──║╚╝║║╚═╗║╚═╗║║║║───║╚═╝║║╚═╗║║║║╔═╝║║╚═╗║╚╝║
╚═══╝╚╝─╚╝╚╝╚╝─╚══╝╚═══╝╚══╝╚═══╝───╚╝──╚══╝╚══╝╚══╝╚╝╚╝───╚═══╝╚══╝╚╝╚╝╚══╝╚══╝╚══╝
 */

public class OrderApp1Controller implements Initializable {

	@FXML
	private Button button_OpenTable;

	@FXML
	private Button button_CleanTable;

	@FXML
	private Button button_DownType;

	@FXML
	private Button button_UpType;

	@FXML
	private Button button_add;

	@FXML
	private Button button_addTable;

	@FXML
	private Button button_addType;

	@FXML
	private Button button_remove;

	@FXML
	private Button button_removeType;

	@FXML
	private Button button_removeTable;

	@FXML
	private Button button_update;

	@FXML
	private ChoiceBox<String> choiceBox;

	@FXML
	private ListView<String> listView;

	@FXML
	private ListView<Table> tablesList;

    @FXML
    private ListView<Product> ticket_list;

	@FXML
	private ListView<String> typesList;
	
	@FXML
	private TextArea textArea_desc;

	@FXML
	private TextField textfield_name;

	@FXML
	private TextField textfield_price;

	@FXML
	private TextField textfield_type;

	@FXML
	private TextArea warnings_textArea;

	@FXML
	private Button button_updateItem;

	@FXML
	private Text table_id;

	@FXML
	private Pane table_pane;

	@FXML
	private Button close_paneTable;


	public static ArrayList<String> listaElementos = new ArrayList<String>();

	public static ArrayList<String> listaTipos = new ArrayList<String>();

	public static ArrayList<Product> listaProducts = new ArrayList<Product>();

	public static ArrayList<Table> listaMesas = new ArrayList<Table>();

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		listView.getItems().add("Añadir un nuevo producto");
		listaElementos.add("Añadir un nuevo producto");
		//choiceBoxSetUp();


		//Main buttons
		button_add.setOnMouseClicked((event) -> anadirElemento(textfield_name.getText(), textfield_price.getText(), choiceBox.getSelectionModel().getSelectedItem(), textArea_desc.getText()));
		button_update.setOnMouseClicked((event) -> updateDB());
		
		//Type button
		button_addType.setOnMouseClicked((event) -> anadirTipo(textfield_type.getText()));
		
		//Tables button
		button_addTable.setOnMouseClicked((event) -> anadirMesa());
		
		//Items lista
		listView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {

				if (newValue == null) {
					return;
				}

				if (newValue.equals("Añadir un nuevo producto")) {
					button_updateItem.setVisible(false);
					button_updateItem.setDisable(true);
					button_add.setVisible(true);
					button_add.setDisable(false);

					textfield_name.setText(null);
					textfield_price.setText(null);
					textArea_desc.setText(null);
					choiceBox.setValue(null);

				} else {
					button_updateItem.setVisible(true);
					button_updateItem.setDisable(false);
					button_add.setVisible(false);
					button_add.setDisable(true);
					warnings_textArea.clear();

					itemSeleccionado(newValue);                
					button_remove.setOnMouseClicked((event) -> quitarElemento(newValue));
					button_updateItem.setOnMouseClicked((event) -> itemUpdate(newValue));
				}
			}    
		});
		
		//Tipos lista
		typesList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {

				if (newValue == null) {
					return;
				}

				button_removeType.setOnMouseClicked((event) -> quitarTipo(newValue));

				button_UpType.setOnMouseClicked((event) -> cambiarOrden(newValue, 1));
				button_DownType.setOnMouseClicked((event) -> cambiarOrden(newValue, 2));


			}    
		});
		
		//Mesas lista
		tablesList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Table>() {
			
			@Override
			public void changed(ObservableValue<? extends Table> arg0, Table arg1, Table arg2) {
				button_removeTable.setOnMouseClicked((event) -> quitarMesa());
				button_OpenTable.setOnMouseClicked((event) -> showPaneFromRight(arg2, table_pane, 1));
			}    
		});
		
		//Table pane button
		close_paneTable.setOnMouseClicked((event) -> hidePaneToRight(table_pane, 1));
		
		
	}

	public void quitarMesa() {

		Table selectedTable = tablesList.getSelectionModel().getSelectedItem();

		if (selectedTable != null) {

			listaMesas.remove(selectedTable);

			tablesList.getItems().clear();
			tablesList.getItems().addAll(listaMesas);
		}

	}

	public void anadirMesa() {

		Table mesa = new Table(listaMesas.size() + 1, null, 0);
		listaMesas.add(mesa);
		tablesList.getItems().clear();
		tablesList.getItems().addAll(listaMesas);
	}

	public void cambiarOrden(String tipo, int way) {
		int index = listaTipos.indexOf(tipo);

		if (index == -1) {

			return;
		}

		if (way == 1 && index > 0) {

			Collections.swap(listaTipos, index, index - 1);
		} else if (way == 2 && index < listaTipos.size() - 1) {

			Collections.swap(listaTipos, index, index + 1);
		}

		typesList.getItems().clear();
		typesList.getItems().addAll(listaTipos);
	}

	public void itemUpdate(String nom) {
		for (Product producte : listaProducts) {
			if (producte.getName().equals(nom)) {
				StringBuilder mensageError = new StringBuilder();

				if (textfield_name.getText() == null || textfield_name.getText().isEmpty()) {
					mensageError.append("Warning: Name field empty.\n");
				}

				if (textfield_price.getText() == null || textfield_price.getText().isEmpty()) {
					mensageError.append("Warning: Price field empty.\n");
				}

				if (choiceBox.getValue() == null || choiceBox.getValue().isEmpty()) {
					mensageError.append("Warning: Type empty.\n");
				}

				if (textArea_desc.getText() == null || textArea_desc.getText().isEmpty()) {
					mensageError.append("Warning: Description empty.\n");
				}

				if (mensageError.length() == 0) {

					producte.setName(textfield_name.getText());  
					producte.setPrice(textfield_price.getText()); 
					producte.setDescription(textArea_desc.getText());
					producte.setType(choiceBox.getValue()); 

					int index = listaElementos.indexOf(nom);

					if (index != -1) {
						listaElementos.set(index, producte.getName()); 
					}

					listView.setItems(FXCollections.observableArrayList(listaElementos));
					break;

				} else {

					warnings_textArea.setText(mensageError.toString());
				}
			}
		}
	}

	public void itemSeleccionado(String nom1) {

		if (nom1 == null || nom1.isEmpty()) {
			return;
		}

		for (Product producte : listaProducts) {
			if (producte.getName().equals(nom1)) {

				textfield_name.setText(producte.getName());
				textfield_price.setText(producte.getPrice());
				textArea_desc.setText(producte.getDescription());
				choiceBox.setValue(producte.getType());
				break;
			}
		}

	}

	public void anadirTipo(String tipo) {
		if (tipo != null) {
			listaTipos.add(tipo);
			textfield_type.clear();
			choiceBox.getItems().clear();
			choiceBox.getItems().addAll(listaTipos);
			typesList.getItems().clear();
			typesList.getItems().addAll(listaTipos);
		}
	}

	public void quitarTipo(String tipo) {
		boolean prohibido = true;
		StringBuilder mensageError = new StringBuilder();


		if (listaTipos.contains(tipo)) {


			for (Product producte : listaProducts) {

				if (producte.getType().equals(tipo)) {
					prohibido = false;
					mensageError.append("Warning: A product has this type, can't \n be deleted.\n");
					warnings_textArea.setText(mensageError.toString());

					break;
				}


			}

			if (prohibido) {
				listaTipos.remove(tipo);
				choiceBox.getItems().clear();
				choiceBox.getItems().addAll(listaTipos);

				typesList.getItems().clear();
				typesList.getItems().addAll(listaTipos);
			}



		}
	}

	public void quitarElemento(String elemento) {
		if (listView.getItems().contains(elemento)) {


			//Fix per a ConcurrentModificationException, un iterator.
			Iterator<Product> iterator = listaProducts.iterator();
			while (iterator.hasNext()) {

				Product product = iterator.next();
				if (product.getName().equals(elemento)) {
					iterator.remove();
				}
			}

			listView.getItems().remove(elemento);
			listaElementos.remove(elemento);

		}

	}

	public void anadirElemento(String nom, String price, String type, String desc) {
		StringBuilder mensageError = new StringBuilder();

		if (nom == null || nom.isEmpty()) {
			mensageError.append("Warning: Name field empty.\n");
		}

		if (price == null || price.isEmpty()) {
			mensageError.append("Warning: Price field empty.\n");
		}

		if (type == null || type.isEmpty()) {
			mensageError.append("Warning: Type empty.\n");
		}

		if (desc == null || desc.isEmpty()) {
			mensageError.append("Warning: Description empty.\n");
		}

		if (mensageError.length() == 0) {
			Product producto = new Product(nom, price, type, desc);
			listaProducts.add(producto);

			listaElementos.add(nom);
			listView.getItems().add(producto.getName());
			//listView.getItems().add(producto.getName() + ", " + producto.getType() + ", " + producto.getPrice() + "€");
			textfield_name.clear();
			textfield_price.clear();
			textArea_desc.clear();
			choiceBox.setValue(null);
		} else {
			warnings_textArea.setText(mensageError.toString());
		}
	}

	public void updateDB() {

		//TODO Database calls

		for (String string : listaElementos) {
			System.out.println(string);
		}

		System.out.println("-------");

		for (Product product : listaProducts) {
			System.out.println(product);
		}

	}

	public void showPaneFromRight(Table table, Pane pane, double durationInSeconds) {
		table_id.setText("Table " + table.getId());
		
		pane.setTranslateX(pane.getParent().getLayoutBounds().getWidth());
		pane.setVisible(true);
		TranslateTransition transition = new TranslateTransition();
		transition.setNode(pane);
		transition.setFromX(pane.getTranslateX());
		transition.setToX(0);
		transition.setDuration(Duration.seconds(durationInSeconds));

		transition.play();

	}

	public void hidePaneToRight(Pane pane, double durationInSeconds) {
	    double startX = pane.getTranslateX();
	    TranslateTransition transition = new TranslateTransition();
	    transition.setNode(pane);
	    transition.setFromX(startX);
	    transition.setToX(pane.getParent().getLayoutBounds().getWidth());
	    transition.setDuration(Duration.seconds(durationInSeconds));
	    transition.setOnFinished(event -> pane.setVisible(false));
	    transition.play();
	}

	public void cargarItemsMesa(Table table) {
		//TODO base de datos
		
		//ticket_list.getItems().
		
	}

}



