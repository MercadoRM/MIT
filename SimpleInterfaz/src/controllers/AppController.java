package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class AppController{

	@FXML
	Button btn1;
	@FXML
	TextField tf1;
	
	@FXML
	public void presionado()
	{
		tf1.setText("Presionado!");
	}
}
