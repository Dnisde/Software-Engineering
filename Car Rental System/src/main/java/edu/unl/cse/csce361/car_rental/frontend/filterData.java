package edu.unl.cse.csce361.car_rental.frontend;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;

import java.awt.event.ActionEvent;
import java.net.URL;
import java.util.ResourceBundle;

public class filterData implements Initializable {

    @FXML private ChoiceBox<String> model = new ChoiceBox<>();
    @FXML private ChoiceBox<String> carClass = new ChoiceBox<>();
    @FXML private ChoiceBox<String> color = new ChoiceBox<>();
    @FXML private ChoiceBox<String> door = new ChoiceBox<>();
    @FXML private ChoiceBox<String> capacity = new ChoiceBox<>();
    @FXML private ChoiceBox<String> fuel = new ChoiceBox<>();
    @FXML private ChoiceBox<String> economy = new ChoiceBox<>();

    /**
     * Stores choicebox value from JavaFX UI
     * @param choiceBox
     */
    public void getModelFilter( ChoiceBox choiceBox){
        model = (ChoiceBox) choiceBox.getValue();
    }
    /**
     * Stores choicebox value from JavaFX UI
     * @param choiceBox
     */
    public void getCarClassFilter( ChoiceBox choiceBox){
        carClass = (ChoiceBox) choiceBox.getValue();
    }
    /**
     * Stores choicebox value from JavaFX UI
     * @param choiceBox
     */
    public void getColorFilter( ChoiceBox choiceBox){
        color = (ChoiceBox) choiceBox.getValue();
    }
    /**
     * Stores choicebox value from JavaFX UI
     * @param choiceBox
     */
    public void getDoorFilter( ChoiceBox choiceBox){
        door = (ChoiceBox) choiceBox.getValue();
    }
    /**
     * Stores choicebox value from JavaFX UI
     * @param choiceBox
     */
    public void getCapacityFilter( ChoiceBox choiceBox){
        capacity = (ChoiceBox) choiceBox.getValue();
    }
    /**
     * Stores choicebox value from JavaFX UI
     * @param choiceBox
     */
    public void getFuelFilter( ChoiceBox choiceBox){
        fuel = (ChoiceBox) choiceBox.getValue();
    }
    /**
     * Stores choicebox value from JavaFX UI
     * @param choiceBox
     */
    public void getEconomyFilter( ChoiceBox choiceBox){
        economy = (ChoiceBox) choiceBox.getValue();
    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}

