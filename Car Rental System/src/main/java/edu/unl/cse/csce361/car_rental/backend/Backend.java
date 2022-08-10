package edu.unl.cse.csce361.car_rental.backend;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import javax.persistence.PersistenceException;
import java.time.DateTimeException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * A facade for the Backend subsystem.
 */
public class Backend {

    private static Backend instance;

    public static Backend getInstance() {
        if (instance == null) {
            instance = new Backend();
        }
        return instance;
    }

    private Backend() {
        super();		/* RETRIEVES EXISTING OBJECTS */
    }

  
  
    /* 
	 * Filter Class, Step 1, Get all the Available Car's Manufacturer from the DataBase 
	 * 
	 * */
	public List<String> getAvailableMake() {
		List<Car> cars = null;
    	List<String> manufacturers = new ArrayList<String>();
    	Session session = HibernateUtil.getSession();
    	try {
    		
    		cars = session.createQuery("FROM CarEntity", Car.class).list();
    		for(Iterator<Car> iterator = cars.iterator(); iterator.hasNext();){
    			CarEntity car = (CarEntity) iterator.next();
    			if(car.getMake() == null) {
    				throw new NullPointerException("Can not extract available car from database..");
    			}
//    			System.out.println(car.getVin());
    			manufacturers.add(car.getMake());
    		}
    	} catch(PersistenceException exception){
    		session.getTransaction().rollback();
    		
    	}
    	/* Return all of the available VIN of Cars */
    	manufacturers = this.returnSingleList(manufacturers);
    	return manufacturers;
    }
	
	/* 
	 * Filter Class, Step 2
	 * Then based on CAR's Make to get available models
	 * 
	 * */

	public List<String> getAvailableModelByMake(String make) {
    	List<Car> cars = null;
    	List<String> model = new ArrayList<String>();
    	Session session = HibernateUtil.getSession();
    	try {
    		
    		cars = session.createQuery("FROM CarEntity", Car.class).list();
    		for(Iterator<Car> iterator = cars.iterator(); iterator.hasNext();){
    			CarEntity car = (CarEntity) iterator.next();
	    		if(car.getMake().equals(make)) {
	    			model.add(car.getModel());
	    		}
    		}
    		if(model.isEmpty() == true) {
    			throw new NullPointerException("Can not extract available model of car from database based on: " + make);
    		}
    	} catch(PersistenceException exception){
    		session.getTransaction().rollback();
    		
    	}
    	model = this.returnSingleList(model);
    	return model;
	}
	
	/* 
     * Based on CAR's Model to get available FuelType
     * Finishing user choices
     * And Using the filter to access Database
     * 
     * */
	public List<String> getAvailableFuelByModel(String model) {
		List<Model> models = null;
    	List<String> fuel = new ArrayList<String>();
    	Session session = HibernateUtil.getSession();
    	try {
    		
    		models = session.createQuery("FROM ModelEntity", Model.class).list();
    		for(Iterator<Model> iterator = models.iterator(); iterator.hasNext();){
    			ModelEntity modelofCar = (ModelEntity) iterator.next();
	    		if(modelofCar.getModel().equals(model)) {
	    			fuel.add(modelofCar.getFuel().toString());
	    		}
    		}
    		if(fuel.isEmpty()==true) {
    			throw new NullPointerException("Can not extract available fuel type of car from database based on: " + model);
    		}
    		
    	} catch(PersistenceException exception){
    		session.getTransaction().rollback();
    		
    	}
    	fuel = this.returnSingleList(fuel);
    	return fuel;
	}
	
	/* 
     * Based on CAR's Model to get available colors
     * Finishing user choices
     * And Using the filter to access Database
     * 
     * */
	
