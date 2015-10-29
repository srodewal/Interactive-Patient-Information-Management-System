package com.ipims.views;






import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * Base view to be inherited by all view classes.
 * Call createScene method at the end that will create scene with a predefined width and height
 * 
 * @author jithin
 *
 */
public class BaseView {

	protected Scene currentScene;
	
	private static final int WIDTH = 600;
	private static final int HEIGHT = 700;
	
	public Scene getCurrentScene() {
		return currentScene;
	}
	
	public Stage getStage() {
		if (currentScene != null) {
			return (Stage)currentScene.getWindow();
		}
		return null;
	}
	
	/**
	 * Shows an alert with the message. 
	 * 
	 * @param message Message to be displayed.
	 */
	public void showErrorMessage(String message) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Error");
		alert.setHeaderText(null);
		alert.setContentText(message);

		alert.showAndWait();
	}
	
	protected void createScene(Parent root) {
		currentScene = new Scene(root, WIDTH, HEIGHT);

	}
}
