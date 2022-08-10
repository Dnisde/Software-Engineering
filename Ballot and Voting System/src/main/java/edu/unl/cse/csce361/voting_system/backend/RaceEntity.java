package edu.unl.cse.csce361.voting_system.backend;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.annotations.NaturalId;

@Entity
public class RaceEntity implements Race {

    @SuppressWarnings("unused")
    @Id
    @GeneratedValue
    private Long id;
    
    @NaturalId
    @Column(length = MAXIMUM_LINE_LENGTH)
    private String position;

    @OneToMany(mappedBy = "race", cascade = CascadeType.ALL)
    private Set<CandidateEntity> candidates = new HashSet<>();
    @ManyToOne(fetch = FetchType.EAGER)
    private BallotEntity ballot;
    
    public RaceEntity() {
    	super();
    }
    
    public RaceEntity(String position) {
    	super();
    	this.position = position;
    	candidates = new HashSet<>();
    }
    
    public RaceEntity(String position, BallotEntity ballot) {
    	super();
    	this.position = position;
    	candidates = new HashSet<>();
    	setBallot(ballot);
    }
    
    @SuppressWarnings("unchecked")
	static Set<Race> getRaces() {
		Session session = HibernateUtil.getSession();
		List<RaceEntity> races = session
				.createQuery("from RaceEntity").list();
		return Set.copyOf(races);
	}
	
	static RaceEntity getRaceByPosition(String position) {
		Session session = HibernateUtil.getSession();
        RaceEntity raceEntity = null;
        try {
        	raceEntity = session.bySimpleNaturalId(RaceEntity.class).load(position);
        } catch (HibernateException exception) {
            System.err.println("Could not load Race " + position + ". " + exception.getMessage());
        }
		return raceEntity;
	}
    
    public long getId() {
    	return this.id;
    }
    
    public String getPosition() {
    	return position;
    }

    public void addCandidate(Candidate candidate) {
        if (candidate instanceof CandidateEntity) {
        	CandidateEntity candidateEntity = (CandidateEntity) candidate;
            this.candidates.add(candidateEntity);
            candidateEntity.setRace(this);
        } else {
            throw new IllegalArgumentException("Expected CandidateEntity, got " + candidate.getClass().getSimpleName());
        }
    }

    public void addCandidates(Set<Candidate> candidates) {
    	if(candidates instanceof CandidateEntity) {
    		for(Candidate c : candidates) {
    			addCandidate(c);
    		}
    	} else {
            throw new IllegalArgumentException("Expected CandidateEntity, got " + candidates.getClass().getSimpleName());
        }
    }

    public void addToBallot(BallotEntity ballot) {
    	ballot.addRace(this);
    }
    
	public void setBallot(BallotEntity ballot) {
		this.ballot = ballot;
	}

}
