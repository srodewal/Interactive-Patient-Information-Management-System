package com.ipims.views;


import com.ipims.usersession.LoginViewController;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;


public class LoginView extends BaseView {

	public void createLoginView(LoginViewController parentController) {

		VBox vbox = new VBox();
	
	
		vbox.setAlignment(Pos.CENTER);
		
		Text appTitle = new Text("IPIMS");
		appTitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 50));
		vbox.getChildren().add(appTitle);

		Text scenetitle = new Text("Interactive Patient Information Management System");		
		scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
		vbox.getChildren().add(scenetitle);

		GridPane grid = new GridPane();
		grid.setAlignment(Pos.CENTER);
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(50, 25, 25, 25));
		
		Label userName = new Label("User Name:");
		grid.add(userName, 0, 1);

		TextField userTextField = new TextField();
		grid.add(userTextField, 1, 1);

		Label pw = new Label("Password:");
		grid.add(pw, 0, 2);

		PasswordField pwBox = new PasswordField();
		grid.add(pwBox, 1, 2);
		
		vbox.getChildren().add(grid);
		
		HBox hbBtn = new HBox(10);
		hbBtn.setAlignment(Pos.CENTER);
		
		// Create Login Button
		Button loginBtn = new Button("Login");
		loginBtn.setPrefSize(100, 10);
		hbBtn.getChildren().add(loginBtn);
		

		// Create New User Button
		Button btn2 = new Button("New User");
		btn2.setPrefSize(100, 10);
		hbBtn.getChildren().add(btn2);
		vbox.getChildren().add(hbBtn);

		//If login button is pressed
		loginBtn.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {

				System.out.println(userTextField.getText()); // for testing
				System.out.println(pwBox.getText()); // for testing
				parentController.handleLoginButtonClick(userTextField.getText(), pwBox.getText());

			}
		});

		//If New User button is pressed
		btn2.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {

				parentController.handleNewUserButtonClick();

			}
		});

		createScene(vbox);
		


	}
}
