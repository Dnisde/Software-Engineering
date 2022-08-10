package edu.unl.cse.csce361.car_rental.frontend;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javafx.event.ActionEvent;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

public class Controller extends dataController implements Initializable {


	@FXML private static  String modelString;
	@FXML private static  String carClassString;
	@FXML private static  String colorString;
	@FXML private static  String doorsString;
	@FXML private static  String capacityString;
	@FXML private static  String fuelString;
	@FXML private static  String economyString;

	@FXML private Label middle;
	@FXML private SplitMenuButton customerType;
	@FXML private SplitMenuButton carClass;
	@FXML private SplitMenuButton numberOfDoors;
	@FXML private SplitMenuButton fuelEconomy;
	@FXML private SplitMenuButton fuelType;
	@FXML private SplitMenuButton seatCapacity;
	@FXML private SplitMenuButton preferColors;

	@FXML private TextField dailyRateTextField;
	@FXML private TextField taxesTextField;
	@FXML private TextField totalTextField;
	@FXML private static TextField duePickupTextField;
	@FXML private static TextField dueReturnTextField;

	//Login Page
	@FXML private static TextField returningName;
	@FXML private static TextField fullName;
	@FXML private static TextField address;
	@FXML private static TextField address2;
	@FXML private static TextField city;
	@FXML private static TextField zip;
	@FXML private static TextField corpAccount;
	@FXML private Button login;

	@FXML private String returningNameString;
	@FXML private String fullNameString;
	@FXML private String addressString;
	@FXML private String address2String;
	@FXML private String cityString;
	@FXML private String zipString;
	@FXML private String corpAccountString;
	@FXML private String customerTypeString;


// Corporate Variables
	@FXML private TextField corpTotalTextField;
	@FXML private TextField corpDuePickupTextField;
	@FXML private TextField corpDueReturnTextField;
	@FXML private TextField corporateNumber;
	@FXML private TextField corpCarSelection;

	protected HashMap<String, String> searchMap = new HashMap<String, String>();
	protected HashMap<Integer, String> filter = new HashMap<Integer, String>();
	
	final static Integer modelFilter = 1;
	final static Integer classFilter = 2;
	final static Integer doorsFilter = 3;
	final static Integer fuelEconFilter = 4;
	final static Integer typeFilter = 5;
	final static Integer seatFilter = 6;
	final static Integer colorFilter = 7;
	
	public void clickReset() {
		middle.setText("Welcome to the Husker Car Rental ");
	}
	
	final public void createMap() {
		for(int i = 0; i< customerType.getItems().size(); i++) {
			searchMap.put("Model", customerType.getItems().get(i).getText());
		}
		for(int i=0;i<carClass.getItems().size();i++) {
			searchMap.putIfAbsent("Class", carClass.getItems().get(i).getText());
		}
		for(int i=0;i<numberOfDoors.getItems().size();i++) {
			searchMap.putIfAbsent("Doors", numberOfDoors.getItems().get(i).getText());
		}
		for(int i=0;i<fuelEconomy.getItems().size();i++) {
			searchMap.putIfAbsent("Economy", fuelEconomy.getItems().get(i).getText());
		}
		for(int i=0;i<fuelType.getItems().size();i++) {
			searchMap.putIfAbsent("fuelType", fuelType.getItems().get(i).getText());
		}
		for(int i=0;i<seatCapacity.getItems().size();i++) {
			searchMap.putIfAbsent("seat", seatCapacity.getItems().get(i).getText());
		}
		for(int i=0;i<preferColors.getItems().size();i++) {
			searchMap.putIfAbsent("seat", preferColors.getItems().get(i).getText());
		}
		
	}

	public void clickLoginMenu(ActionEvent event) {

		customerType.setOnAction(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub'

				System.out.println("Customer Selector button clicked");
			}

		});

		for(int i = 0; i< customerType.getItems().size(); i++) {



			String customerItem = customerType.getItems().get(i).getText();


			customerType.getItems().get(i).setOnAction(new EventHandler<ActionEvent>(){
				@Override
				public void handle(ActionEvent event) {
					// TODO Auto-generated method stub
					customerType.setText(customerItem);

					System.out.println("User Select Type: " + customerItem);
//					middle.setText("Select Customer - " + customerItem);
					customerTypeString = customerItem;
					System.out.println(customerItem);
				}


			});

		}


	}

	public void login(ActionEvent event) throws IOException {

		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("home.fxml"));
		Parent homeParent = loader.load();
		dataController controller = loader.getController();

//		returningNameString = controller.getReturningName();
//		fullNameString = controller.getFullName();
//		addressString = controller.getAddress1();
//		cityString = controller.getCity();
//		zipString = controller.getZip();
//		corpAccountString = controller.getCorpAccount();
//		customerTypeString = controller.getCustomerString();

