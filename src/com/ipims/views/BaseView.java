package com.ipims.views;






import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class BaseView {

	protected Scene currentScene;
	

	
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
	
	protected void createScene(Parent root) {
		currentScene = new Scene(root, 500, 700);
	}
}
