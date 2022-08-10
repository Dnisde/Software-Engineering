package edu.unl.cse.csce361.voting_system.backend;


import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import org.hibernate.Session;

@Entity
public class VoterBallot {
	
	@Id
	@GeneratedValue
	private Long vbId;

	@OneToOne
	@JoinColumn(name="personId", nullable = true, updatable = true)
	private VoterEntity voter;
	@ManyToOne(fetch = FetchType.EAGER)
    private BallotEntity ballot;
	
	@Column(length=255)
	private String votes;
	@Column( nullable = false )
	private int hasVoted;
	
	public VoterBallot() {
		super();
	}
	
	public VoterBallot(VoterEntity voter, BallotEntity ballot) {
		this.voter = voter;
		this.ballot = ballot;
		this.hasVoted = 0;
	}
	
	@SuppressWarnings("unchecked")
	public static Set<VoterBallot> getVoterBallots() {
		Session session = HibernateUtil.getSession();
		List<VoterBallot> vbs = session
				.createQuery("from VoterBallot").list();
		return Set.copyOf(vbs);
	}
	
	public static VoterBallot getVoterBallot(VoterEntity voter) {
		VoterBallot val = null;
        Set<VoterBallot> vbs = getVoterBallots();
        for(VoterBallot vb : vbs) {
        	if(vb.getPersonId() == voter.getId()) {
        		val = vb;
        	}
        }
		return val;
	}
	
	public Long getPersonId() {
		return voter.getId();
	}
	
	public void setVotes(String voteString) {
		this.votes = voteString;
	}
	
	public String getVotes() {
		return this.votes;
	}
	
	public void setHasVoted() {
		this.hasVoted = 1;
	}
	
	public int getHasVoted() {
		return this.hasVoted;
	}
	
}
