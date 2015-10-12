package com.ipims.views;

import java.util.ArrayList;
import java.util.*;

import com.ipims.models.User;

import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class BaseView {

	protected Scene currentScene;
	
	// added
	//public ArrayList<User> validUsers = new ArrayList<User>();
	static protected Map<String, String> validUsers = new HashMap<String, String>();
	// added
	
	public Scene getCurrentScene() {
		return currentScene;
	}
	
	public Stage getStage() {
		if (currentScene != null) {
			return (Stage)currentScene.getWindow();
		}
		return null;
	}
	
	public void showErrorMessage(String message) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Error");
		alert.setHeaderText(null);
		alert.setContentText(message);

		alert.showAndWait();
	}
	
}
