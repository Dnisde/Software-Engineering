package edu.unl.cse.csce361.voting_system.voting_logic;

import java.util.HashSet;
import java.util.Set;

import edu.unl.cse.csce361.voting_system.backend.Backend;
import edu.unl.cse.csce361.voting_system.backend.Candidate;
import edu.unl.cse.csce361.voting_system.backend.CandidateEntity;
import edu.unl.cse.csce361.voting_system.backend.Proposition;
import edu.unl.cse.csce361.voting_system.backend.PropositionEntity;
import edu.unl.cse.csce361.voting_system.backend.Race;

public class Elections {
	
	/**
	 * 
	 * @return The list of races on the election ballot
	 */
	public String[] getRaces() {
		Set<Race> races = Backend.getInstance().getRaces();
		Set<String> temp = new HashSet<>();
		String[] racePositions = new String[races.size()];
		for(Race r : races) {
			temp.add(r.getPosition());
		}
		racePositions = temp.toArray(racePositions);
		return racePositions;
	}
	
	/**
	 * 
	 * @param The string name of a specific race or position
	 * @return The list of candidates running for that position
	 */
	public String[] getCandidateNames(String race) {
		Set<Candidate> candidates = Backend.getInstance().getCandidates(race);
		Set<String> temp = new HashSet<>();
		String[] candidateNames = new String[candidates.size()];
		for(Candidate c : candidates) {
			temp.add(c.getName());
		}
		candidateNames = temp.toArray(candidateNames);
		return candidateNames;
	}
	
	public CandidateEntity getCandidateByName(String name) {
		return Backend.getInstance().getCandidateByName(name);
	}

	/**
	 * 
	 * @return a string array of the titles of all active propositions
	 */
	public String[] getPropositionTitles() {
		Set<Proposition> propositions = Backend.getInstance().getPropositions();
		Set<String> temp = new HashSet<>();
		String[] propositionTitles = new String[propositions.size()];
		for(Proposition p : propositions) {
			temp.add(p.getTitle());
		}
		propositionTitles = temp.toArray(propositionTitles);
		return propositionTitles;
	}
	
	/**
	 * 
	 * @return a string array of the descriptions of all active proposition
	 */
	public String[] getPropositionDescriptions() {
		Set<Proposition> propositions = Backend.getInstance().getPropositions();
		Set<String> temp = new HashSet<>();
		String[] propositionDescriptions = new String[propositions.size()];
		for(Proposition p : propositions) {
			temp.add(p.getDescription());
		}
		propositionDescriptions = temp.toArray(propositionDescriptions);
		return propositionDescriptions;
	}
	
	public PropositionEntity getPropositionByTitle(String title) {
		return Backend.getInstance().getPropositionByTitle(title);
	}

}
