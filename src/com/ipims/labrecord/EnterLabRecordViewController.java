package com.ipims.labrecord;

import com.ipims.MenuViewController;
import com.ipims.usersession.UserSession;
import com.ipims.views.EnterLabRecordView;

import javafx.scene.Scene;

public class EnterLabRecordViewController {

	
	private EnterLabRecordView view;

	//--------------- View Controller methods ---------------
	//--------------------------------------------------------
	
public  EnterLabRecordViewController() {
	view = new EnterLabRecordView();
	view.createEnterLabRecordView(UserSession.getInstance().getCurrentUser(), this);
}

public Scene getScene() {
	return view.getCurrentScene();
}

	public void goBack() {
		 MenuViewController menu = new MenuViewController();
         view.getStage().setScene(menu.getScene());
         
	}


	
}
