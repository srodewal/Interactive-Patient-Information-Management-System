package com.ipims.usersession;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class RegistrationViewController {

	Stage currentStage;
	Scene scene;
	
	// for sending registration info to database
	private String firstNameToDatabase;
	private String lastNameToDatabase;
    private String addressToDatabase;
    private String ssnNumberToDatabase;
    private String insuranceProviderToDatabase;
    private String emailToDatabase;
    private String phoneNumberToDatabase;
    private String dateOfBirthToDatabase; 
    
    public String getFirstName() {
    	return firstNameToDatabase;
    }
    
    public String getLastName() {
    	return lastNameToDatabase;
    }
    
    public String getAddress() {
    	return addressToDatabase;
    }
    
    public String getSSN() {
    	return ssnNumberToDatabase;
    }
    
    public String getInsuranceProvider() {
    	return insuranceProviderToDatabase;
    }
    
    public String getEmail() {
    	return emailToDatabase;
    }
    
    public String getPhoneNumber() {
    	return phoneNumberToDatabase;
    }
    
    public String getDOB() {
    	return dateOfBirthToDatabase;
    }

    public Scene getScene() {
    	

        GridPane registerPane = new GridPane();
        registerPane.setAlignment(Pos.CENTER);
        registerPane.setHgap(10);
        registerPane.setVgap(10);
        registerPane.setPadding(new Insets(25, 25, 25, 25));
        
        Text registerText = new Text("Register");
        registerText.setFont(Font.font("Verdana", FontWeight.NORMAL, 20));
        registerPane.add(registerText, 0, 0, 2, 1);
        
        Scene registerScene = new Scene(registerPane, 600, 550);

        Label firstName = new Label("First Name:");
        registerPane.add(firstName, 0, 1);
        TextField firstNameTextField = new TextField();
        registerPane.add(firstNameTextField, 1, 1);

        Label lastName = new Label("Last Name:");
        registerPane.add(lastName, 0, 2);
        TextField lastNameTextField = new TextField();
        registerPane.add(lastNameTextField, 1, 2);
        
        Label dateOfBirth = new Label("Date of Birth:");
        registerPane.add(dateOfBirth, 0, 3);
        TextField dateOfBirthTextField = new TextField();
        registerPane.add(dateOfBirthTextField, 1, 3);
        
        Label currentAddress = new Label("Current Address:");
        registerPane.add(currentAddress, 0, 4);
        TextField currentAddressTextField = new TextField();
        registerPane.add(currentAddressTextField, 1, 4);
        
        Label ssnNumber = new Label("Social Security Number:");
        registerPane.add(ssnNumber, 0, 5);
        TextField ssnNumberTextField = new TextField();
        registerPane.add(ssnNumberTextField, 1, 5);

        Label phoneNumber = new Label("Phone Number:");
        registerPane.add(phoneNumber, 0, 6);
        TextField phoneNumberTextField = new TextField();
        registerPane.add(phoneNumberTextField, 1, 6);
        
        Label email = new Label("Email:");
        registerPane.add(email, 0, 7);
        TextField emailTextField = new TextField();
        registerPane.add(emailTextField, 1, 7);

        Label healthInsuranceProvider = new Label("Health Insurance Provider:");
        registerPane.add(healthInsuranceProvider, 0, 8);
        TextField healthInsuranceTextField = new TextField();
        registerPane.add(healthInsuranceTextField, 1, 8);
        
        //Label pw = new Label("Password:");
        //registerPane.add(pw, 0, 2);

        //PasswordField pwBox = new PasswordField();
        //registerPane.add(pwBox, 1, 2);

        Button btn = new Button("Submit");
        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn.getChildren().add(btn);
        registerPane.add(hbBtn, 1, 9);

        Button btn2 = new Button("Back");
        HBox hbBtn2 = new HBox(10);
        hbBtn2.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn2.getChildren().add(btn2);
        registerPane.add(hbBtn2, 1, 10);
        
        
        final Text actiontarget = new Text();
        registerPane.add(actiontarget, 1, 10);
        
        final Text firstNameTarget = new Text();
        registerPane.add(firstNameTarget, 1, 11);

        btn.setOnAction(new EventHandler<ActionEvent>() {

        	
        	
        	
            @Override
            public void handle(ActionEvent e) {
                actiontarget.setFill(Color.RED);
                actiontarget.setText("Information submitted");
                
                firstNameToDatabase = firstNameTextField.getText();
                lastNameToDatabase = lastNameTextField.getText();
                addressToDatabase = currentAddressTextField.getText();
                ssnNumberToDatabase = ssnNumberTextField.getText();
                insuranceProviderToDatabase = healthInsuranceTextField.getText();
                emailToDatabase = emailTextField.getText();
                phoneNumberToDatabase = phoneNumberTextField.getText();
                dateOfBirthToDatabase = dateOfBirthTextField.getText(); 
                
                // create new user Object
                // User newUser = new User();
                // newUser.setFirstName(firstNameToDatabase);
                // .....
                // UserSession.getInstance().login(newUser);
            
                
                firstNameTarget.setFill(Color.RED);
                firstNameTarget.setText(firstNameToDatabase);

            }
        });
        
        
        btn2.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e) {
            	
            	
            	LoginViewController LoginPage = new LoginViewController();
            	
            	Stage stage = (Stage) registerScene.getWindow();
            	
            	stage.setScene(LoginPage.getScene());
            	
            }
        });
             
     
    
    return registerScene;
    }

}
