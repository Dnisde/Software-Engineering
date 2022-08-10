package edu.unl.cse.csce361.car_rental.rental_logic;
import edu.unl.cse.csce361.car_rental.backend.*;

public class RentalLogic {

    private static RentalLogic instance;
    
    /* 
     * Creating a Facade of instance that being allowed to access all the function 
     */
    public static RentalLogic getInstance() {
        if (instance == null) {
            instance = new RentalLogic();
        }
        return instance;
    }

    private RentalLogic() {
        super();		/* RETRIEVES EXISTING OBJECTS */
    }
    
    Backend backend = Backend.getInstance();

}
