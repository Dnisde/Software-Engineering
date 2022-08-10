package edu.unl.cse.csce361.voting_system.backend;

import java.time.DateTimeException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.persistence.PersistenceException;

import org.hibernate.Session;

public class Backend {
	
	private static Backend instance;

	   public static Backend getInstance() {
		   if (instance == null) {
			   instance = new Backend();
		   }
	       return instance;
	   }

	   private Backend() {
	       super();
	   }
	   
	static BallotEntity ballot;
	
	
	
    /* RETRIEVES EXISTING OBJECTS */

    /**
     * Retrieves the person that has the specified ssn
     *
     * @param ssn
     * @return The specified person if it is present in the database; <code>null</code> otherwise
     */
	public Person getPerson(String ssn) {
		return PersonEntity.getPersonBySsn(ssn);
	}
	public Race getRace(String position) {
		return RaceEntity.getRaceByPosition(position);
	}
	public Set<Candidate> getCandidates(String race) {
		return CandidateEntity.getCandidatesByRace(race);
	}
	public Set<Race> getRaces() {
		return RaceEntity.getRaces();
	}
	public Set<Proposition> getPropositions() {
		return PropositionEntity.getPropositions();
	}
	public BallotEntity getBallot() {
		return BallotEntity.getBallot();
	}
	public Set<VoterBallot> getVoterBallots() {
		return VoterBallot.getVoterBallots();
	}
	public CandidateEntity getCandidateByName(String name) {
		return CandidateEntity.getCandidateByName(name);
	}
	public PropositionEntity getPropositionByTitle(String title) {
		return PropositionEntity.getPropositionByTitle(title);
	}
	public VoterBallot getVoterBallot(VoterEntity voter) {
		return VoterBallot.getVoterBallot(voter);
	}
	
	
	
	/* CREATES  NEW OBJECTS */
	public Person createVoter(String ssn, String name, String address1,
								String address2, String city, String state, String zipCode,
								String dateOfBirth)
							    throws DateTimeException, IllegalArgumentException, NullPointerException{
		Person voter;
        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        try {
        	voter = new VoterEntity(ssn, name, address1, address2, city, state, zipCode, dateOfBirth); 
        	session.saveOrUpdate(voter);
            session.getTransaction().commit();
        } catch (PersistenceException | DateTimeException | IllegalArgumentException | NullPointerException exception) {
        	session.getTransaction().rollback();
            voter = getPerson(ssn);
            session.getTransaction().rollback();
            if (voter == null) {
                throw new IllegalArgumentException("Could not create new voter " + name + ": " +
                        exception.getMessage() + ". Could not retrieve existing voter " + name +
                        " from database.", exception);
            }
        }
        
        return voter;
	}
	
	
	
	public Person createElectionOfficial(String ssn, String name)
		    throws DateTimeException, IllegalArgumentException, NullPointerException{
		Person electionOfficial;
		Session session = HibernateUtil.getSession();
		session.beginTransaction();
		try {
			electionOfficial = new ElectionOfficialEntity(ssn, name); 
			session.saveOrUpdate(electionOfficial);
			session.getTransaction().commit();
		} catch (PersistenceException | DateTimeException | IllegalArgumentException | NullPointerException exception) {
			session.getTransaction().rollback();
			electionOfficial = getPerson(ssn);
			session.getTransaction().rollback();
			if (electionOfficial == null) {
				throw new IllegalArgumentException("Could not create new electionOfficial " + name + ": " +
						exception.getMessage() + ". Could not retrieve existing electionOfficial " + name +
						" from database.", exception);
			}
		}
		
		return electionOfficial;
	}
	
	
	
	/**
	 * Creates individual ballot entity for the given voter and currently active ballot
	 * @param voter
	 * @param ballot
	 * @return VoterBallot instance
	 */
	public VoterBallot createVoterBallot(VoterEntity voter, BallotEntity ballot) {
		VoterBallot vb;
		Session session = HibernateUtil.getSession();
		session.beginTransaction();
		try {
			vb = new VoterBallot(voter, ballot);
			voter.setVoterBallot(vb);
			session.saveOrUpdate(vb);
			session.saveOrUpdate(voter);
			session.getTransaction().commit();
		}catch (PersistenceException | DateTimeException | IllegalArgumentException | NullPointerException exception) {
			session.getTransaction().rollback();
			vb = null;
			throw new IllegalArgumentException("Could not create individual ballot for " + voter.getName() + ": " +
					exception.getMessage() + ".");
		}
		return vb;
	}

	
	
	/**
	 * Get a current voter's Id by using the user's SSN
	 * @param ssn
	
	 */
	public Long getVotersIdByssn(String ssn){
		List<Voter> voters= null;
		Long voterInformation = null;
		Session session = HibernateUtil.getSession();
		try {
			
			voters = session.createQuery("FROM VoterEntity", Voter.class).list();
			for(Iterator<Voter> iterator = voters.iterator(); iterator.hasNext();){
				VoterEntity voter = (VoterEntity) iterator.next();
				if(voter.getSsn().equals(ssn)) {
					voterInformation = voter.getId();
				}
			}
			
		} catch(PersistenceException exception){
			
			if(voters == null) {
				throw new NullPointerException("Can not extract available voters ID from database");
			}
		}
		return voterInformation;
		
	}
	
	
	
