package edu.unl.cse.csce361.car_rental.frontend;

import java.io.IOException;
import java.util.HashMap;

import com.sun.glass.events.WindowEvent;
import javafx.scene.control.MenuItem;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.SplitMenuButton;
import javafx.stage.Stage;


public class loginController {
	
	@FXML private SplitMenuButton carModel;
	
	public void AddElementToMenu() throws IOException {
		MenuItem choice1 = new MenuItem("Choice 1");
		MenuItem choice2 = new MenuItem("Choice 2");
		MenuItem choice3 = new MenuItem("Choice 3");
//		FXMLLoader summaryloader = new FXMLLoader(
//			    getClass().getResource(
//			        "home.fxml"
//			    )
//		);
//		carModel = (SplitMenuButton) summaryloader.load();
//		carModel.getItems().addAll(choice1, choice2, choice3);
	}
	
	
	public void switchPageButtonHomeModel(ActionEvent event)throws IOException{
		
//		AddElementToMenu();
		
		FXMLLoader loaderSelectModel = new FXMLLoader();
		loaderSelectModel.setLocation(getClass().getResource("home.fxml"));
		Parent parent = loaderSelectModel.load();
		
		Scene scene = new Scene(parent);
		Stage home = (Stage)((Node)event.getSource()).getScene().getWindow();
		home.setTitle("Husker Klunker Car Rentals");
		home.setScene(scene);
		home.show();
	}
	
}