	public List<String> getAvailableColorByModel(String model) {
    	List<Car> cars = null;
    	List<String> color = new ArrayList<String>();
    	Session session = HibernateUtil.getSession();
    	try {
    		
    		cars = session.createQuery("FROM CarEntity", Car.class).list();
    		for(Iterator<Car> iterator = cars.iterator(); iterator.hasNext();){
    			CarEntity car = (CarEntity) iterator.next();
    			if(car.getModel().equals(model)) {
	    			color.add(car.getColor());
	    		}
    		}
    		if(color.isEmpty() == true) {
    			throw new NullPointerException("Can not extract available color of car from database based on: " + model);
    		}
    	} catch(PersistenceException exception){
    		session.getTransaction().rollback();
    		
    	}
    	color = this.returnSingleList(color);
    	return color;
    }
	
	
	
	/* 
	 * !!! -- !!!
	 * Based on User's selection, 
	 * this method will be implemented by using filter to select available Car from Database 
	 * It will return a number of VINs of Available Cars that could be rental
	 * 
	 * */
	public List<String> getAvailableCarsVinByFilter(String make, String model) {
    	List<Car> cars = null;
    	List<String> Vin = new ArrayList<String>();
    	Session session = HibernateUtil.getSession();
    	try {
    		
    		cars = session.createQuery("FROM CarEntity", Car.class).list();
    		for(Iterator<Car> iterator = cars.iterator(); iterator.hasNext();){
    			CarEntity car = (CarEntity) iterator.next();
    			if(car.getMake().equals(make) && car.getModel().equals(model)) {
    				Vin.add(car.getVin());
    			}
//    			System.out.println(car.getVin());
    		}
    		if(Vin.isEmpty() == true) {
				throw new IllegalStateException("Can not extract available car from database..");
			}
    	} catch(PersistenceException exception){
    		session.getTransaction().rollback();
    		
    	}
    	/* Return all of the available VIN of Cars */
    	Vin = this.returnSingleList(Vin);
    	return Vin;
    }
	
	
	/* 
	 * Based on the filter's returned VINs, 
	 * it will find the attributes of Car's from database that using in rental_logic package
	 * 
	 * */
	public List<String> getAttributesOfCarsByVin(String vin){
		List<String> attributesOfCar = new ArrayList<String>();
		String[] modelAttributes = null;
		String model_name = null;
		List<Car> cars = null;
		List<Model> models = null;
		Session session = HibernateUtil.getSession();
		try {
    		/* Get the Cars list from Database */
    		cars = session.createQuery("FROM CarEntity", Car.class).list();
    		for(Iterator<Car> iterator = cars.iterator(); iterator.hasNext();){
    			CarEntity car = (CarEntity) iterator.next();
    			if(car.getVin().equals(vin)) {
    				/* Extract Vin number from database */
    				model_name = car.getModel();
    				attributesOfCar.add(car.getMake());
    				attributesOfCar.add(car.getModel());
    				attributesOfCar.add(car.getVin());
    				attributesOfCar.add(car.getLicensePlate());
    				attributesOfCar.add(car.getColor());
    			}
//    			System.out.println(car.getVin());
    		}
    		if(model_name == null) {
				throw new IllegalStateException("Can not extract available car from database..");
			}
    	} catch(PersistenceException exception){
    		session.getTransaction().rollback();
    		
    	}
		
		if(!(model_name == null)) {
			Session session2 = HibernateUtil.getSession();
			try {
	    		/* Get the Cars list from Database */
	    		models = session2.createQuery("FROM ModelEntity", Model.class).list();
	    		for(Iterator<Model> iterator = models.iterator(); iterator.hasNext();){
	    			ModelEntity modelAttribute = (ModelEntity) iterator.next();
	    			if(modelAttribute.getModel().equals(model_name)) {
	    				/* Extract All attributes from database */
	    				
	    				attributesOfCar.add(modelAttribute.getNumberOfDoors().get().toString());
	    				attributesOfCar.add(modelAttribute.getTransmission().toString());
	    				attributesOfCar.add(modelAttribute.getClassType().toString());
	    				attributesOfCar.add(modelAttribute.getFuel().toString());
	    				attributesOfCar.add(modelAttribute.getFuelEconomyKPL().toString());
	    				attributesOfCar.add(modelAttribute.getFuelEconomyLP100K().toString());
	    				attributesOfCar.add(modelAttribute.getFuelEconomyMPG().toString());
	    			}
//	    			System.out.println(car.getVin());
	    		}
	    	} catch(PersistenceException exception){
	    		session2.getTransaction().rollback();
	    		
	    	}
//			modelAttributes = Backend.getModelByName(model_name).toString().split(",");
			
		}
//		for(int i=0;i<modelAttributes.length;i++) {
//			attributesOfCar.add(modelAttributes[i]);
//		}
		System.out.println("Car attributes are: " + attributesOfCar);
		return attributesOfCar;
	}

