package edu.unl.cse.csce361.voting_system.backend;

import java.util.Optional;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import org.hibernate.HibernateException;
import org.hibernate.Session;

/**
 * Hibernate implementation of {@link Voter}.
 */
@Entity
public class VoterEntity extends PersonEntity implements Voter {
    	
	@OneToOne(fetch = FetchType.EAGER)
    private PersonEntity person;
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "personId")
	private VoterBallot voterBallot;
	
	@Column(nullable = false, length = Person.MAXIMUM_LINE_LENGTH)
    private String dateOfBirth;
    @Column(nullable = false, length = Person.MAXIMUM_LINE_LENGTH)
    private String address1;
    @Column(length = Person.MAXIMUM_LINE_LENGTH)
    private String address2;
    @Column(nullable = false, length = Voter.MAXIMUM_CITY_LENGTH)
    private String city;
    @Column(nullable = false, length = Voter.STATE_LENGTH)
    private String state;
    @Column(nullable = false, length = Voter.ZIPCODE_LENGTH)
    private String zipCode;
    
	public VoterEntity () {
		super();
	}
	
	public VoterEntity(String ssn, String name, String address1, String address2,
						String city, String state, String zipCode, String dateOfBirth) 
						throws IllegalArgumentException, NullPointerException{
		super(ssn, name);
		this.dateOfBirth = dateOfBirth;
		setAddress(address1, address2, city, state, zipCode);
	}
	
	@Override
	public String getAddress() {
		StringBuilder address = new StringBuilder();
        address.append(address1).append(System.lineSeparator());
        if (!address2.equals("")) {
            address.append(address2).append(System.lineSeparator());
        }
        address.append(city).append(", ").append(state).append("  ").append(zipCode);
        return address.toString();
	}
	
	@Override
	public String getDateOfBirth() {
		return dateOfBirth;
	}
	
	public static VoterEntity getVoterBySsn(String ssn) {
		Session session = HibernateUtil.getSession();
        VoterEntity voterEntity = null;
        try {
        	if(session.bySimpleNaturalId(PersonEntity.class).load(ssn) instanceof VoterEntity) {
        		voterEntity = (VoterEntity) session.bySimpleNaturalId(PersonEntity.class).load(ssn);
        	}
        } catch (HibernateException exception) {
            System.err.println("Could not load Voter " + ssn + ". " + exception.getMessage());
        }
		return voterEntity;
	}
	
	@Override
	public VoterBallot getVoterBallot() {
		return this.voterBallot;
	}
	
	public void setAddress(String streetAddress1, String streetAddress2, String city, String state, String zipCode)
            throws IllegalArgumentException, NullPointerException {
        if ((streetAddress1 == null) || (city == null) || state == null || zipCode == null) {
            throw new NullPointerException("In an address, only the second street address line can be null.");
        }
        if (streetAddress1.isBlank() || city.isBlank() || state.isBlank() || zipCode.isBlank()) {
            throw new IllegalArgumentException("In an address, only the second street address line can be blank.");
        }
        if (streetAddress1.length() > Person.MAXIMUM_LINE_LENGTH) {
            throw new IllegalArgumentException("First address line is " + streetAddress1.length() +
                    " characters long, exceeding maximum length of " + Person.MAXIMUM_LINE_LENGTH + " characters.");
        }
        if ((streetAddress2 != null) && (streetAddress2.length() > Person.MAXIMUM_LINE_LENGTH)) {
            throw new IllegalArgumentException("First address line is " + streetAddress2.length() +
                    " characters long, exceeding maximum length of " + Person.MAXIMUM_LINE_LENGTH + " characters.");
        }
        if (city.length() > Voter.MAXIMUM_CITY_LENGTH) {
            throw new IllegalArgumentException("First address line is " + city.length() +
                    " characters long, exceeding maximum length of " + Voter.MAXIMUM_CITY_LENGTH + " characters.");
        }
        if (state.length() != Voter.STATE_LENGTH) {
            throw new IllegalArgumentException("State abbreviation must be exactly " + Voter.STATE_LENGTH + " letters");
        }
        if (zipCode.length() != Voter.ZIPCODE_LENGTH) {
            throw new IllegalArgumentException("Zip code must contain exactly " + Voter.ZIPCODE_LENGTH + " digits");
        }
        this.address1 = streetAddress1;
        this.address2 = Optional.ofNullable(streetAddress2).orElse("");
        this.city = city;
        this.state = state.toUpperCase();
        this.zipCode = zipCode;
    }
	
	public void setVoterBallot(VoterBallot vb) {
		this.voterBallot = vb;
	}

}
