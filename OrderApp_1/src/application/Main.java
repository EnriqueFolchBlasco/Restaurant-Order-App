package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.fxml.FXMLLoader;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		
		try {
			String fxml = "vista/OrderApp1.fxml";
			Parent root = FXMLLoader.load(getClass().getClassLoader().getResource(fxml));
			Scene scene = new Scene(root);
			primaryStage.setTitle("OrderApp1");
			primaryStage.setResizable(false);
			primaryStage.setScene(scene);
			primaryStage.show();

            String css = getClass().getClassLoader().getResource("vista/application.css").toExternalForm();
            scene.getStylesheets().add(css);

		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}