    /**
     * Retrieves the customer that has the specified name, if such a customer exists.
     *
     * @param name The name of the customer
     * @return The specified customer if it is present in the database; <code>null</code> otherwise
     */
    public Customer getCustomer(String name) {
    	
        return CustomerEntity.getCustomerByName(name);
    }
    
    



    
    static Model getModelByName(String name) {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        Model model = null;
        try {
            model = session.bySimpleNaturalId(ModelEntity.class).load(name);
            session.getTransaction().commit();
        } catch (HibernateException exception) {
            System.err.println("Could not load Model " + name + ". " + exception.getMessage());
        }
        return model;
    }
    

    /**
     * Retrieves a collection of car models that are of the specified vehicle class.
     *
     * @param vehicleClass The name of the vehicle class
     * @return A set of car models
     */
    public Set<Model> getModels(String vehicleClass) {
        Set<Model> models;
        try {
            Model.VehicleClass vehicleClassEnum = Model.VehicleClass.valueOf(vehicleClass.toUpperCase());
            
            models = ModelEntity.getModelsByClass(vehicleClassEnum);
          
        } catch (IllegalArgumentException | NullPointerException exception) {
            System.err.println("No such vehicle class: " + vehicleClass.toUpperCase() + ". " + exception.getMessage());
            models = Set.of();
        }
        return models;
    }
    
    
    /* CREATES  NEW OBJECTS */
    
