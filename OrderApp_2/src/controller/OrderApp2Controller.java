package controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;

public class OrderApp2Controller implements Initializable {



    @FXML
    private ScrollPane scrollPane1;

    @FXML
    private AnchorPane scrollPaneAnchorPanel;

    @FXML
    private FlowPane flowPaneContainer;  

    public static ArrayList<String> listaElementos = new ArrayList<String>();

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {

    	
        flowPaneContainer.setHgap(10);
        flowPaneContainer.setVgap(10);
        flowPaneContainer.setPrefWrapLength(scrollPaneAnchorPanel.getWidth());

        //button_add.setOnMouseClicked((event) -> anadirElemento(textfield_1.getText()));
    }

    public void anadirElemento(String elemento) {
        if (elemento != null && !elemento.trim().isEmpty()) {
            listaElementos.add(elemento);

            Button newButton = new Button(elemento);

            newButton.setPrefSize(80, 80);

            newButton.setOnAction(e -> {
                System.out.println("Button clicked: " + elemento);
            });

            flowPaneContainer.getChildren().add(newButton);

            
        }
    }
}
