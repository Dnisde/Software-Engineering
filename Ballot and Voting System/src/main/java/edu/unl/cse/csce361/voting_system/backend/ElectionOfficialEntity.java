package edu.unl.cse.csce361.voting_system.backend;


import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;

/**
 * 
 * Hibernate implementation of {@link ElectionOfficial}.
 *
 */
@Entity
public class ElectionOfficialEntity extends PersonEntity implements ElectionOfficial {
	
	@OneToOne(fetch = FetchType.EAGER)
    private PersonEntity person;
	
	public ElectionOfficialEntity() {
        super();
    }
    
    public ElectionOfficialEntity(String ssn, String name) {
    	super(ssn, name);
    }

}
