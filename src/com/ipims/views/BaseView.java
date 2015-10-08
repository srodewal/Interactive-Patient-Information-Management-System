package com.ipims.views;

import javafx.scene.Scene;
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
	
}
