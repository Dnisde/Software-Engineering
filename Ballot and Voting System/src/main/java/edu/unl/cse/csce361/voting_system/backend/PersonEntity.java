package edu.unl.cse.csce361.voting_system.backend;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Id;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.annotations.NaturalId;


/**
 * Hibernate implementation of {@link Person}.
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class PersonEntity implements Person {
	
	@SuppressWarnings("unused")
    @Id
    @GeneratedValue
    private Long personId;
	
	@NaturalId
    @Column(unique = true, length = Person.MAXIMUM_LINE_LENGTH)   // apparently we have to define the size to enforce NaturalID's uniqueness?
    private String ssn;
    @Column(length = Person.MAXIMUM_LINE_LENGTH)
    private String name;

    public PersonEntity () {
    	super();
    }
    
    public PersonEntity(String ssn, String name) 
			throws IllegalArgumentException, NullPointerException{
    	this.ssn = ssn;
    	this.name = name;
    }
    
    @Override
    public String getName() {
        return name;
    }
    
    @Override
    public String getSsn() {
    	return ssn;
    }
    
    public Long getId() {
    	return personId;
    }
   
    static Person getPersonBySsn(String ssn) {
		Session session = HibernateUtil.getSession();
        session.beginTransaction();
        Person person = null;
        try {
            person = session.bySimpleNaturalId(PersonEntity.class).load(ssn);
            session.getTransaction().commit();
        } catch (HibernateException exception) {
            System.err.println("Could not load Person " + ssn + ". " + exception.getMessage());
        }
        return person;
	}

}
