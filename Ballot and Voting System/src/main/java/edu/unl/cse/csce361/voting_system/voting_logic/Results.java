package edu.unl.cse.csce361.voting_system.voting_logic;

import edu.unl.cse.csce361.voting_system.backend.CandidateEntity;
import edu.unl.cse.csce361.voting_system.backend.PropositionEntity;

public class Results {
	
	public int getCandidateVotes(String candidateName) {
		Elections election = new Elections();
		CandidateEntity candidate = election.getCandidateByName(candidateName);
		return candidate.getVotes();
	}
	
	public Integer getPropositionVotes(String propositionTitle) {

		Elections election = new Elections();
		PropositionEntity proposition = election.getPropositionByTitle(propositionTitle);
		return proposition.getVotes();
	}

}
