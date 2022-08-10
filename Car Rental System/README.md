# Car Rental System

##  The UI is not completely functional but here is a list of what we implemented

- **Builder Pattern**
- **Logic Layer**
- **Facade**
- **Decorator**

## Three-tier Architecture:
  - backend: Communication with database, access the data of car information and customer information.
  - frontend: Car_Rental system UI, the interface of instagram.
  - rental_logic: Intermediate logic between frontend and backend.

## Builder Pattern:
  - rental_logic:

    - CarBuilding.java: Created Backend.java's instantiate in order to being able to access the method from backend architecture. More details will being able to check in *Backend.java.*

    - CorporateCustomerBuilding.java: Suppose to be the same structure as *InidividualCustomerBuilding.java*.

    - CustomerBuilding.java: Suppose to be the same structure as *InidividualCustomerBuilding.java*.

    - IndividualCustomerBuilding.java:
      - This is the main class that I have worked with the builder pattern. I created *Backend.java* instantiate in order to being able to access the method from backend architecture. Create the getter and setter methods for private variables and transfer the information into builder: *IndividualCustomerBuilding build()* function that be able to passed in database.

    - RentalLogic.java:
      - The facade class, create an instance that represents the architecture of rental_logic. Integrate all the function's instantiate together. Since we don't have time to finishing it completely but we create the structure as an instance that being suppose to become an accessible class to frontend to communicate with database.

## Facade Pattern:

  - backend:

    - Backend.java: Fundamental of the Logic layer that communicate with database:

      - Class description:

        1. public List<String> getAvailableMake();
            -  Filter Class, Step 1, Get all the Available Car's Manufacturer from the DataBase

        2. public List<String> getAvailableModelByMake(String make);
            -  Filter Class, Step 2, Then based on CAR's Make to get available models

        3. public List<String> getAvailableFuelByModel(String model);
            -  Based on CAR's Model to get available color, Finishing user choices. And Using the filter to access Database

        4. public List<String> getAvailableCarsVinByFilter(String make, String model);
            -  Based on User's selection, this method will be implemented by using filter to select available Car from Database. It will return a number of VINs of Available Cars that could be rental.

        5. public List<String> getAttributesOfCarsByVin(String vin);
            -  Based on the filter's returned VINs, it will find the attributes of Car's from database that using in rental_logic package.

            - **Those function are preparing with using in frontend, User is being able to select the make, and model. We will use it to search the available cars within the database and returns a list of Car's Vin (The unique Id of the car). Then we using the vin of car to get the attributes of car's from both CarEntity and ModelEntity(Including fuel type, fuel Economy, NumberOfDoors, ), printing on the SelectCarModel page of the application that being able to used.**

        6. 6 ~ 12: Insert new Car, Model, Individual, Corporate Customer into database. (Defualt created and included in backend package).

    - CustomerRental.java: Interface of DataBase of **"CustomerRentalEntity"**.

    - CustomerRentalEntity.java: Database of car rental, Table names: (CustomerRental). There are four columns there. **Primary Key: ID**, **Customer Account: account** , **Vin of Car's(Unique): vin**, **Individual Or Corporate Customer label: Customer_kind("1" represents Individual, "2" represents Corporate).** If a customer(Individual or Corporate), rent a car, his account number will be stored in Table, and pair with a special vin number of a car that he rented, and accompant with a label: **1 or 2**.

## Decorator Pattern:

  - frontend:

    ...
