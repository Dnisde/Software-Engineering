package edu.unl.cse.csce361.voting_system.voting_logic;

import edu.unl.cse.csce361.voting_system.backend.Backend;
import edu.unl.cse.csce361.voting_system.backend.Person;

public class CreateAccount {
	
	public void createElectionOfficial(String ssn, String name) {
		Person electionOfficial = Backend.getInstance().createElectionOfficial(ssn, name);
		if(electionOfficial != null) {
			System.out.println("Election Official created successfully");
		}
	}
	
	public void createVoter(String ssn, String name, String address1, String address2, String city,
							String state, String zip, String birthDate) {
		Person voter = Backend.getInstance().createVoter(ssn, name, address1, address2, city, state, zip, birthDate);
		if(voter != null) {
			System.out.println("Voter created successfully");
		}
	}

}
