package edu.unl.cse.csce361.voting_system.backend;

import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.annotations.NaturalId;

@Entity
public class CandidateEntity implements Candidate {
	
    @SuppressWarnings("unused")
    @Id
    @GeneratedValue
    private Long candidateId;
    
    @ManyToOne(fetch = FetchType.EAGER)
    private RaceEntity race;

    @NaturalId
    @Column(length = Person.MAXIMUM_LINE_LENGTH)
    private String name;
    @Column
    private Integer votes;
    
    public CandidateEntity() {
    	super();
    }
    
    public CandidateEntity(String name, RaceEntity race) {
    	super();
    	setName(name);
    	setRace(race);
    	this.votes = 0;
    }
    
    public CandidateEntity(String name, String race) {
    	super();
    	setName(name);
    	setRace(race);
    	this.votes = 0;
    }
    
    @SuppressWarnings("unchecked")
	static Set<Candidate> getCandidatesByRace(String race) {
		RaceEntity raceEntity = RaceEntity.getRaceByPosition(race);
		Session session = HibernateUtil.getSession();
		List<CandidateEntity> candidates = session
				.createQuery("from CandidateEntity where race_id=" + raceEntity.getId())
				.list();
		return Set.copyOf(candidates);
	}
    
    static CandidateEntity getCandidateByName(String name) {
		Session session = HibernateUtil.getSession();
		CandidateEntity candidate = null;
        try {
        	candidate = session.bySimpleNaturalId(CandidateEntity.class).load(name);
        } catch (HibernateException exception) {
            System.err.println("Could not load Candidate " + name + ". " + exception.getMessage());
        }
		return candidate;
	}
    
    @Override
	public String getName() {
		return this.name;
	}
    
    public int getVotes() {
		return this.votes;
	}
    
    public void addToRace(RaceEntity race) {
    	race.addCandidate(this);
    }
    
	public void setRace(String race) {
    	RaceEntity raceEntity = null;
        try {
            raceEntity = HibernateUtil.getSession().bySimpleNaturalId(RaceEntity.class).load(race);
        } catch (Exception e) {
            System.err.println("Error while loading model: either the required Java class is not a mapped entity\n" +
                    "    (unlikely), or the entity does not have a simple natural ID (also unlikely).");
            System.err.println("  " + e.getMessage());
            System.err.println("Please inform the the developer that the error occurred in\n" +
                    "    CandidateEntity.setRace(String).");
            raceEntity = null;
            System.err.println("Resuming, leaving " + this.toString() + " without an assigned model.");
        } finally {
            if (raceEntity != null) {
                setRace(raceEntity);
            } else {
                this.race = null;
            }
        }
        
    }
	
	void setRace(RaceEntity race) {
    	this.race = race;
    }
	
	void setName(String name) {
    	this.name = name;
    }
	
	public void vote() {
		this.votes++;
	}

}