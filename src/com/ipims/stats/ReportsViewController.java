package com.ipims.stats;

import java.util.List;

import com.ipims.MenuViewController;
import com.ipims.usersession.UserSession;
import com.ipims.views.Appointmentsview;
import com.ipims.views.ReportsView;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.text.Text;

public class ReportsViewController {
	private ReportsView view;
	//public Text reportTitle;
	private ReportManager statRep;


	//--------------- View Controller methods ---------------
	//--------------------------------------------------------

	public ReportsViewController(Text reportTitle) {
		ObservableList<String> items = null;
		if (reportTitle.equals("Health Outcomes")) {
			//List<String> percents = statRep.analyzeHealth();
			//items = FXCollections.observableArrayList (percents);
		} else if (reportTitle.equals("Admission Rates")) {
			//List<String> num = statRep.analyzeAdmissionRate();
			//items = FXCollections.observableArrayList (num);
		} else if (reportTitle.equals("Patient Populations"))
		{
			//List<String> pops = statRep.analyzePatientPopulation();
			//items = FXCollections.observableArrayList(pops);
		}
		view = new ReportsView();
		view.createReportsView(this, reportTitle, items);
	}

	public Scene getScene() {
		return view.getCurrentScene();
	}

	public void goBackStats() {
		 StatsViewController stats = new StatsViewController();
         view.getStage().setScene(stats.getScene());

	}
}
