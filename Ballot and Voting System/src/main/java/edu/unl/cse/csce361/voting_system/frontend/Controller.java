package edu.unl.cse.csce361.voting_system.frontend;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Controller {

	public void changeStages(String nextStage, ActionEvent event) throws IOException {
		Parent nextScene = FXMLLoader.load(getClass().getResource(nextStage+".fxml"));
		if(nextStage.equals("home")) {
	        Scene homepage = new Scene(nextScene, 650, 500);
	        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
	        stage.setScene(homepage);
			stage.show();
		} else {
	        Scene homepage = new Scene(nextScene);
	        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
	        stage.setScene(homepage);
	        stage.show();
		}
    }
}
