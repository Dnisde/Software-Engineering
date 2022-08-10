package edu.unl.cse.csce361.voting_system.frontend;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import edu.unl.cse.csce361.voting_system.backend.Backend;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

public class VoterBallotController {

	@FXML
	private ListView<String> races;
	@FXML
	private ListView<String> proposition;
	
	@FXML
	private void initialize() throws Exception {
		
		Backend instance = Backend.getInstance();
		
		/* A list of String that include all tha voter's ballot information */
    	List<String> unformattedVotes = instance.getVoteBallotInformation();
    	
    	int number = 1;
		for(Iterator<String> iterator = unformattedVotes.iterator(); iterator.hasNext();) {
			String singleVoteBallot = (String) iterator.next();
			String[] splitInformation = singleVoteBallot.split(";");
			
			List<String> propositionInformation = new ArrayList<String>();
			List<String> raceInformation = new ArrayList<String>();
			
			/*
			 * For Each Voter's proposition Information: 
			 */
			for(int i = 0; i < splitInformation.length; i++) {
				String propositionJudge1 = "Yes";
				String propositionJudge2 = "No";
				
				if (splitInformation[i].contains(propositionJudge1) || splitInformation[i].contains(propositionJudge2)){
					String[] tokens = splitInformation[i].split(",");
					String proposition = tokens[0] + " : " + tokens[1];
					propositionInformation.add(proposition);
					splitInformation[i] = null;
				}
			}
			proposition.getItems().add("User " + number + ":" + propositionInformation.toString());
			
			/*
			 * For Each Voter's Race Information: 
			 */
			for(int i = 0; i < splitInformation.length; i++) {
				if(splitInformation[i] != null) {
					String[] tokens2 = splitInformation[i].split(",");
					String race = tokens2[0] + " : " + tokens2[1];
					raceInformation.add(race);
				}
			}
			races.getItems().add("User " + number + ":" + raceInformation.toString());
			number = number + 1;
		}
	}
	
	public void pressBackHome(ActionEvent event) throws IOException {
		Controller con = new Controller();
		con.changeStages("home", event);
	}
	
}
