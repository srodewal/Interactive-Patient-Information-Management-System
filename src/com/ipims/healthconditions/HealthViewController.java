package com.ipims.healthconditions;

import com.ipims.MenuViewController;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class HealthViewController {

private Scene ScheduleScene;
	
	public Scene getScene() {
		GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        Text scenetitle = new Text("Update Healthcare Condition");
        scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        grid.add(scenetitle, 0, 0, 2, 1);

        Label userName = new Label("Date:");
        grid.add(userName, 0, 1);

        TextField userTextField = new TextField();
        grid.add(userTextField, 1, 1);

        Label pw = new Label("Time:");
        grid.add(pw, 0, 2);

        PasswordField pwBox = new PasswordField();
        grid.add(pwBox, 1, 2);

        Button submitBtn = new Button("Submit");
        HBox submitBox = new HBox(10);
        submitBtn.setAlignment(Pos.BOTTOM_RIGHT);
        submitBox.getChildren().add(submitBtn);
        grid.add(submitBtn, 1, 4);
        
        Button backBtn = new Button("Back");
        HBox backBox = new HBox(10);
        backBtn.setAlignment(Pos.BOTTOM_RIGHT);
        backBox.getChildren().add(backBtn);
        grid.add(backBtn, 1, 5);

        final Text actiontarget = new Text();
        grid.add(actiontarget, 1, 6);

        submitBtn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e) {
                actiontarget.setFill(Color.GREEN);
               actiontarget.setText("Condition recorded.");
            	
            	
            //	MenuViewController menu = new MenuViewController();
            //	Stage stage = (Stage) ScheduleScene.getWindow();
            //	stage.setScene(menu.getScene());
            	
            }
        });
        
        backBtn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e) {
                actiontarget.setFill(Color.GREEN);
                actiontarget.setText("Back Pressed!"); 
            	
            	
                MenuViewController menu = new MenuViewController();
                Stage stage = (Stage) ScheduleScene.getWindow();
                stage.setScene(menu.getScene());
            	
            }
        });
        ScheduleScene = new Scene(grid, 300, 275);
		return ScheduleScene;
	}
	
}

