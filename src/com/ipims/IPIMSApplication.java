package com.ipims;

import com.ipims.database.DatabaseManager;
import com.ipims.models.Doctor;
import com.ipims.models.HSPStaff;
import com.ipims.models.LabStaff;
import com.ipims.models.Nurse;
import com.ipims.models.Patient;
import com.ipims.usersession.LoginViewController;
import com.ipims.usersession.UserSession;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Controller class that is load when the application is launched.
 * 
 * @author jithin
 *
 */
public class IPIMSApplication extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) {

		preFillDatabase();
		if (UserSession.getInstance().getCurrentUser() == null) {
			// If user is not logged in show login view

			LoginViewController vc = new LoginViewController();
			primaryStage.setScene(vc.getScene());
			primaryStage.show();
		} 

	}

	/**
	 * Prefill database with dummy data.
	 */
	private void preFillDatabase() {
		preFillDoctor();
		preFillNurse();
		preFillHSPStaff();
		preFillPatient();
		preFillLabStaff();

	}

	private void preFillLabStaff() {
		LabStaff user = new LabStaff();
		user.setName("Lab Guy");
		user.setUserName("lab");
		user.setAddress("asdad");
		user.setDateOfBirth("12/2/1978");
		user.setSsn("2324234");
		user.setPhoneNumber("34435345");
		user.setEmail("Asda");
		user.setInsurance("insurance");
		user.setSex("Male");
		user.setRace("Other");
		if (DatabaseManager.getInstance().getUser("lab", "test") == null) {
			DatabaseManager.getInstance().newUser(user, "test");
		}
	}
	
	private void preFillDoctor() {

		Doctor user = new Doctor();
		user.setName("Dr. Jones");
		user.setUserName("johnc");
		user.setAddress("asdad");
		user.setDateOfBirth("12/2/1978");
		user.setSsn("2324234");
		user.setPhoneNumber("34435345");
		user.setEmail("Asda");
		user.setInsurance("insurance");
		user.setSex("Male");
		user.setRace("Other");

		if (DatabaseManager.getInstance().getUser("johnc", "test") == null) {
			user.setCategory(Helper.getAllCategories().get(1));
			DatabaseManager.getInstance().newUser(user, "test");
		}

		if (DatabaseManager.getInstance().getUser("adam", "test") == null) {
			user.setName("Dr. Adams");
			user.setCategory(Helper.getAllCategories().get(0));
			user.setUserName("adam");
			DatabaseManager.getInstance().newUser(user, "test");
		}

		if (DatabaseManager.getInstance().getUser("will", "test") == null) {
			user.setName("Dr. Williams");
			user.setUserName("will");
			user.setCategory(Helper.getAllCategories().get(3));
			DatabaseManager.getInstance().newUser(user, "test");
		}		

		if (DatabaseManager.getInstance().getUser("smith") == null) {
			user.setName("Dr. Smith");
			user.setUserName("smith");
			user.setCategory(Helper.getAllCategories().get(2));
			DatabaseManager.getInstance().newUser(user, "test");
		}
	}

	private void preFillNurse() {

		Nurse user = new Nurse();
		user.setName("Alice");
		user.setUserName("alice");
		user.setAddress("asdad");
		user.setDateOfBirth("12/2/1978");
		user.setSsn("2324234");
		user.setPhoneNumber("34435345");
		user.setEmail("Asda");
		user.setInsurance("insurance");
		user.setSex("Male");
		user.setRace("Other");
		if (DatabaseManager.getInstance().getUser("alice", "test") == null) {
			DatabaseManager.getInstance().newUser(user, "test");
		}

		if (DatabaseManager.getInstance().getUser("nurse2", "test") == null) {
			user.setName("nurse2");
			user.setUserName("nurse2");
			DatabaseManager.getInstance().newUser(user, "test");
		}

	}

	private void preFillHSPStaff() {
		HSPStaff user = new HSPStaff();
		user.setName("HSP");
		user.setUserName("hsp1");
		user.setAddress("asdad");
		user.setDateOfBirth("12/2/1978");
		user.setSsn("2324234");
		user.setPhoneNumber("34435345");
		user.setEmail("Asda");
		user.setInsurance("insurance");
		user.setSex("Male");
		user.setRace("Other");
		if (DatabaseManager.getInstance().getUser("hsp1", "test") == null) {
			DatabaseManager.getInstance().newUser(user, "test");
		}

		if (DatabaseManager.getInstance().getUser("hsp2", "test") == null) {
			user.setName("hsp2");
			user.setUserName("hsp2");
			DatabaseManager.getInstance().newUser(user, "test");
		}
	}
	
	private void preFillPatient() {
		Patient user = new Patient();
		user.setName("Jithin");
		user.setUserName("j");
		user.setAddress("asdad");
		user.setDateOfBirth("12/2/1978");
		user.setSsn("2324234");
		user.setPhoneNumber("34435345");
		user.setEmail("Asda");
		user.setInsurance("insurance");
		user.setSex("Male");
		user.setRace("Other");
		if (DatabaseManager.getInstance().getUser("j", "j") == null) {
			DatabaseManager.getInstance().newUser(user, "j");
		}
	}
}
