package edu.unl.cse.csce361.voting_system.frontend;

import java.io.IOException;

import edu.unl.cse.csce361.voting_system.voting_logic.BackendLogic;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

public class IndividualVoterResultsController {
	
	@FXML
	private ListView<String> races;
	@FXML
	private ListView<String> choices;

	@FXML
	private void initialize() {
		String unformattedVotes = BackendLogic.getCurrentVoterBallot().getVotes();
		String[] splitRaces = unformattedVotes.split(";");
		//For each race/proposition
		for(int i = 0; i < splitRaces.length; i++) {
			String[] tokens = splitRaces[i].split(",");
			races.getItems().add(tokens[0]);
			choices.getItems().add(tokens[1]);
		}
	}
	
	public void pressBackHome(ActionEvent event) throws IOException {
		Controller con = new Controller();
		con.changeStages("home", event);
	}

}
