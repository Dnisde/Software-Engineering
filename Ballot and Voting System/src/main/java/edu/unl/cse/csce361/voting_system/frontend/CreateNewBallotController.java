package edu.unl.cse.csce361.voting_system.frontend;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import edu.unl.cse.csce361.voting_system.backend.*;
import edu.unl.cse.csce361.voting_system.voting_logic.CreateNewBallot;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class CreateNewBallotController {

	@FXML
	private TextField AddRaceText;
	@FXML
	private TextArea AddCandidatesText;
	@FXML
	private TextField AddPropositionTitleText;
	@FXML
	private TextField AddPropositionDescriptionText;
	@FXML
	private MenuButton BallotYear;
	@FXML
	private MenuButton BallotMonth;
	@FXML
	private Label middle;
	
	private HashMap<Integer, String> filter = new HashMap<Integer, String>();
	private HashMap<Integer, ArrayList<String>> raceCandidateMap = new HashMap<Integer, ArrayList<String>>();
	private HashMap<Integer, ArrayList<String>> propositionMap = new HashMap<Integer, ArrayList<String>>();
	private final Integer monthlabel = 1;
	private final Integer yearlabel = 2;
	
	private String raceText, allCandidates, propositionTitleText, propositionDescriptionText;
	private String[] candidates; 
	private Integer RACE_CANDIDATE_COUNT = 1;
	private Integer PROPOSITION_COUNT = 1;
	
	public void addRaceWithCandidates(ActionEvent event) throws IOException {
		
		/*  Initialize an Array to store relationship between each race correspond with several candidates */
		ArrayList<String> raceAndCandidate = new ArrayList<String>();
		
		raceText = AddRaceText.getText();
		allCandidates = AddCandidatesText.getText();
		if(raceText.isBlank() || allCandidates.isBlank()) {
			System.out.println("You can not leave either Race OR Candidates Blank ! ");
			middle.setText("You can not leave either \n RACE OR CANDIDATES Blank ! ");
		}else if(!raceText.matches("^[A-Za-z]+$") || !allCandidates.matches("^[A-Za-z,]+$")){
			System.out.println("Hey, illegal syntax detected! ");
			middle.setText("Please entering: \n alphanumeric Character ONLY !");
		} else {
			/* Adding race position that User wants to create */
			raceAndCandidate.add(raceText);
			
			/* Adding Candidates that User wants to create for the race */
			candidates = allCandidates.split(",");
			for (int i=0; i<candidates.length; i++) {
				raceAndCandidate.add(candidates[i]);
			}
			/* Put the value of attributes into map */
			raceCandidateMap.put(RACE_CANDIDATE_COUNT, raceAndCandidate);
			RACE_CANDIDATE_COUNT = RACE_CANDIDATE_COUNT + 1;
			AddRaceText.clear();
			AddCandidatesText.clear();
			System.out.println(raceCandidateMap);
			middle.setText("Add Race and Candidate Successful.");
		}
		
		
	}

	public void addPropositions(ActionEvent event) throws IOException {
		
		ArrayList<String> proposition = new ArrayList<String>();
		propositionTitleText = AddPropositionTitleText.getText();
		propositionDescriptionText = AddPropositionDescriptionText.getText();
		if(propositionTitleText.isBlank() || propositionDescriptionText.isBlank()) {
			System.out.println("You can not leave either Title OR Proposition Blank ! ");
			middle.setText("You can not leave either \nTitle OR Proposition Blank ! ");
		} else {
			proposition.add(propositionTitleText);
			proposition.add(propositionDescriptionText);
			propositionMap.put(PROPOSITION_COUNT, proposition);
			PROPOSITION_COUNT = PROPOSITION_COUNT + 1;
			AddPropositionTitleText.clear();
			AddPropositionDescriptionText.clear();
			System.out.println(propositionMap);
			middle.setText("Add Prosposition Successful. ");
		}
		
	}


    public void createBallotButton(ActionEvent event) throws IOException {
		Controller con = new Controller();
		con.changeStages("createBallot", event);
	}

    public void backButton(ActionEvent event) throws IOException {
		Controller con = new Controller();
		con.changeStages("electionOfficial", event);
	}

	

	public void submitButton(ActionEvent event) throws IOException {
		
		/* Delete all the information about the current ballot */
		
		CreateNewBallot instance = new CreateNewBallot();
		instance.deleteBallot();
		
		/* Creating a new ballot and insert all information that needed */
		BallotEntity ballot = instance.createNewBallot();
		
		/* 
		 * Detect how many races exist: 
		 */
		for(Iterator<ArrayList<String>> iterator = raceCandidateMap.values().iterator(); iterator.hasNext();) {
			ArrayList<String> currentRaceCandidate = iterator.next();
			if(currentRaceCandidate.isEmpty() == false) {
				String race = currentRaceCandidate.get(0); // Get Race of current Race
				currentRaceCandidate.remove(0); // Remove the race correspond
				instance.createNewRace(race, ballot); // Insert Race into database
				/* Loop the current Candidates for this race */
				for (int j=0; j<currentRaceCandidate.size(); j++) {
					instance.createNewCandidate(currentRaceCandidate.get(j), race);
				}
			}
		
		}
		
		for (Iterator<ArrayList<String>> iterator = propositionMap.values().iterator(); iterator.hasNext();) {
			ArrayList<String> currentProposition = iterator.next();
			if(currentProposition.isEmpty() == false) {
				instance.createNewProposition(currentProposition.get(0), currentProposition.get(1), ballot);
				
			}
		}
		Controller con = new Controller();
		con.changeStages("home", event);
		
	}

	
	
	public void SelectMonthOfBallot() {
		
		System.out.println("Set Ballot Month button clicked");

		for(int i = 0; i< BallotMonth.getItems().size(); i++) {
			
			String month= BallotMonth.getItems().get(i).getText();

			BallotMonth.getItems().get(i).setOnAction(new EventHandler<ActionEvent>(){
				
				@Override
				public void handle(ActionEvent event) {
					BallotMonth.setText(month);
					filter.put(monthlabel, month);
					middle.setText("Set Ballot Month with: " + month);
				}
			});
			
		}
				
	}
	
	public void SelectYearOfBallot() {
		
		System.out.println("Set Ballot Year button clicked");

		for(int i = 0; i< BallotYear.getItems().size(); i++) {
			
			String year = BallotYear.getItems().get(i).getText();

			BallotYear.getItems().get(i).setOnAction(new EventHandler<ActionEvent>(){
				
				@Override
				public void handle(ActionEvent event) {
					BallotYear.setText(year);
					filter.put(yearlabel, year);
					middle.setText("Set Ballot Year with: " + year);
				}

			});
			
		}
				
	}
	
	@FXML
	private void pressCheckVoter(ActionEvent event) throws IOException {
		Controller con = new Controller();
		con.changeStages("checkVote", event);
	}
	
	@FXML
	private void pressCheckVoterBallot(ActionEvent event) throws IOException {
		Controller con = new Controller();
		con.changeStages("voterBallotCollection", event);
	}
}



