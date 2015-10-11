package com.ipims.healthconditions;

import java.awt.TextArea;

import com.ipims.MenuViewController;
import com.ipims.usersession.UserSession;
import com.ipims.views.HealthView;

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

private HealthView view;
	
	public HealthViewController() {
		view = new HealthView();
		view.createHealthview(UserSession.getInstance().getCurrentUser(), this);
	}
	public Scene getScene() {
		return view.getCurrentScene();
	}
	
	
     
    
        
	
}

