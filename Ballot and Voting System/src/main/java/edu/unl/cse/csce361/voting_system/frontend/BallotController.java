package edu.unl.cse.csce361.voting_system.frontend;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import edu.unl.cse.csce361.voting_system.voting_logic.BackendLogic;
import edu.unl.cse.csce361.voting_system.voting_logic.Elections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Accordion;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TitledPane;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Pane;

public class BallotController {
	
	List<ToggleGroup> candidateGroup = new ArrayList<ToggleGroup>();
	List<ToggleGroup> propositionGroup = new ArrayList<ToggleGroup>();
	String votes = "";
	String currentVoterSsn = BackendLogic.getCurrentVoterString();
	

	
	@FXML
	private Accordion accordion;
	
	/**
	 * populates the ballot with active races and propositions
	 */
	@FXML
	private void initialize() {
		
		Pane pane;
		TitledPane tp;
		
		/* 
		 * Initializes the races
		 */
		Elections election = new Elections();
		String[] races = election.getRaces();
		//for each individual race
		for(int i = 0; i < races.length; i++) {
			String position = races[i];
			
			//Create CheckBoxes for all candidates in given race
			String[] candidates = election.getCandidateNames(races[i]);
			ToggleGroup group = new ToggleGroup();
			group.setUserData(position);
			RadioButton[] rb = new RadioButton[candidates.length];
			for(int j = 0; j < candidates.length; j++) {
				rb[j] = new RadioButton(candidates[j]);
				rb[j].setUserData(candidates[j]);
				rb[j].setToggleGroup(group);
			}
			candidateGroup.add(group);
			
			//Fill the pane with the options
			pane = new Pane(); 
			pane.getChildren().addAll(rb);
			for(int j = 0; j < candidates.length; j++) {
				rb[j].relocate(20, 10 + 30 * j);
			}
			//Add the race to the accordion
			tp = new TitledPane(position, pane);
			accordion.getPanes().add(tp);
		}
		
		
		/*
		 * Initializes the propositions
		 */
		String[] propositions = election.getPropositionTitles();
		String[] descriptions = election.getPropositionDescriptions();

		Label label;
		
		//Populates Pane with Propositions
		for(int i = 0; i < propositions.length; i++) {
			label = new Label(descriptions[i]);
			pane = new Pane();
			
			ToggleGroup group = new ToggleGroup();
			group.setUserData(propositions[i]);
			
			RadioButton yes = new RadioButton("Yes");
			yes.setUserData("Yes");
			yes.setToggleGroup(group);
			RadioButton no = new RadioButton("No");
			no.setUserData("No");
			no.setToggleGroup(group);
			propositionGroup.add(group);
			
			pane.getChildren().add(label);
			pane.getChildren().add(yes);
			pane.getChildren().add(no);
			yes.relocate(20, 30);
			no.relocate(20, 60);
			tp = new TitledPane(propositions[i], pane);
			accordion.getPanes().add(tp);
		}
        
	}
	
	/**
	 * Method will submit selections made by the user and update the votes
	 * @param event
	 * @throws IOException
	 */
	public void pressSubmit(ActionEvent event) throws IOException {
		BackendLogic.getCurrentVoterBallot().setHasVoted();
		
		//Sorts through the votes made for candidates
		for(int i = 0; i < candidateGroup.size(); i++) {
			String candidateName = " ";
			if(candidateGroup.get(i).getSelectedToggle() != null) {
				candidateName = candidateGroup.get(i).getSelectedToggle().getUserData().toString();
				Elections election = new Elections();
				election.getCandidateByName(candidateName).vote();
			}
			//Adds vote to string
			String candidateAndPosition = candidateGroup.get(i).getUserData().toString() + "," + candidateName + ";";
			votes += candidateAndPosition;
		}
		
		//Sorts through the votes made for propositions
		for(int j = 0; j < propositionGroup.size(); j++) {
			String propositionVote = " ";
			if(propositionGroup.get(j).getSelectedToggle() != null) {
				propositionVote = propositionGroup.get(j).getSelectedToggle().getUserData().toString();
				Elections election = new Elections();
				if(propositionVote.equalsIgnoreCase("Yes")) {
					election.getPropositionByTitle(propositionGroup.get(j).getUserData().toString()).voteYes();
				}
				if(propositionVote.equalsIgnoreCase("No")) {
					election.getPropositionByTitle(propositionGroup.get(j).getUserData().toString()).voteNo();
				}
			}
			//Adds vote to string
			String propositionAndVote = propositionGroup.get(j).getUserData().toString() + "," + propositionVote + ";";
			votes += propositionAndVote;
		}
		BackendLogic.updateIndividualVotes(votes);
		System.out.println(BackendLogic.getCurrentVoterBallot().getVotes());
		
		Controller con = new Controller();
		con.changeStages("individualVoterResults", event);
	}
}
