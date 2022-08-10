package edu.unl.cse.csce361.voting_system.voting_logic;

import edu.unl.cse.csce361.voting_system.backend.Backend;
import edu.unl.cse.csce361.voting_system.backend.BallotEntity;
import edu.unl.cse.csce361.voting_system.backend.Candidate;
import edu.unl.cse.csce361.voting_system.backend.Proposition;
import edu.unl.cse.csce361.voting_system.backend.Race;

public class CreateNewBallot{
	
	public BallotEntity createNewBallot() {
		BallotEntity ballot = null;
		ballot = Backend.getInstance().createBallot();
		return ballot;
	}
	
	public void createNewProposition(String title, String description, BallotEntity ballot) {
		Proposition proposition = Backend.getInstance().createProposition(title, description, ballot);
		if(proposition != null) {
			System.out.println("The new proposition has been created successfully: " + proposition.getTitle() +
					": " + proposition.getDescription());
		}
		
	}
	
	public void createNewRace(String position, BallotEntity ballot) {
		Race race = Backend.getInstance().createRace(position, ballot);
		if(race != null) {
			System.out.println("The new race has been created successfully: " + race.getPosition());
		}
		
	}
	
	public void createNewCandidate(String name, String racePosition) {
		Candidate candidate = Backend.getInstance().createCandidate(name, racePosition);
		if(candidate != null) {
			System.out.println("The candidate was create successfully, candidate name: " + candidate.getName());
		}
		
	}

	public void deleteBallot() {
		Backend.getInstance().deleteBallot();
		System.out.println("Deleted current ballot");
	}
	
}
