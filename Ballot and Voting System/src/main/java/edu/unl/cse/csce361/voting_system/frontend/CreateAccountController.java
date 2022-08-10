package edu.unl.cse.csce361.voting_system.frontend;

import java.io.IOException;
import java.time.LocalDate;
import java.time.Period;
import edu.unl.cse.csce361.voting_system.voting_logic.CreateAccount;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

public class CreateAccountController {
	
	@FXML
	private Button createElectionOfficialButton;
	@FXML
	private Button createAuditorButton;
	@FXML
	private Button createVoterButton;
	@FXML
	private TextField firstName;
	@FXML
	private TextField lastName;
	@FXML
	private TextField ssn;
	@FXML
	private TextField streetAddress1;
	@FXML
	private TextField streetAddress2;
	@FXML
	private TextField city;
	@FXML
	private TextField state;
	@FXML
	private TextField zipCode;
	@FXML
	private DatePicker dateOfBirth;
	
	
	/*  
	 * Register as an Election Official(Auditor Include) in the voting system
	 * 
	 * @Ability: Election Official is able to create a brand new ballot by using the voting system.
	 * Also, Election Official could check the current races and proposition's status; For instance, 
	 * 		1. how many voters has voted within the system, and how did they voted.
	 * 		2. Search for a certain user's voting status, check if they voted or not.
	 * 	
	 * @Suggestion & Warning:  -- ! 
	 * Once an Election Official trying to create a new ballot by press the "Submit" Button,
	 * the old ballot from the latest one will be dropped and deleted.
	 * Because the system will treat the  old ballot competition has finished as default.
	 */
	
	public void pressRegisterElectionOfficial(ActionEvent event) throws IOException {
		userName = firstName.getText() + " " + lastName.getText();
		userSsn = ssn.getText();
		if(userName == null || userSsn == null) {
			System.out.println("Please enter all fields to create an election official");
		}
		else {
			CreateAccount createElectionOfficial = new CreateAccount();
			createElectionOfficial.createElectionOfficial(userSsn, userName);
			Controller con = new Controller();
			con.changeStages("home", event);
		}
	}

	/*  
	 * Register as an voter for the voting system:
	 * 
	 * @Ability: The voter could vote for current candidates of different races, 
	 * and also for propositions of the ballot which is available.
	 * 
	 * @Restriction: 
	 * 1.  A voter could be only able to choose a certain candidate for a single race, 
	 * and a certain proposition answer for a ballot, and can only vote one time.
	 * 
	 * 2.  Once submitted, the user could not being able to modify their decision, however, 
	 * they could review their choices when they trying to login in for the second time.
	 */
	
	public void pressRegisterVoter(ActionEvent event) throws IOException {
		userName = firstName.getText() + " " + lastName.getText();
		userSsn = ssn.getText();
		userAddress1 = streetAddress1.getText();
		userAddress2 = streetAddress2.getText();
		userCity = city.getText();
		userState = state.getText();
		userZip = zipCode.getText();
		userDateOfBirth = dateOfBirth.getValue().toString();
		int birthYear = dateOfBirth.getValue().getYear();
		int birthMonth = dateOfBirth.getValue().getMonth().getValue();
		int birthDay = dateOfBirth.getValue().getDayOfMonth();
		if(checkAge(birthYear, birthMonth, birthDay)) {
			if(userName == null || userSsn == null || userAddress1 == null ||
					userAddress2 == null || userCity == null || userState == null ||
					userZip == null || userDateOfBirth == null) {
				System.out.println("Please enter all fields to register a voter");
			}
			else {
				CreateAccount createVoter = new CreateAccount();
				createVoter.createVoter(userSsn, userName, userAddress1, userAddress2, userCity, userState, userZip, userDateOfBirth);
				Controller con = new Controller();
				con.changeStages("home", event);
			}
		} else {
			System.out.println("You are not of legal age to vote");
			Controller con = new Controller();
			con.changeStages("home", event);
		}
	}

	public boolean checkAge(int year, int month, int day) {
		LocalDate birthday = LocalDate.of(year, month, day);
		Period period = Period.between(birthday, LocalDate.now());
		if(period.getYears() >= 18) {
			return true;
		}
		return false;
	}

	private String userName, userSsn, userAddress1, userAddress2,
		userCity, userState, userZip, userDateOfBirth;
}
