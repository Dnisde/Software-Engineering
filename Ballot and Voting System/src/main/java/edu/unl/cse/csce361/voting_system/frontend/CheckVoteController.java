package edu.unl.cse.csce361.voting_system.frontend;

import java.io.IOException;

import edu.unl.cse.csce361.voting_system.voting_logic.BackendLogic;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/*
 * Allows an election official to search for voters and see if they have voted
 */
public class CheckVoteController {
	
	@FXML
	private TextField voterFirstName;
	@FXML
	private TextField voterLastName;
	@FXML
	private TextField voterSsn;
	@FXML
	private Label result;

	@FXML
	private void searchVoter(ActionEvent event) {
		firstName = voterFirstName.getText();
		lastName = voterLastName.getText();
		ssn = voterSsn.getText();
		System.out.println(firstName + " " + lastName + " " + ssn);
		
		if(firstName.isEmpty() || lastName.isEmpty() || ssn.isEmpty()) {
			result.setText("Please enter all fields");
		}
		else {
			System.out.println("Searching for voter...");
			int var = BackendLogic.getHasVoted(ssn);
			if(var == 1) {
				result.setText(firstName + " " + lastName + " has already voted.");
			}
			else if(var == 0) {
				result.setText(firstName + " " + lastName + " has not yet voted.");
			}
			else {
				result.setText("Error in retrieving voter data");
			}
		}
	}
	
	@FXML
	private void pressReturn(ActionEvent event) throws IOException {
		Controller con = new Controller();
		con.changeStages("electionOfficial", event);
	}
	
	String firstName, lastName, ssn;
}
