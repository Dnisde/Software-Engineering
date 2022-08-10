package edu.unl.cse.csce361.voting_system.voting_logic;

import java.util.Set;

import edu.unl.cse.csce361.voting_system.backend.Backend;
import edu.unl.cse.csce361.voting_system.backend.BallotEntity;
import edu.unl.cse.csce361.voting_system.backend.ElectionOfficialEntity;
import edu.unl.cse.csce361.voting_system.backend.Person;
import edu.unl.cse.csce361.voting_system.backend.VoterBallot;
import edu.unl.cse.csce361.voting_system.backend.VoterEntity;

public class BackendLogic {
	
	//Sets the currently logged in voter
	static VoterEntity currentVoter;
	
	public static void setCurrentVoter(VoterEntity voter) {
		currentVoter = voter;
	}
	
	public static VoterEntity getCurrentVoter() {
		return currentVoter;
	}
	
	public static String getCurrentVoterString() {
		return currentVoter.getSsn();
	}
	
	/**
	 * Checks to see if person exists
	 */
	public static Person isPerson(String ssn) {
		Person found = Backend.getInstance().getPerson(ssn);
		return found;
	}
	
	/**
	 * Gets what type of person is connected to the ssn
	 * @param ssn
	 * @return "Voter" if person is a voter, "Official" if person is an official
	 */
	public static String getInstanceOf(String ssn) {
		String type = new String();
		Person person = Backend.getInstance().getPerson(ssn);
		if(person instanceof VoterEntity) {
			type = "Voter";
		}
		if(person instanceof ElectionOfficialEntity) {
			type = "Official";
		}
		return type;
	}
	
	/**
	 * Retrieves VoterEntity object with the given SSN
	 * @param ssn
	 * @return VoterEntity
	 */
	public static VoterEntity getVoter(String ssn) {
		VoterEntity voter;
		if(Backend.getInstance().getPerson(ssn) instanceof VoterEntity) {
			voter = (VoterEntity) Backend.getInstance().getPerson(ssn);
		} else {
			voter = null;
		}
		return voter;
	}
	
	/**
	 * Creates an individual ballot for the voter based on their ssn
	 * @param ssn
	 */
	public static void createVoterBallot(String ssn) {
		VoterEntity voter = getVoter(ssn);
		BallotEntity ballot = Backend.getInstance().getBallot();
		VoterBallot vb = Backend.getInstance().createVoterBallot(voter, ballot);
		if(vb == null) {
			//rollback or something
		}
	}
	
	/** 
	 * Checks to see if Voter has a VoterBallot already
	 * @param voter
	 * @return boolean : true if a VoterBallot exists for the voter; false if it does not
	 */
	public static boolean checkVoterBallot(VoterEntity voter) {
		Set<VoterBallot> vbs = Backend.getInstance().getVoterBallots();
		for(VoterBallot vb : vbs) {
			if(vb.getPersonId() == voter.getId()) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Retrieves the VoterBallot for the given voter, if one exists
	 * @param voter
	 * @return VoterBallot corresponding to the voter
	 */
	public static VoterBallot getCurrentVoterBallot() {
		Set<VoterBallot> vbs = Backend.getInstance().getVoterBallots();
		for(VoterBallot vb : vbs) {
			if(vb.getPersonId() == currentVoter.getId()) {
				return vb;
			}
		}
		return null;
	}
	
	public static void updateIndividualVotes(String voteString) {
		Backend.getInstance();
		Backend.updateIndividualVotes(getCurrentVoterBallot(), voteString);
	}
	
	//Checks if the voter corresponding to the String ssn has voted
	public static int getHasVoted(String ssn) {
		VoterEntity voter = getVoter(ssn);
		System.out.println("Voter name : " + voter.getName());
		VoterBallot vb  = Backend.getInstance().getVoterBallot(voter);
		System.out.println(vb.getPersonId());
		return vb.getHasVoted();
	}

}