//		System.out.println(returningNameString);


		Scene scene = new Scene(homeParent);
		Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
		window.setScene(scene);
		window.show();

	}

	public void returnCarScreen(ActionEvent event) throws IOException {

		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("return.fxml"));
		Parent homeParent = loader.load();
		dataController controller = loader.getController();

//		returningNameString = controller.getReturningName();
//		fullNameString = controller.getFullName();
//		addressString = controller.getAddress1();
//		cityString = controller.getCity();
//		zipString = controller.getZip();
//		corpAccountString = controller.getCorpAccount();
//		customerTypeString = controller.getCustomerString();

//		System.out.println(returningNameString);


		Scene scene = new Scene(homeParent);
		Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
		window.setScene(scene);
		window.show();

	}

	public void cancelCheckoutReturn(ActionEvent event) throws IOException {

		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("home.fxml"));
		Parent homeParent = loader.load();
		dataController controller = loader.getController();


		Scene scene = new Scene(homeParent);
		Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
		window.setScene(scene);
		window.show();

	}

	public void clickModel() {
		
		customerType.setOnAction(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub'

				System.out.println("Model button clicked");
			}
			
		});
		
		for(int i = 0; i< customerType.getItems().size(); i++) {


			
			String modelItem = customerType.getItems().get(i).getText();


			customerType.getItems().get(i).setOnAction(new EventHandler<ActionEvent>(){
				@Override
				public void handle(ActionEvent event) {
					// TODO Auto-generated method stub
					customerType.setText(modelItem);
					filter.put(modelFilter, modelItem);
					System.out.println("User Select Model: " + modelItem);
					middle.setText("Select Model - " + modelItem);
					modelString = modelItem;
					System.out.println(modelItem);
				}


			});

		}

		
	}  
	
	
	public void clickClass() {
		
		carClass.setOnAction(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				System.out.println("Class button clicked");
			}
			
		});
		
		for(int i=0;i<carClass.getItems().size();i++) {



			String item = carClass.getItems().get(i).getText();

			carClass.getItems().get(i).setOnAction(new EventHandler<ActionEvent>(){
				@Override
				public void handle(ActionEvent event) {
					// TODO Auto-generated method stub
					carClass.setText(item);
					filter.put(classFilter, item);
					carClassString = item;
					System.out.println("User Select class: " + item);
					middle.setText("Select the -- " + item + " type");

				}
			   
			});


		}
		
	}
	
	public void clickNumberOfDoors() {
		
		/* For Debug */
		numberOfDoors.setOnAction(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				System.out.println("Doors Selection button clicked");
			}
			
		});
		
		for(int i=0;i<numberOfDoors.getItems().size();i++) {



			String doorsNumber = numberOfDoors.getItems().get(i).getText();
			numberOfDoors.getItems().get(i).setOnAction(new EventHandler<ActionEvent>(){
				@Override
				public void handle(ActionEvent event) {
					// TODO Auto-generated method stub
					numberOfDoors.setText(doorsNumber);
					filter.put(doorsFilter, doorsNumber);
					doorsString = doorsNumber;
					System.out.println("Select Number of doors: " + doorsNumber);
					middle.setText("Select type of " + doorsNumber + " cars");
				}
			   
			});


		}
	}
	
	
	public void clickFuelEconomy() {
		
		fuelEconomy.setOnAction(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				System.out.println("Fule Economy Button was clicked");
			}
			
		});
		
		for(int i=0;i<fuelEconomy.getItems().size();i++) {
			
			String economyType = fuelEconomy.getItems().get(i).getText();
			fuelEconomy.getItems().get(i).setOnAction(new EventHandler<ActionEvent>(){
				@Override
				public void handle(ActionEvent event) {
					// TODO Auto-generated method stub
					fuelEconomy.setText(economyType);
					filter.put(fuelEconFilter, economyType);
					economyString = economyType;
					System.out.println("Select fuel Economy type: " + economyType);
					middle.setText("Select economy of " + economyType + "cars");

				}
			   
			});
			

		}
	}
	
	public void clickFuelType() {
		fuelType.setOnAction(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				System.out.println("Fule Type Button was clicked");
			}
			
		});
		
		for(int i=0;i<fuelType.getItems().size();i++) {
			
			String fueltype = fuelType.getItems().get(i).getText();
			fuelType.getItems().get(i).setOnAction(new EventHandler<ActionEvent>(){
				@Override
				public void handle(ActionEvent event) {
					// TODO Auto-generated method stub
					fuelType.setText(fueltype);
					filter.put(typeFilter, fueltype);
					fuelString = fueltype;
					System.out.println("Select type of fuel: " + fueltype);
					middle.setText("Select type of fuel : " + fueltype);

				}
			   
			});



		}
	}
	
	public void clickSeatCapacity() {
		
		seatCapacity.setOnAction(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				System.out.println("Seat Selection button was clicked");
			}
			
		});
		
		for(int i=0;i<seatCapacity.getItems().size();i++) {
			
			String seatType = seatCapacity.getItems().get(i).getText();
			seatCapacity.getItems().get(i).setOnAction(new EventHandler<ActionEvent>(){
				@Override
				public void handle(ActionEvent event) {
					// TODO Auto-generated method stub
					seatCapacity.setText(seatType);
					filter.put(seatFilter, seatType);
					capacityString = seatType;
					System.out.println("Select number of seats: " + seatType);
					middle.setText("Select number of seats :  " + seatType);

				}
			   
			});

		}
	}
	
	public void clickColorPreference() {
		preferColors.setOnAction(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				System.out.println("Select preference of color button was clicked");
			}
			
		});
		
		for(int i=0;i<preferColors.getItems().size();i++) {
			
			String color = preferColors.getItems().get(i).getText();
			preferColors.getItems().get(i).setOnAction(new EventHandler<ActionEvent>(){
				@Override
				public void handle(ActionEvent event) {
					// TODO Auto-generated method stub
					preferColors.setText(color);
					filter.put(colorFilter, color);
					colorString = color;
					System.out.println("Select the preference color: " + color);
					middle.setText("Preference Color --- " + color);

				}
			   
			});

		}
	}
	
	public void reset() {
		
		customerType.setText("Model");
		carClass.setText("Class");
		preferColors.setText("Prefered Color");
		numberOfDoors.setText("Number of doors");
		fuelEconomy.setText("Fuel Economy");
		seatCapacity.setText("Seating Capacity");
		fuelType.setText("Fuel type");
		middle.setText("Alright, you reset all the selection...");
		filter.clear();

	}
	
	public HashMap<Integer, String> getFilter() {
		return this.filter;
	}
	
	public Stage switchPageButtonPushedSelectModel(ActionEvent event) throws IOException {
		/* Loading the secondary scene of Select Car */
		/* For Debug */
		System.out.println("Attributes: ");
		System.out.println(filter);
		
		FXMLLoader loaderSelectModel = new FXMLLoader();
		loaderSelectModel.setLocation(getClass().getResource("SelectModel.fxml"));
		Parent child = loaderSelectModel.load();

		Scene scene = new Scene(child);
		Stage secondaryStage = (Stage)((Node)event.getSource()).getScene().getWindow();

		secondaryStage.setTitle("Husker Klunker Car Rentals");
		
		secondaryStage.setScene(scene);
		secondaryStage.show();
		
//		SelectModelController controller = loaderSelectModel.<SelectModelController>getController();
//		controller.initData(customer);
		return secondaryStage;
	}
	
	public void switchPageButtonPushedCheckoutModel(javafx.scene.input.MouseEvent event) throws IOException{

		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("CheckoutModel.fxml"));
		Parent homeParent = loader.load();
		dataController priceCdontroller = loader.getController();
		priceCdontroller.setDuePickupTextField();
		priceCdontroller.setCorporatePickupPrice(priceCdontroller.getDuePickup());


		Scene scene = new Scene(homeParent);
		Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
		window.setScene(scene);
		window.show();
	}

	public void switchPageButtonHomeModel(ActionEvent event)throws IOException{
		FXMLLoader loaderSelectModel = new FXMLLoader();
		loaderSelectModel.setLocation(getClass().getResource("home.fxml"));
		Parent parent = loaderSelectModel.load();

		Scene scene = new Scene(parent);
		Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

		window.setScene(scene);
		window.show();
	}

	public void switchPageButtonThankYouModel(ActionEvent event)throws  IOException{
		FXMLLoader loaderSelectModel = new FXMLLoader();
		loaderSelectModel.setLocation(getClass().getResource("ThankYou.fxml"));
		Parent parent = loaderSelectModel.load();

		Scene scene = new Scene(parent);
		Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

		window.setScene(scene);
		window.show();
	}
	/**
	 * This method returns user to homepage with previous filter set
	 * @param event
	 * @throws IOException
	 */
	public void cancelCheckout(ActionEvent event) throws IOException {

		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("home.fxml"));
		Parent homeParent = loader.load();
		dataController controller = loader.getController();
		controller.getCarModel(modelString);
		controller.getCarClass(carClassString);
		controller.getFuelEconomy(economyString);
		controller.getFuelType(fuelString);
		controller.getNumberOfDoors(doorsString);
		controller.getSeatCapacity(capacityString);
		controller.getColor(colorString);

		Scene scene = new Scene(homeParent);
		Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
		window.setScene(scene);
		window.show();

	}


	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {

	}
}
