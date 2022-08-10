package edu.unl.cse.csce361.voting_system.frontend;

import java.io.IOException;

import edu.unl.cse.csce361.voting_system.voting_logic.BackendLogic;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class HomeController {
	
	@FXML
	private Button loginButton;
	@FXML
	private Button registerButton;
	@FXML
	private TextField firstName;
	@FXML
	private TextField lastName;
	@FXML
	private TextField ssn;
	
	public void pressLogin(ActionEvent event) throws IOException {
		System.out.println("I pressed login");
		userFirstName = firstName.getText();
		userLastName = lastName.getText();
		userSsn = ssn.getText();
		if(userFirstName == null || userLastName == null || userSsn == null) {
			//TODO: alter this so the text appears on the screen
			System.out.println("Please enter all fields");
		}
		else {
			if(BackendLogic.isPerson(userSsn) != null) { 
				Controller con = new Controller();
				
				//if person logging in is a voter go to ballot page
				if(BackendLogic.getInstanceOf(userSsn).equalsIgnoreCase("Voter")) {
				    BackendLogic.setCurrentVoter(BackendLogic.getVoter(userSsn));
					//Create an individual ballot instance for the voter
					if(!BackendLogic.checkVoterBallot(BackendLogic.getVoter(userSsn))) {
						BackendLogic.createVoterBallot(userSsn);
						BackendLogic.getCurrentVoter().setVoterBallot(BackendLogic.getCurrentVoterBallot());
					}
					//If voter has not yet voted, send them to the ballot page, else send them to "how they voted" page
					if(BackendLogic.getCurrentVoterBallot().getHasVoted() == 0) {
						con.changeStages("ballot", event);
					}
					else {
						System.out.println("User has already voted");
						con.changeStages("individualVoterResults", event);
					}

					con.changeStages("ballot", event);

				}
				//if person logging in is an election official go to manage ballot page
				if(BackendLogic.getInstanceOf(userSsn).equalsIgnoreCase("Official")) {
					con.changeStages("electionOfficial", event);
				}

			}
			
		}
	}
	
	public void pressRegister(ActionEvent event) throws IOException {
		Controller con = new Controller();
		con.changeStages("createAccount", event);
	}
	
	public void pressViewResults(ActionEvent event) throws IOException {
		Controller con = new Controller();
		con.changeStages("electionResults", event);
	}
	
	static String userFirstName, userLastName, userSsn;

}
