package edu.unl.cse.csce361.voting_system.backend;

public interface Voter extends Person {
	
    /** The maximum length of the city's name */
    int MAXIMUM_CITY_LENGTH = 32;
    /** The exact length of the state's abbreviation */
    int STATE_LENGTH = 2;
    /** The exact length of the zip code (without the "plus-four") */
    int ZIPCODE_LENGTH = 5;
    
    /** Provide's the voter's address.
     * @return the voter's address */
    String getAddress();
    
    /** Provide's the voter's DOB.
     * @return the voter's DOB */
    String getDateOfBirth();
    
    /** Provide's the voter's ballot.
     * @return the voter's ballot */
    VoterBallot getVoterBallot();

}