    /**
     * <p>Creates a new car model with the specified parameters. The model's name must be unique. If there is a notional
     * model that has different options (such as available with an automatic transmission and also is available with
     * a manual transmission, it is common to provide "qualifiers" in the model name, such as "SUX 2000-A" and "SUX
     * 2000-M". If the model already exists, then the existing model will be returned (with the existing parameters,
     * not the specified parameters).</p>
     * <p>The model name cannot be <code>null</code>. Any other <code>null</code>able parameters may be
     * <code>null</code>, which indicates their actual values are unknown. The <code>enum</code> parameters may be
     * <code>UNKNOWN</code> to indicate their actual values are unknown.</p>
     *
     * @param manufacturer   The name of the car model's manufacturer, also known as its "make"
     * @param model          The name of the model
     * @param classType      The vehicle class, such as SUV or ECONOMY
     * @param numberOfDoors  The number of doors the vehicle has
     * @param transmission   The transmission type, such as AUTOMATIC or MANUAL
     * @param fuel           The type of fuel the vehicle uses, such as GASOLINE or PLUGIN_ELECTRIC
     * @param fuelEconomyMPG The car's fuel efficiency, measured in miles per gallon (or miles per gallon equivalent)
     * @return a new car model with the specified parameters, or an existing car model with the same model name
     * @throws IllegalStateException if a new car model by the specified model name cannot be added to the data
     *                               store, and also an existing car model by the specified name cannot be retrieved
     *                               from the data store
     */
    public Model createModel(String manufacturer, String model, Model.VehicleClass classType, Integer numberOfDoors,
                             Model.Transmission transmission, Model.Fuel fuel, Integer fuelEconomyMPG)
            throws IllegalStateException {
        Model carModel;
        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        try {
            carModel = new ModelEntity(manufacturer, model, classType, numberOfDoors, transmission, fuel,
                    fuelEconomyMPG);
            session.saveOrUpdate(carModel);
            session.getTransaction().commit();
        } catch (PersistenceException exception) {
            session.getTransaction().rollback();
            carModel = ModelEntity.getModelByName(model);
            if (carModel == null) {
                throw new IllegalStateException("Could not create new car model " + model + ": " +
                        exception.getMessage() + ". Could not retrieve existing car model " + model +
                        " from database.", exception);
            }
        }
        return carModel;
    }
    
    
    /**
     * <p>Creates a new car with the specified parameters. The car's Vehicle Identification Number (VIN) must be unique.
     * The license plate must also be unique if it is not <code>null</code>. If the car already exists  (based on the
     * VIN), then the existing car will be returned (with the existing parameters, not the specified parameters)</p>
     *
     * <p>The VIN cannot be <code>null</code>. Any other parameters may be <code>null</code>, which indicates their
     * actual values are unknown.</p>
     *
     * @param model        The name of the car's model
     * @param color        The name of the car's color
     * @param licensePlate The car's license plate number
     * @param vin          The car's Vehicle Identification Number
     * @return a new car with the specified parameters, or an existing car model with the same VIN
     * 
     *                              
     */
    public Car createCar(String model, String color, String licensePlate, String vin) throws IllegalStateException {
    	/*    	new CarEntity("Ranger", "Blue", "123 ABC", "1234567890ABCDEFG"); */
    	Car cars = null;
    	Session session = HibernateUtil.getSession();
    	session.beginTransaction();
    	try {
    		cars = new CarEntity(model, color, licensePlate, vin);
    		session.saveOrUpdate(cars);
    		session.getTransaction().commit();
    	} catch(PersistenceException exception){
    		session.getTransaction().rollback();
    		cars = Backend.getCarsByVin(vin); // debug
    		// if the car already exists in the database
    		if (cars == null) {
    			throw new IllegalStateException("Could not create a new car" + vin + ": " +
                        exception.getMessage() + ". Could not retrieve existing cars" + vin +
                        " from database.", exception);
    		}
    	}
    	
        return cars;
    }
    
    
    /*
     * This is basically a searching for the car's method by using unique ID: vin.
     */
    static Car getCarsByVin(String vin) {
    	 Session session = HibernateUtil.getSession();
         session.beginTransaction();
         Car cars = null;
         try {
             cars = session.bySimpleNaturalId(CarEntity.class).load(vin);
             session.getTransaction().commit();
         } catch (HibernateException exception) {
             System.err.println("Could not load Car " + vin + ". " + exception.getMessage());
         }
         return cars;
    }

