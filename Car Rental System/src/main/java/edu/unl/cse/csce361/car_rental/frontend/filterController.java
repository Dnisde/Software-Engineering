package edu.unl.cse.csce361.car_rental.frontend;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;


import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class filterController implements Initializable {


    @FXML private SplitMenuButton carModel;
    @FXML private SplitMenuButton carClass;
    @FXML private SplitMenuButton numberOfDoors;
    @FXML private SplitMenuButton fuelEconomy;
    @FXML private SplitMenuButton fuelType;
    @FXML private SplitMenuButton seatCapacity;
    @FXML private SplitMenuButton preferColors;

    /**
     * All these methods below return the a filter value chosen by user.
     * @param carModelString
     */
    public void getCarModel(String carModelString){
        carModel.setText(carModelString);
    }
    public void getCarClass(String carClassString){
        carClass.setText(carClassString);
    }
    public void getNumberOfDoors(String doorNumberString){
        numberOfDoors.setText(doorNumberString);
    }
    public void getFuelEconomy(String economyString){
        fuelEconomy.setText(economyString);
    }
    public void getFuelType(String fuelTypeString){
        fuelType.setText(fuelTypeString);
    }
    public void getSeatCapacity(String seatCapacityString){
        seatCapacity.setText(seatCapacityString);
    }
    public void getColor(String colorString){ preferColors.setText(colorString); }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}

