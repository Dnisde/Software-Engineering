package edu.unl.cse.csce361.voting_system.frontend;

import java.io.IOException;

import edu.unl.cse.csce361.voting_system.voting_logic.Elections;
import edu.unl.cse.csce361.voting_system.voting_logic.Results;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

public class ElectionResultsController {
	
	@FXML
	private ListView<String> race;
	@FXML
	private ListView<String> winner;
	
	@FXML
	private void initialize() {
		Elections election = new Elections();
		Results results = new Results();
		String[] races = election.getRaces();
		for(int i = 0; i < races.length; i++) {
			System.out.println(races[i]);
			race.getItems().add(races[i]);
			String[] candidates = election.getCandidateNames(races[i]);
			int max = 0;
			String winningCandidate = "";
			for(int j = 0; j < candidates.length; j++) {
				System.out.println(candidates[j] + " " + results.getCandidateVotes(candidates[j]));
				if(results.getCandidateVotes(candidates[j]) > max) {
					max = results.getCandidateVotes(candidates[j]);
					winningCandidate = candidates[j];
				}
			}
			winner.getItems().add(winningCandidate);
		}
		
		String[] propositions = election.getPropositionTitles();
		for(int k = 0; k < propositions.length; k++) {
			System.out.println(propositions[k]);
			race.getItems().add(propositions[k]);
			System.out.println(results.getPropositionVotes(propositions[k]));
			if(results.getPropositionVotes(propositions[k]) > 0) {
				winner.getItems().add("Passed");
			}
			if(results.getPropositionVotes(propositions[k]) < 0) {
				winner.getItems().add("Failed");
			}
		}
	}
	
	public void pressBackHome(ActionEvent event) throws IOException {
		Controller con = new Controller();
		con.changeStages("home", event);
	}
}
