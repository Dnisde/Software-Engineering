package edu.unl.cse.csce361.car_rental.rental_logic;
import edu.unl.cse.csce361.car_rental.backend.*;



public class IndividualCustomerBuilding {

	static Backend backend = Backend.getInstance();
	
	private Customer individualCustomer = null;
	private Customer newIndividualCustomer;
	
	private String name;
	private String streetAddress1;
	private String streetAddress2;
	private String city;
	private String state;
	private String zipCode;
	private String corporateAccount;
	private Double negotiatedRate;
	
	
	
	public String getName() {
		return name;
	}

	public String getStreetAddress1() {
		return streetAddress1;
	}

	public String getStreetAddress2() {
		return streetAddress2;
	}

	public String getCity() {
		return city;
	}

	public String getState() {
		return state;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setStreetAddress1(String streetAddress1) {
		this.streetAddress1 = streetAddress1;
	}

	public void setStreetAddress2(String streetAddress2) {
		this.streetAddress2 = streetAddress2;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public void setState(String state) {
		this.state = state;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public Double getNegotiagedRate() {
		return negotiatedRate;
	}

	public void setNegotiagedRate(Double negotiagedRate) {
		this.negotiatedRate = negotiagedRate;
	}

	/* 
	 * Creating a Individual Customer, and insert it into database 
	 * Option 1: If it already existed in database(Registered), instead of insert, it returns an existing (Object) Individual Customer
	 * Option 2: If it is not exist, create one and insert it into database
	 * 
	 * */
	public Customer setNewIndividualCustomer() {
 		setNewIndividualCustomer(
 				backend.createCorporateCustomer(this.name, this.streetAddress1, this.streetAddress2, 
 				this.city, this.state, this.zipCode, this.corporateAccount, this.negotiatedRate));
 		
 		return this.individualCustomer;
 	}
	
	/*
	 * Getting an existed Customer by Name:
	 * Option 1: If is not existed, it will give you an exception
	 * Option 2: If it existed, get it from Database
	 * 
	 */
	public Customer getExistingCustomerByName(String name) {
		
		this.individualCustomer = backend.getCustomer(name);
		return this.individualCustomer;
		
	}
	
	public IndividualCustomerBuilding build() {
		return (IndividualCustomerBuilding) setNewIndividualCustomer();
	}

	public Customer getNewIndividualCustomer() {
		return newIndividualCustomer;
	}

	public void setNewIndividualCustomer(Customer newIndividualCustomer) {
		this.newIndividualCustomer = newIndividualCustomer;
	}
	

}
