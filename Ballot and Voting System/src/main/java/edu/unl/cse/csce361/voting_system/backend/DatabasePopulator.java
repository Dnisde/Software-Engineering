package edu.unl.cse.csce361.voting_system.backend;

import java.util.Set;

import javax.persistence.PersistenceException;

import org.hibernate.MappingException;
import org.hibernate.Session;

public class DatabasePopulator {
	
	static BallotEntity ballot;
	
	static Set<Person> createVoters() {
		System.out.println("Creating voters...");
		return Set.of(
				new VoterEntity("123456789", "Dennis Reynolds", "123 Frank St", "Apt 2", "Lincoln","NE","12345","1983-12-09"),
				new VoterEntity("112233445", "Barack Obama", "789 South St", "", "Lincoln","NE","68508","1974-04-24"),
				new VoterEntity("987654321", "Ernie Chambers", "6543 Lincoln Mall", "5", "Lincoln","NE","12345","1941-07-12"));
		
	}
	
	static Set<Person> createElectionOfficials() {
		System.out.println("Creating election officials...");
		return Set.of(
				new ElectionOfficialEntity("554433221", "Imin Charge"),
				new ElectionOfficialEntity("246801357", "Coffee House"));		
	}
	
	static Set<Candidate> createCandidates() {
		System.out.println("Creating candidates...");
		return Set.of(
				new CandidateEntity("Patt Mann", "Mayor"),
				new CandidateEntity("Dawn Keykong", "Mayor"),
				new CandidateEntity("Inky", "City Council"),
				new CandidateEntity("Blinky", "City Council"),
				new CandidateEntity("Q. Burte", "Sheriff"));
	}
	
	static BallotEntity createBallot() {
		System.out.println("Creating ballot...");
		ballot = new BallotEntity();
		return ballot;
	}

	static Set<Proposition> createPropositions() {
		System.out.println("Creating propositions...");
		return Set.of(
				new PropositionEntity("Proposition 1", "Shall there be a 25¢ tax on cherries?", ballot),
				new PropositionEntity("Proposition 2", "Shall liquor licenses be required for electronic bars?", ballot),
				new PropositionEntity("Proposition 3", "Shall electronic race tracks be held liable for electronic car crashes?", ballot));
	}
	
	static Set<RaceEntity> createRaces() {
		System.out.println("Creating races...");
		return Set.of(
				new RaceEntity("Mayor", ballot),
				new RaceEntity("City Council", ballot),
				new RaceEntity("Sheriff", ballot));
	}
	
	static void depopulateTables(Session session) {
        System.out.println("Emptying tables...");
        session.createQuery("delete from PersonEntity").executeUpdate();
        session.createQuery("delete from ElectionOfficialEntity").executeUpdate();
        session.createQuery("delete from VoterEntity").executeUpdate();
        session.createQuery("delete from CandidateEntity").executeUpdate();
        session.createQuery("delete from PropositionEntity").executeUpdate();
        session.createQuery("delete from BallotEntity").executeUpdate();
        session.createQuery("delete from RaceEntity").executeUpdate();
        session.createQuery("delete from VoterBallot").executeUpdate();
    }
	
    public static void main(String[] args) {
        System.out.println("Creating Hibernate session...");
        Session session = HibernateUtil.getSession();
        System.out.println("Starting Hibernate transaction...");
        session.beginTransaction();
        try {
            depopulateTables(session);
            createVoters().forEach(session::saveOrUpdate);
            createElectionOfficials().forEach(session::saveOrUpdate);
            session.saveOrUpdate(createBallot());
            createPropositions().forEach(session::saveOrUpdate);
            createRaces().forEach(session::saveOrUpdate);
            createCandidates().forEach(session::saveOrUpdate);
            System.out.println("Concluding Hibernate transaction...");
            session.getTransaction().commit();
            System.out.println("Success! The database has been populated.");
        } catch (MappingException mappingException) {
            System.err.println("Problem encountered when creating a table. The most likely problem is a missing\n" +
                    "    <mapping class=\"...\"/> tag in hibernate.cfg.xml, but it's possible the\n" +
                    "    problem is that the programmer is attempting to load an interface instead\n" +
                    "    of an entity.");
            StackTraceElement[] trace = mappingException.getStackTrace();
            System.err.println("  " + trace[trace.length - 1]);
            System.err.println("  " + mappingException.getMessage());
            session.getTransaction().rollback();
        } catch (PersistenceException persistenceException) {
            System.err.println("Problem encountered when populating or depopulating a table. It's not clear why\n" +
                    "    this would happen unless it's a network or server failure. But it's probably\n" +
                    "    something completely unexpected.");
            StackTraceElement[] trace = persistenceException.getStackTrace();
            System.err.println("  " + trace[trace.length - 1]);
            System.err.println("  " + persistenceException.getMessage());
            System.err.println("  " + persistenceException.getCause().getCause().getMessage());
            session.getTransaction().rollback();
        } catch (Exception exception) {
            System.err.println("Problem encountered that (probably) has nothing to do with creating and\n" +
                    "    (de)populating tables. This is (most likely) is a plain, old-fashioned\n" +
                    "    programming boo-boo.");
            StackTraceElement[] trace = exception.getStackTrace();
            System.err.println("  " + trace[trace.length - 1]);
            System.err.println("  " + exception.getMessage());
            session.getTransaction().rollback();
        } finally {
            HibernateUtil.closeSession(session);
        }
    }

}