    /**
     * <p>Creates a new corporate customer with the specified parameters. The customer's name must be unique. Only
     * the second street address may be <code>null</code> (which is treated as the equivalent of a blank line);
     * similarly, only the second street address may be an empty string. If the customer already exists (based on the
     * name), then the existing customer will be returned (with the existing parameters, not the specified
     * parameters).</p>
     * <p>If a new customer is created, it is guaranteed to be a {@link CorporateCustomer}. If an existing customer is
     * retrieved, it is only guaranteed to be a {@link Customer}.</p>
     *
     * @param name             The customer's name
     * @param streetAddress1   The first line of the customer's street address
     * @param streetAddress2   The second line of the customer's street address
     * @param city             The city of the customer's address
     * @param state            The abbreviation for the state of the customer's address
     * @param zipCode          The customer's 5-digit zip code
     * @param corporateAccount The customer's corporate account number
     * @param negotiatedRate   The customer's negotiated rate
     * @return a new corporate customer with the specified parameters, or an existing customer with the same name
     * @throws IllegalStateException if an argument is too long, too short, contains illegal characters, or has an
     *                               invalid value
     * @throws NullPointerException  if an argument (other than <code>streetAddress2</code>) is <code>null</code>
     */
    public Customer createCorporateCustomer(String name, String streetAddress1, String streetAddress2,
                                            String city, String state, String zipCode,
                                            String corporateAccount, Double negotiatedRate)
            throws IllegalStateException, NullPointerException {
    	Customer corporate = null;
    	Session session = HibernateUtil.getSession();
    	session.beginTransaction();
    	try {
    		/* If an argument (other than <code>streetAddress2</code>) is <code>null</code> */
    		if(name == null | streetAddress1 == null | city == null | 
    				state == null | zipCode == null | corporateAccount == null | negotiatedRate == null) 
    			throw new NullPointerException("There existing blanks that should not be empty, please fill them complete (Except Second line of Address)");
    		/* If an argument is too long, too short, contains illegal characters, or has an invalid value */
    		this.limitateCharacter(name, 20);
    		this.limitateCharacter(city, 10);
    		this.limitateCharacter(state, 10);
    		this.limitateNum(zipCode, 5, 5);
    		this.limitateNum(corporateAccount, 10, 12);
    		/* Otherwise, insert a valid new Corporate Customer into database. */
    		corporate = new CorporateCustomerEntity(name, streetAddress1, streetAddress2,
                    city, state, zipCode, corporateAccount, negotiatedRate);
    		session.saveOrUpdate(corporate);
    		session.getTransaction().commit();
    		
    	} catch(PersistenceException exception){
    		session.getTransaction().rollback();
    		corporate = CustomerEntity.getCustomerByName(name);
    		if (corporate == null) {
    			throw new IllegalStateException("Could not create a new Coporate Customer" + name + ": " +
                        exception.getMessage() + ". Could not retrieve existing Coporate Customer" + name +
                        " from database.", exception);
    		}
    	}
    	
        return corporate;
    }

    
    /**
   
     * <p>Creates a new individual customer with the specified parameters. The customer's name must be unique. Only
     * the second street address may be <code>null</code> (which is treated as the equivalent of a blank line);
     * similarly, only the second street address may be an empty string. If the customer already exists (based on the
     * name), then the existing customer will be returned (with the existing parameters, not the specified
     * parameters).</p>
     * <p>If a new customer is created, it is guaranteed to be a {@link IndividualCustomer}. If an existing customer is
     * retrieved, it is only guaranteed to be a {@link Customer}.</p>
     * <p>Use this method only if the payment card information is not yet known. If the payment card information is
     * known, use
     * {@link #createIndividualCustomer(String, String, String, String, String, String, String, int, int, String)}</p>
     *
     * @param name           The customer's name
     * @param streetAddress1 The first line of the customer's street address
     * @param streetAddress2 The second line of the customer's street address
     * @param city           The city of the customer's address
     * @param state          The abbreviation for the state of the customer's address
     * @param zipCode        The customer's 5-digit zip code
     * @return a new individual customer with the specified parameters, or an existing customer with the same name
     * @throws IllegalArgumentException if an argument is too long, too short, or contains illegal characters
     * @throws NullPointerException     if an argument (other than <code>streetAddress2</code>) is <code>null</code>
     */
    public Customer createIndividualCustomer(String name, String streetAddress1, String streetAddress2,
                                             String city, String state, String zipCode)
            throws IllegalArgumentException, NullPointerException {
    	Customer individual = null;
    	Session session = HibernateUtil.getSession();
    	session.beginTransaction();
    	try {
    		/* If an argument (other than <code>streetAddress2</code>) is <code>null</code> */
    		if(name == null | streetAddress1 == null | city == null | 
    				state == null | zipCode == null) 
    			throw new NullPointerException("There existing blanks that should not be empty, please fill them complete (Except Second line of Address)");
    		/* If an argument is too long, too short, contains illegal characters, or has an invalid value */
    		this.limitateCharacter(name, 20);
    		this.limitateCharacter(city, 10);
    		this.limitateCharacter(state, 10);
    		this.limitateNum(zipCode, 5, 5);
    		/* Otherwise, insert a valid new Individual Customer into database. */
    		individual= new IndividualCustomerEntity(name, streetAddress1, streetAddress2,
                    city, state, zipCode);
    		session.saveOrUpdate(individual);
    		session.getTransaction().commit();
    		
    	} catch(PersistenceException exception){
    		session.getTransaction().rollback();
    		individual = CustomerEntity.getCustomerByName(name);
    		if (individual == null) {
    			throw new IllegalStateException("Could not create a new Individual Customer" + name + ": " +
                        exception.getMessage() + ". Could not retrieve existing Individual Customer" + name +
                        " from database.", exception);
    		}
    	}
    	
        return individual;

    }

