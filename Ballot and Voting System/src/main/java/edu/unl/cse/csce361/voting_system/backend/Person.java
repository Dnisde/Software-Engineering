package edu.unl.cse.csce361.voting_system.backend;

public interface Person {

	/** The maximum length of each of the customer's name and two street address lines */
    int MAXIMUM_LINE_LENGTH = 48;
    int MAXIMUM_DATE_LENGTH = 8;
    int MAXIMUM_SSN_LENGTH = 9;

    
    /** Provide's the person's name.
     * @return the person's name */
    String getName();
	
    /** Provides the person's social security number.
     * @return the person's social security number */
	String getSsn();
	
}
