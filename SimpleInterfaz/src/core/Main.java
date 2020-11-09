package core;

import java.io.IOException;
import java.net.URL;

import controllers.AppController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;

public class Main extends Application{

	@Override
	public void start(Stage primaryStage) throws IOException {
		FXMLLoader cargador = new FXMLLoader();
		System.out.println(getClass().toString());
		URL xmlUrl = getClass().getResource("/fxmls/app.fxml");
		cargador.setLocation(xmlUrl);
		cargador.setController(new AppController()); // Writing controller directly 
		BorderPane root = cargador.load();
		Scene scene = new Scene(root,400,400);
		scene.getStylesheets().add(getClass().getResource("/styles/app.css").toExternalForm());
		primaryStage.setTitle("Mi app contactos");
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}

}