    /**
     * <p>Creates a new individual customer with the specified parameters. The customer's name must be unique. Only
     * the second street address may be <code>null</code> (which is treated as the equivalent of a blank line);
     * similarly, only the second street address may be an empty string. If the customer already exists (based on the
     * name), then the existing customer will be returned (with the existing parameters, not the specified
     * parameters).</p>
     * <p>If a new customer is created, it is guaranteed to be a {@link IndividualCustomer}. If an existing customer is
     * retrieved, it is only guaranteed to be a {@link Customer}.</p>
     * <p>If the payment card information is not yet known, use
     * {@link #createIndividualCustomer(String, String, String, String, String, String)}</p>
     *
     * @param name                       The customer's name
     * @param streetAddress1             The first line of the customer's street address
     * @param streetAddress2             The second line of the customer's street address
     * @param city                       The city of the customer's address
     * @param state                      The abbreviation for the state of the customer's address
     * @param zipCode                    The customer's 5-digit zip code
     * @param paymentCardNumber          The customer's payment card number
     * @param paymentCardExpirationMonth The month of the payment card's expiration date
     * @param paymentCardExpirationYear  The year of the payment card's expiration date
     * @param paymentCardCvv             The payment card's Card Verification Value
     * @return a new individual customer with the specified parameters, or an existing customer with the same name
     * @throws DateTimeException        if the payment card's month or year are invalid
     * @throws IllegalArgumentException if an argument is too long, too short, or contains illegal characters
     * @throws NullPointerException     if an argument (other than <code>streetAddress2</code>) is <code>null</code>
     */
    public Customer createIndividualCustomer(String name, String streetAddress1, String streetAddress2,
                                             String city, String state, String zipCode,
                                             String paymentCardNumber, Integer paymentCardExpirationMonth,
                                             Integer paymentCardExpirationYear, String paymentCardCvv)
            throws DateTimeException, IllegalArgumentException, NullPointerException {
    	Customer individual = null;
    	Session session = HibernateUtil.getSession();
    	session.beginTransaction();
    	try {
    		/* If an argument (other than <code>streetAddress2</code>) is <code>null</code> */
    		if(name == null | streetAddress1 == null | city == null | 
    				state == null | zipCode == null | paymentCardNumber == null | 
    				paymentCardExpirationMonth == null | paymentCardExpirationYear == null |
    				paymentCardCvv == null) 
    			throw new NullPointerException("There existing blanks that should not be empty, please fill them complete (Except Second line of Address)");
    		/* If an argument is too long, too short, contains illegal characters, or has an invalid value */
    		this.limitateCharacter(name, 20);
    		this.limitateCharacter(city, 10);
    		this.limitateCharacter(state, 10);
    		this.limitateNum(zipCode, 5, 5);
    		this.limitateNum(paymentCardNumber, 8, 19);
    		this.limitateNum(paymentCardExpirationMonth.toString(), 2, 2);
    		this.limitateNum(paymentCardExpirationYear.toString(), 2, 2);
    		this.limitateNum(paymentCardCvv, 3, 3);
    		/* Otherwise, insert a valid new Individual Customer into database. */
    		individual= new IndividualCustomerEntity(name, streetAddress1, streetAddress2,
                    city, state, zipCode);
    		session.saveOrUpdate(individual);
    		session.getTransaction().commit();
    		
    	} catch(PersistenceException exception){
    		session.getTransaction().rollback();
    		individual = CustomerEntity.getCustomerByName(name);
    		if (individual == null) {
    			throw new IllegalStateException("Could not create a new Individual Customer" + name + ": " +
                        exception.getMessage() + ". Could not retrieve existing Individual Customer" + name +
                        " from database.", exception);
    		}
    	}
    	
        return individual;
    }
    
