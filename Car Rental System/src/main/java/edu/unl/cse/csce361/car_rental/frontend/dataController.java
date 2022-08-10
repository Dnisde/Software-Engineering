package edu.unl.cse.csce361.car_rental.frontend;

import edu.unl.cse.csce361.car_rental.backend.Backend;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;


import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

public class dataController implements Initializable {


    @FXML private SplitMenuButton carModel;
    @FXML private SplitMenuButton carClass;
    @FXML private SplitMenuButton numberOfDoors;
    @FXML private SplitMenuButton fuelEconomy;
    @FXML private SplitMenuButton fuelType;
    @FXML private SplitMenuButton seatCapacity;
    @FXML private SplitMenuButton preferColors;


    @FXML private TextField dailyRateTextField;
    @FXML private TextField taxesTextField;
    @FXML private TextField totalTextField;
    @FXML private TextField duePickupTextField;
    @FXML private TextField dueReturnTextField;

    @FXML private TextField returningName;
    @FXML private TextField fullName;
    @FXML private TextField address;
    @FXML private TextField address2;
    @FXML private TextField city;
    @FXML private TextField zip;
    @FXML private TextField corpAccount;
    @FXML private SplitMenuButton customerType;
    @FXML private Button login;


    public void setDailyRateTextField(){
        dailyRateTextField.setText("40");
    }
    public void setTaxesTextField(){
        taxesTextField.setText("40");
    }
    public void setTotalTextField(){
        taxesTextField.setText("40");
    }
    public void setDuePickupTextField(){
        duePickupTextField.setText("40");
    }
    public String getDuePickup(){
        return duePickupTextField.getText();
    }
    public void setDueReturnTextField(){
        dueReturnTextField.setText("40");
    }



    public void setCorporatePickupPrice(String pickupPrice){
        int pickupPriceInt = Integer.parseInt(pickupPrice);
        pickupPriceInt = (int)(pickupPriceInt * 0.9);
        pickupPrice = Integer.toString(pickupPriceInt);
        dailyRateTextField.setText(pickupPrice);
    }

    public void getReturnPrice(String returnPrice){ dailyRateTextField.setText(returnPrice); }

    /*
    public void getCarString(){
        ASK Dennis
    }

     */



    public String setCorporateCarString(String car1, String car2, String car3, String car4){
        String result ="";
        return result.concat(car1).concat("/n").concat(car2).concat("/n").concat(car3).concat("/n").concat(car4).concat("/n");
    }

    /**
         * All these methods below return the a filter value chosen by user.
         * @param carModelString
         */


    public void getCarModel(String carModelString){
        carModel.setText(carModelString);
    }
    public void getCarClass(String carClassString){ carClass.setText(carClassString); }
    public void getNumberOfDoors(String doorNumberString){ numberOfDoors.setText(doorNumberString); }
    public void getFuelEconomy(String economyString){ fuelEconomy.setText(economyString); }
    public void getFuelType(String fuelTypeString){ fuelType.setText(fuelTypeString); }
    public void getSeatCapacity(String seatCapacityString){ seatCapacity.setText(seatCapacityString); }
    public void getColor(String colorString){ preferColors.setText(colorString); }


    // Set Login Values

    public void setCustomerType(String customerTypeString){
        customerType.setText(customerTypeString);
    }
    public String getCustomerString(){
        customerType.setText("");
        return customerType.getText();
    }

    public String getReturningName() {
      String name = null;
      name = returningName.getText();
        return name;
    }
    public String getFullName() {
        fullName.setText("");
        return fullName.getText();
    }
    public String getAddress1() {
        address.setText("");
        return address.getText();
    }
    public String getCity() {
        city.setText("");
        return city.getText();
    }
    public String getZip() {
        zip.setText("");
        return zip.getText();
    }
    public String getCorpAccount() {
        corpAccount.setText("");
        return corpAccount.getText();
    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
