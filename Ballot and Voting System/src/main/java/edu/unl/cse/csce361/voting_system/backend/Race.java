package edu.unl.cse.csce361.voting_system.backend;

public interface Race {
    
	/** The maximum length of the race title */
    int MAXIMUM_LINE_LENGTH = 48;
    
    /** Provide's the race's position.
     * @return the race's position */
    public String getPosition();

}
