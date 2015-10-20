package com.ipims.labrecord;

import com.ipims.MenuViewController;
import com.ipims.usersession.UserSession;
import com.ipims.views.LabRecordView;
import com.ipims.views.PatientCaseView;

import javafx.scene.Scene;

public class LabRecordViewController {

	private LabRecordView view;

	//--------------- View Controller methods ---------------
	//--------------------------------------------------------
	
public  LabRecordViewController() {
	view = new LabRecordView();
	view.createLabRecordView(UserSession.getInstance().getCurrentUser(), this);
}

public Scene getScene() {
	return view.getCurrentScene();
}

	public void goBack() {
		 MenuViewController menu = new MenuViewController();
         view.getStage().setScene(menu.getScene());
         
	}

	
	
	
	
}