    /* 
     * This is limitation method of construct string with valid only by exactly digits of numbers;
     * And has to be Numbers at the same time
     *  */
    public void limitateNum(String string, Integer min_char, Integer max_char) throws IllegalStateException{
    	final int MIN_CHAR = min_char;
    	final int MAX_CHAR = max_char;
    	int maxLength = (string.length() < MAX_CHAR)?string.length():MAX_CHAR;
    	String inputString = string.substring(0, maxLength);
    	// IF the user input only contains with number
    	if(string.matches("[0-9]+") == true) {
	    	if(string.length() > MAX_CHAR) {
	    		System.out.println("Your entering is invalid: Number is too long, should lesser than " + MAX_CHAR + " numerical digits like: " + inputString);
	    		throw new IllegalStateException
	    		("Your entering is invalid: Number is too long, should lesser than " + MAX_CHAR + " numerical digits like: " + inputString);
	    		
	    	}if(string.length() < MIN_CHAR) {
	    		System.out.println("Your entering is invalid: Number is too short, should  be at least " + MIN_CHAR + " numerical digits");
	    		throw new IllegalStateException
	    		("Your entering is invalid: Number is too short, should  be at least " + MIN_CHAR + " numerical digits");
	    	}
	    		
    	}
    	// IF the user input doesn't only contains with number
    	else {
    		System.out.println("Your entering is invalid: Contains illegal characters (Only numbers being accepted) ");
    		throw new IllegalStateException("Your entering is invalid: Contains illegal characters (Only numbers being accepted) ");
    	}
    	
    	
    }
    
    
    /* 
     * This is limitation method of construct string with valid only by characters; 
     * And lesser than limit amount of MAXIMUM CHARS at the same time 
     * */
    public void limitateCharacter(String string, Integer max_char) throws IllegalStateException{
    	final int MAX_CHAR = max_char;
    	// IF the user input only contains with characters
    	if(string.matches("[a-zA-Z]+") == true) {
    		if(string.length() > MAX_CHAR) {
	    		System.out.println("Your entering is invalid: Characters is too long, should be lesser than " + MAX_CHAR + " characters allowed ");
	    		throw new IllegalStateException
	    		("Your entering is invalid:  Characters is too long, should be lesser " + MAX_CHAR + " characters allowed ");
	    		
	    	}
    	}
    	// IF the user input doesn't only contains with characters
    	else {
    		System.out.println("Your entering is invalid: Contains illegal characters (Only characters being accepted) ");
    		throw new IllegalStateException("Your entering is invalid: Contains illegal characters (Only characters being accepted) ");
    	}
    }
    
    public void limitNumYearMonth(Integer integer, Integer limitmonth, Integer limityear) {
    	
    }
    
	private List<String> returnSingleList(List<String> demolist) {
    	/* Create a hashmap to check the duplicate value */
    	List<String> unduplicateList = demolist;
    	HashSet<String> h = new HashSet<String>(unduplicateList);   
    	unduplicateList.clear();   
    	unduplicateList.addAll(h);   
    	return unduplicateList;
    }
    
    
}