	/**
	 * Create a new proposition accompany with a tile and a description.
	 * @param: title: The current proposition tile for the ballot of this year
	 * @param: description:  The current proposition for the ballot of this year
	 * @param: ballot: The ballotEntity for the proposition
	 * 
	 */
	public Proposition createProposition(String title, String description, BallotEntity ballot) {
		Proposition proposition = null;
		Session session = HibernateUtil.getSession();
		session.beginTransaction();
		try {
			proposition = new PropositionEntity(title, description, ballot);
			session.save(proposition);
			session.getTransaction().commit();
		}catch (PersistenceException | DateTimeException | IllegalArgumentException | NullPointerException exception) {
			session.getTransaction().rollback();
			if (proposition == null) {
				throw new IllegalArgumentException("Could not create a new Proposition" + title + description + ": " + 
						exception.getMessage() + ". Could not retrieve existing Proposition: " + title + description +
						"from database", exception);
			}
		}
		return proposition;
		
	}
	
	
	 
	/**
	 * Create a new race accompany with a Race Name(Position), and the ballot.
	 * @param: position: race name
	 * @param: ballot: The ballotEntity for the proposition
	 * 
	 */
	public Race createRace(String position, BallotEntity ballot) {
		Race race = null;
		Session session = HibernateUtil.getSession();
		session.beginTransaction();
		try {
			race = new RaceEntity(position, ballot);
			session.save(race);
			session.getTransaction().commit();
		}catch (PersistenceException | DateTimeException | IllegalArgumentException | NullPointerException exception) {
			session.getTransaction().rollback();
			if (race == null) {
				throw new IllegalArgumentException("Could not create a new race: " + position + ": " + 
						exception.getMessage() + ". Could not retrieve existing Candidate: " + position +
						"from database", exception);
			
			}
		}
		return race;
	}
	
	
	
	/**
	 * Create a new Candidate accompany with Candidate's name and Race Name(Position)
	 * name:  the name of the candidates who participated the race
	 * race:  the current race position that the candidates want participate 
	 * 
	 */
	public Candidate createCandidate(String name, String race) {
		Candidate candidate = null;
		Session session = HibernateUtil.getSession();
		session.beginTransaction();
		try {
			candidate = new CandidateEntity(name, race);
			session.save(candidate);
			session.getTransaction().commit();
		} catch (PersistenceException | DateTimeException | IllegalArgumentException | NullPointerException exception) {
			session.getTransaction().rollback();
			if (candidate == null) {
				throw new IllegalArgumentException("Could not create a new Candidate for the race: " + race + ": " + 
						exception.getMessage() + ". Could not retrieve existing Candidate: " + name + race +
						"from database", exception);
			}
		}
		return candidate;
	}
	
	
	
	/**
	 * createBallot: Create a new brand new ballot for the database (A BallotEntity)
	 */
	public BallotEntity createBallot(){
		System.out.println("Creating a new ballot...");
		BallotEntity ballot = null;
		Session session = HibernateUtil.getSession();
		try {
			ballot = new BallotEntity();
			/* Creating a new ballot corresponding to a ballot ID */
			session.saveOrUpdate(ballot);
			session.getTransaction().commit();
		} catch (PersistenceException | DateTimeException | IllegalArgumentException | NullPointerException exception) {
			session.getTransaction().rollback();
			if (ballot == null) {
				throw new IllegalArgumentException("Could not create a new Ballot " + 
						exception.getMessage());
			}
		}
		/* If created Successful */
		if(ballot != null) {
			System.out.println("Success! The database has been populated.");
		}
		return ballot;
	}
	
	
	
	/**
	 * deleteBallot: Delete an existed Ballot in database, and drop the reference tables. (A BallotEntity)
	 */
	public void deleteBallot(){
		System.out.println("Deleting ballot...");
		Session session = HibernateUtil.getSession();
		session.beginTransaction();
		try {
			session.createQuery("delete from CandidateEntity").executeUpdate();
			session.createQuery("delete from PropositionEntity").executeUpdate();
			session.createQuery("delete from BallotEntity").executeUpdate();
			session.createQuery("delete from RaceEntity").executeUpdate();
			session.createQuery("delete from VoterBallot").executeUpdate();
		} catch (NullPointerException exception) {
			System.out.println("No ballot currently exists");
		}
	}

	
	
	/**
	 * Updating the votes information for an individual voter, and record that into Table: VoterBallot
	 * @param: vb: VoterBallot Object
	 * @param: voteString: A string that include the information that the Voter voted.
	 */
	public static void updateIndividualVotes(VoterBallot vb, String voteString) {
		Session session = HibernateUtil.getSession();
		session.beginTransaction();
		try {
			vb.setVotes(voteString);
			session.saveOrUpdate(vb);
			session.getTransaction().commit();
		} catch(PersistenceException | DateTimeException | IllegalArgumentException | NullPointerException exception) {
			if(vb.getVotes().isEmpty()) {
				System.out.println("Did not update voter ballot");
			}
			session.getTransaction().rollback();
		}
	}
	
	
	
	/** 
	 * Getting the Voter's choices available information of the current Ballot
	 * @param: List<String> information: Return a list of string that stores all voter's ballot information 
	 */
	public List<String> getVoteBallotInformation() throws Exception{
		Session session = HibernateUtil.getSession();
		List<String> information = new ArrayList<String>();
		List<VoterBallot> ballotforVoter = null;
		try {
			ballotforVoter = session.createQuery("FROM VoterBallot", VoterBallot.class).list();
			for(Iterator<VoterBallot> iterator = ballotforVoter.iterator(); iterator.hasNext();){
				VoterBallot singleVoteBallot = (VoterBallot) iterator.next();
				information.add(singleVoteBallot.getVotes());
			}
			
		} catch(PersistenceException exception){
			
			if(ballotforVoter == null) {
				throw new Exception("Can not extract available voters' ballot Information from database !");
			}
		}
		return information;
	}

}


