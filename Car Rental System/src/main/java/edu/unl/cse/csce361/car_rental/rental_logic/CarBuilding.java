package edu.unl.cse.csce361.car_rental.rental_logic;

import edu.unl.cse.csce361.car_rental.backend.Backend;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;



public class CarBuilding{
	
	
	static Backend backend = Backend.getInstance();
	
	private List<String> makeOfCars;
	
	private List<String> modelOfCars;
	
	private List<String> vinOfCars;
	
	private List<String> attributesOfCar;
 	
 	public List<String> getMakeOfCar() {
 		makeOfCars = new ArrayList<String>();
 		makeOfCars = backend.getAvailableMake();
 		return makeOfCars;
 	}
 	
 	public List<String> getModelOfCarByMake(String make){
 		modelOfCars = new ArrayList<String>();
 		modelOfCars = backend.getAvailableModelByMake(make);
 		return modelOfCars;
 	}
 	
 	public List<String> getVinOfCarByFilter(String make, String model){
 		vinOfCars = new ArrayList<String>();
 		vinOfCars = backend.getAvailableCarsVinByFilter(make, model);
 		return vinOfCars;
 	}
 	
 	public List<String> getAttributesOfCarbyVin(String vin){
 		attributesOfCar = new ArrayList<String>();
 		attributesOfCar = backend.getAttributesOfCarsByVin(vin);
 		return attributesOfCar;
 		
 	}

 	


	public CarBuilding build() {
		
		return null;
 		
 	}
 	
 	public class Carbuilder{
 		
 	}
//	
	/* A kind of demo to show the functionality works in Backend.java 
	 * 
	 * Comment this out if needed
	 * 
	public static void main(String[] args) {
		
    	System.out.println(backend.getAvailableMake());
    	System.out.println(backend.getAvailableModelByMake("Ford"));
    	System.out.println(backend.getAvailableCarsVinByFilter("Ford", "Ranger"));
    	backend.getAttributesOfCarsByVin("1234567890ABCDEFG");
	}
       
	*/
	

}
