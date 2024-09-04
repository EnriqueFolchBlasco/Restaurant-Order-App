package controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import models.Product;

public class OrderApp1Controller implements Initializable {

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
    private ListView<String> tablesList;

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


    public static ArrayList<String> listaElementos = new ArrayList<String>();
    
    public static ArrayList<String> listaTipos = new ArrayList<String>();
    
    public static ArrayList<Product> listaProducts = new ArrayList<Product>();



    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
    	
//    	listaElementos.add("Producto1");
//    	listView.getItems().addAll(listaElementos);
    	choiceBoxSetUp();
    	
    	
    	//Main buttons
        button_add.setOnMouseClicked((event) -> anadirElemento(textfield_name.getText(), textfield_price.getText(), choiceBox.getSelectionModel().getSelectedItem(), textArea_desc.getText()));
        button_update.setOnMouseClicked((event) -> updateDB());
        
        //Type buttons
        button_addType.setOnMouseClicked((event) -> anadirTipo(textfield_type.getText()));
        button_removeType.setOnMouseClicked((event) -> quitarTipo(textfield_type.getText()));
        
        //Hold element then press button to delete it
        listView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> arg0, String arg1, String arg2) {

		        button_remove.setOnMouseClicked((event) -> quitarElemento(arg2));

			}	
		});
        
    }
    
    public void anadirTipo(String tipo) {
        if (!tipo.isEmpty()) {
        	listaTipos.add(tipo);
        	textfield_type.clear();
            choiceBox.getItems().clear();
        	choiceBox.getItems().addAll(listaTipos);
        	
        	for (String string : listaTipos) {
				System.out.println(string);
			}

        }
    }
    
    public void quitarTipo(String tipo) {
        if (listaTipos.contains(tipo)) {
            listaTipos.remove(tipo);
            choiceBox.getItems().clear();
        	choiceBox.getItems().addAll(listaTipos);
        }
    }
    
    public void choiceBoxSetUp() {
    	
    	listaTipos.add("Entrante");
    	listaTipos.add("Comida");
    	listaTipos.add("Bebida");

    	
    	choiceBox.getItems().addAll(listaTipos);
    	
    }
    
    public void quitarElemento(String elemento) {
        if (listView.getItems().contains(elemento)) {
        	
            listView.getItems().remove(elemento);
            listaElementos.remove(elemento);
            
            
            //Fix per a ConcurrentModificationException
            Iterator<Product> iterator = listaProducts.iterator();
            while (iterator.hasNext()) {
                Product product = iterator.next();
                if (product.getName().equals(elemento)) {
                    iterator.remove();
                }
            }
            
        }
    }

    public void anadirElemento(String nom, String price, String type, String desc) {
    	StringBuilder mensageError = new StringBuilder();

    	if (nom.isEmpty()) {
    	    mensageError.append("Warning: Name field empty.\n");
    	}
    	
    	if (price.isEmpty()) {
    	    mensageError.append("Warning: Price field empty.\n");
    	}
    	
    	if (type == null) {
    	    mensageError.append("Warning: Type empty.\n");
    	}
    	
    	if (desc.isEmpty()) {
    	    mensageError.append("Warning: Description empty.\n");
    	}

    	if (mensageError.length() == 0) {
    	    Product producto = new Product(nom, price, type, desc);
    	    listaProducts.add(producto);

    	    listaElementos.add(nom);
    	    listView.getItems().add(nom);
    	    textfield_name.clear();
    	} else {
    	    warnings_textArea.setText(mensageError.toString());
    	}
    }
    
    public void updateDB() {
    	
		//TODO Database calls

    	
    	for (Product product : listaProducts) {
			System.out.println(product);
		}
    	
    }
    
    
    
}
