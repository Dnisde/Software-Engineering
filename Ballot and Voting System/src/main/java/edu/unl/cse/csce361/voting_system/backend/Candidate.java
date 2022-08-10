package edu.unl.cse.csce361.voting_system.backend;

public interface Candidate {
    
	/** The maximum length of the candidate's name */
    int MAXIMUM_LINE_LENGTH = 48;

    /** Provide's the candidate's name.
     * @return the candidate's name */
    String getName();
    
}
