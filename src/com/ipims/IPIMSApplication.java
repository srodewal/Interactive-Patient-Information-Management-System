package com.ipims;

import com.ipims.database.DatabaseManager;
import com.ipims.models.Doctor;
import com.ipims.models.HSPStaff;
import com.ipims.models.Nurse;
import com.ipims.usersession.LoginViewController;
import com.ipims.usersession.UserSession;

import javafx.application.Application;
import javafx.stage.Stage;

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

		} else {
			// User is logged in, so show the menu
		}

	}

	private void preFillDatabase() {
		preFillDoctor();
		preFillNurse();
		preFillCategory();
		preFillHSPStaff();

	}

	private void preFillDoctor() {

		Doctor user = new Doctor();
		user.setName("John XX");
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
			DatabaseManager.getInstance().newUser(user, "test");
		}

		if (DatabaseManager.getInstance().getUser("tom", "test") == null) {
			user.setName("Tom T");
			user.setUserName("tom");
			DatabaseManager.getInstance().newUser(user, "test");
		}

		if (DatabaseManager.getInstance().getUser("will", "test") == null) {
			user.setName("Will");
			user.setUserName("will");
			DatabaseManager.getInstance().newUser(user, "test");
		}

		if (DatabaseManager.getInstance().getUser("smith", "test") == null) {
			user.setName("Smith");
			user.setUserName("smith");
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

	private void preFillCategory() {

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
}
