package com.ipims;


import com.ipims.appointment.AppointmentViewController;
import com.ipims.database.DatabaseManager;
import com.ipims.healthconditions.HealthViewController;
import com.ipims.hsp.ViewPatientInfoViewController;
import com.ipims.labrecord.EnterLabRecordViewController;
import com.ipims.labrecord.LabRecordViewController;
import com.ipims.medication.PrescribeMedViewController;
import com.ipims.patientcase.PatientCaseViewController;
import com.ipims.stats.StatsViewController;
import com.ipims.usersession.LoginViewController;
import com.ipims.usersession.UserSession;
import com.ipims.views.MenuView;

import javafx.scene.Scene;

/**
 * Shows the initial menu view. MenuView will produce menu based on the logged in user type.
 * 
 * @author jithin
 *
 */
public class MenuViewController {
	private MenuView view;


	//--------------- View Controller methods ---------------
	//--------------------------------------------------------
	
	public MenuViewController() {
		view = new MenuView();
		view.createMenuScene(UserSession.getInstance().getCurrentUser(), this);
		DatabaseManager.getInstance();
	}

	public Scene getScene() {
		return view.getCurrentScene();
	}

	//--------------- User interaction methods ---------------
	//--------------------------------------------------------
	
	public void handleAppointments() {
		AppointmentViewController schedule = new AppointmentViewController();
		view.getStage().setScene(schedule.getScene());	
	}
	
	public void handleHealthCondition() {
		HealthViewController healthview = new HealthViewController();
		view.getStage().setScene(healthview.getScene());
	}

	public void handleLogout() {
		UserSession.getInstance().logout();
		LoginViewController vc = new LoginViewController();
		view.getStage().setScene(vc.getScene());
	}
	
	public void handleGenerateStatsReports() {
		StatsViewController statsView = new StatsViewController();
		view.getStage().setScene(statsView.getScene());
	}

	public void handlePrescribeMed() {
		PrescribeMedViewController PrescribeMedView = new PrescribeMedViewController();
		view.getStage().setScene(PrescribeMedView.getScene());
	}
	
	public void handlePatientCase() {
		PatientCaseViewController PatientCaseView = new PatientCaseViewController();
		view.getStage().setScene(PatientCaseView.getScene());
	}
	
	public void handleLabRecord() {
		LabRecordViewController LabRecordView = new LabRecordViewController();
		view.getStage().setScene(LabRecordView.getScene());
	}
	
	public void handleEnterLabRecord() {
		EnterLabRecordViewController EnterLabRecordView = new EnterLabRecordViewController();
		view.getStage().setScene(EnterLabRecordView.getScene());
	}
	
	public void handleViewPatientInfo() {
		ViewPatientInfoViewController PatientInfoView = new ViewPatientInfoViewController();
		view.getStage().setScene(PatientInfoView.getScene());
	}

}

















