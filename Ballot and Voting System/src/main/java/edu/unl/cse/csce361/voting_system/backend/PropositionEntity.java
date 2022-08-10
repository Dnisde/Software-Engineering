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
public class PropositionEntity implements Proposition {
	
    @SuppressWarnings("unused")
    @Id
    @GeneratedValue
    private Long id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    private BallotEntity ballot;              // depends on concretion for database purposes
    
    @NaturalId
    @Column(length = MAXIMUM_LINE_LENGTH)
    private String title;
    @Column(length = MAXIMUM_DESC_LENGTH)
    private String description;
    @Column
    private Integer yesVotes;
    @Column
    private Integer noVotes;
    
    public PropositionEntity() {
    	super();
    }
    
    public PropositionEntity(String title, String description, BallotEntity ballot) {
    	super();
    	this.title = title;
    	this.description = description;
    	setBallot(ballot);
    	this.yesVotes = 0;
    	this.noVotes = 0;
    }
    
    public PropositionEntity(String title, String description) {
    	super();
    	this.title = title;
    	this.description = description;
    	this.yesVotes = 0;
    	this.noVotes = 0;
    }
    
    @Override
    public String getTitle() {
    	return this.title;
    }
    
    @Override
    public String getDescription() {
    	return this.description;
    }
    
    public void voteYes() {
    	this.yesVotes++;
    }
    
    public void voteNo() {
    	this.noVotes++;
    }
    
    public int getVotes() {
    	return this.yesVotes-this.noVotes;
    }
	
	public static PropositionEntity getPropositionByTitle(String title) {
		Session session = HibernateUtil.getSession();
        session.beginTransaction();
        PropositionEntity proposition = null;
        try {
        	proposition = session.bySimpleNaturalId(PropositionEntity.class).load(title);
            session.getTransaction().commit();
        } catch (HibernateException exception) {
            System.err.println("Could not load Proposition " + title + ". " + exception.getMessage());
        }
        return proposition;
	}
	
	@SuppressWarnings("unchecked")
	static Set<Proposition> getPropositions() {
		Session session = HibernateUtil.getSession();
		List<Proposition> propositions = session
				.createQuery("from PropositionEntity").list();
		return Set.copyOf(propositions);
	}
	
	public void addToBallot(BallotEntity ballot) {
    	ballot.addProposition(this);
    }
	
	public void setBallot(BallotEntity ballot) {
		this.ballot = ballot;
	}

}
