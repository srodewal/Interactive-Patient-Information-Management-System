package com.ipims.hsp;

import com.ipims.MenuViewController;
import com.ipims.views.ViewPatientInfoView;

import javafx.scene.Scene;

public class ViewPatientInfoViewController {

	private ViewPatientInfoView view;
		
		public ViewPatientInfoViewController() {
			view = new ViewPatientInfoView();
			view.createViewPatientInfoView(this);
		}
		public Scene getScene() {
			return view.getCurrentScene();
		}
		
		public void goBack() {
			MenuViewController menu = new MenuViewController();
	        view.getStage().setScene(menu.getScene());
		}   
}
