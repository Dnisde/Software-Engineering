package edu.unl.cse.csce361.voting_system.backend;


import java.util.Collections;
import java.util.Set;
import java.util.HashSet;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.HibernateException;
import org.hibernate.Session;

@Entity
public class BallotEntity {
	
	@Id
    @GeneratedValue
    private Long ballotId;

	@OneToMany(mappedBy = "ballot", cascade = CascadeType.ALL)
    private Set<PropositionEntity> propositions = new HashSet<PropositionEntity>();
    @OneToMany(mappedBy = "ballot", cascade = CascadeType.ALL)
    private Set<RaceEntity> races = new HashSet<RaceEntity>();
    @OneToMany(mappedBy = "ballot", cascade = CascadeType.ALL)
	private Set<VoterBallot> vb;
    
    public BallotEntity() {
    	super();
    }
    
    public BallotEntity(Set<PropositionEntity> propositions, Set<RaceEntity> races) 
            throws IllegalArgumentException, NullPointerException {
    	super();
    	races = new HashSet<>();
    	propositions = new HashSet<>();
    }
    
    @SuppressWarnings("unchecked")
	public static BallotEntity getOnlyBallot() {
		Session session = HibernateUtil.getSession();
		BallotEntity ballot = null;
		try {
			List<BallotEntity> be = session
					.createQuery("from BallotEntity").list();
			ballot = be.get(0);
			System.out.println(ballot.getId());
		} catch (NullPointerException e) {
			System.out.println(e);
		}
		return ballot;
	}
    
    /** Retrieves BallotEntity
	 * Currently: only retrieves the only ballot in the database, based upon its generated ID.
	 * If the databasePopulator is run again, the ID must be updated
	 * TODO: Method should retrieve the currently active ballot, that is, the most recently created one
	 */
	static BallotEntity getBallot() {
		long id = getOnlyBallot().getId();
		Session session = HibernateUtil.getSession();
        BallotEntity ballot = null;
        session.beginTransaction();
        try {
        	ballot = session.byId(BallotEntity.class).getReference(id);
        	session.getTransaction().commit();
        }catch (HibernateException exception) {
        	session.getTransaction().rollback();
        	System.out.println("Unable to retrieve ballot: " + exception.getMessage());
        }
		return ballot;
	}
	
    public long getId() {
    	return this.ballotId;
    }
    
    public Set<PropositionEntity> getPropositions() {
        return Collections.unmodifiableSet(propositions);
    }
    
    public Set<RaceEntity> getRaces() {
    	return Collections.unmodifiableSet(races);
    }

    public void addProposition(Proposition proposition) {
        if (proposition instanceof PropositionEntity) {
            PropositionEntity propositionEntity = (PropositionEntity) proposition;
            this.propositions.add(propositionEntity);
            propositionEntity.setBallot(this);
        } else {
            throw new IllegalArgumentException("Expected PropositionEntity, got " + proposition.getClass().getSimpleName());
        }
    }
    
    public void addPropositions(Set<Proposition> propositions) {
    	if (propositions instanceof PropositionEntity) {
    		for(Proposition p : propositions) {
    			addProposition(p);
    		}
    	} else {
    		throw new IllegalArgumentException("Expected PropositionEntity, got " + propositions.getClass().getSimpleName());
    	}
    }
    
    public void addRace(Race race) {
    	if(race instanceof RaceEntity) {
    		RaceEntity raceEntity = (RaceEntity) race;
    		this.races.add(raceEntity);
    		raceEntity.setBallot(this);
    	} else {
    		throw new IllegalArgumentException("Expected RaceEntity, got " + race.getClass().getSimpleName());
    	}
    }
    
    public void addRaces(Set<Race> races) {
    	if(races instanceof RaceEntity) {
    		for(Race r : races) {
    			addRace(r);
    		}
    	}else {
    		throw new IllegalArgumentException("Expected PropositionEntity, got " + races.getClass().getSimpleName());
    	}
    }
    
}
