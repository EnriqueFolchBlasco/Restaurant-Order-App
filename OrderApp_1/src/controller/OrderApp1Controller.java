package controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;

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


    public static ArrayList<String> listaElementos = new ArrayList<String>();
    
    public static ArrayList<String> listaTipos = new ArrayList<String>();


    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
    	
    	listaElementos.add("Producto1");
    	listView.getItems().addAll(listaElementos);
    	choiceBoxSetUp();
    	
    	//Main buttons
        button_add.setOnMouseClicked((event) -> anadirElemento(textfield_name.getText()));
        button_remove.setOnMouseClicked((event) -> quitarElemento(textfield_name.getText()));
        button_update.setOnMouseClicked((event) -> updateDB());
        
        //Type buttons
        button_addType.setOnMouseClicked((event) -> anadirTipo(textfield_type.getText()));
        button_removeType.setOnMouseClicked((event) -> quitarTipo(textfield_type.getText()));
        
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
        }
    }

    public void anadirElemento(String elemento) {
        if (!elemento.isEmpty()) {
            listaElementos.add(elemento);
            listView.getItems().add(elemento);
            textfield_name.clear();
        }
    }
    
    public void updateDB() {
    	
    }
    
    
    
}
