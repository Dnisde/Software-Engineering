package edu.unl.cse.csce361.voting_system.backend;

public interface Proposition{
    
	/** The maximum length of the proposition title */
	int MAXIMUM_LINE_LENGTH = 48;
	/** The maximum length of the proposition description */
	int MAXIMUM_DESC_LENGTH = 100;
    
	/** Provide's the proposition's title.
     * @return the proposition's title */
	public String getTitle();
	
	/** Provide's the proposition's description.
     * @return the proposition's description */
	public String getDescription();

}
