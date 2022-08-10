package edu.unl.cse.csce361.car_rental.frontend;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class priceController implements Initializable {

    @FXML private TextField dailyRateTextField;
    @FXML private TextField taxesTextField;
    @FXML private TextField totalTextField;
    @FXML private TextField duePickupTextField;
    @FXML private TextField dueReturnTextField;


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


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}

