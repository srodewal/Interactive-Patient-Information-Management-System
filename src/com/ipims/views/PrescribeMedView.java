package com.ipims.views;

//import java.awt.print.PrinterException;
//import java.awt.print.PrinterJob;
import javafx.print.PrinterJob;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.ipims.Helper;
import com.ipims.database.DatabaseManager;
import com.ipims.healthconditions.PrescribeMedViewController;
import com.ipims.models.Patient;
import com.ipims.models.Prescription;
import com.ipims.models.User;
import com.ipims.models.User.UserType;
import com.ipims.usersession.UserSession;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class PrescribeMedView extends BaseView {
	private List<Patient> allPatients;
	
	// method for printing current view
	private void print(Node node) {
	    System.out.println("Creating a printer job...");

	    PrinterJob job = PrinterJob.createPrinterJob();
	    if (job != null) {
	      System.out.println(job.jobStatusProperty().asString());

	      boolean printed = job.printPage(node);
	      if (printed) {
	        job.endJob();
	      } else {
	        System.out.println("Printing failed.");
	      }
	    } else {
	      System.out.println("Could not create a printer job.");
	    }
	  }
	
	public void createPrescribeMedView(User user, PrescribeMedViewController prescribeMedViewController) {

		VBox vbox = new VBox();
		vbox.setPadding(new Insets(25));
		vbox.setSpacing(8);

		HBox hbox = new HBox();
		hbox.setSpacing(10);
		
		Text title = new Text("E-Prescribe");
		title.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
		hbox.getChildren().add(title);

		Button mainMenuBtn = new Button("Main Menu");
		mainMenuBtn.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {
				prescribeMedViewController.goBack();


			}
		});
		hbox.getChildren().add(mainMenuBtn);
		vbox.getChildren().add(hbox);
		
		ListView<String> list = new ListView<String>();
		ObservableList<String> items = FXCollections.observableArrayList (
			);
		
		// for success/error message
		final Text actionTarget = new Text();
		final Text actionTargetForLab = new Text();
		
		// Show Prescribe Medication if Doctor
		//if (user.getUsertype() == UserType.PATIENT ) {
			vbox.getChildren().add(addPrescription(items, actionTarget, actionTargetForLab));
		//}
		
		list.setItems(items); // moved
		

		Text subTitle = new Text("View Prescriptions");
		title.setFont(Font.font("Tahoma", FontWeight.NORMAL, 15));
		vbox.getChildren().add(subTitle);
		
		Button printBtn = new Button("Print");
		printBtn.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {
				// make information printable
				actionTarget.setFill(Color.GREEN);
				actionTarget.setText("Print requested");
				// implementation of print
				print(vbox);
			}
		});

		
		vbox.getChildren().addAll(list, printBtn, actionTarget, actionTargetForLab);
		
		createScene(vbox);
		
	}

	public VBox addPrescription(ObservableList<String> items, Text actionTarget, Text actionTarget2) {

		VBox baseVbox = new VBox();
		baseVbox.setPadding(new Insets(15));
		baseVbox.setSpacing(8);
		baseVbox.setStyle("-fx-background-color: #336699;");

		Text title = new Text("Prescribe Medication");
		title.setFont(Font.font("Tahoma", FontWeight.NORMAL, 15));
		title.setFill(Color.WHITE);

		baseVbox.getChildren().add(title);

		HBox hbox = new HBox();
		hbox.setSpacing(10);

		Label PatientLabel = new Label("Patient:");
		PatientLabel.setTextFill(Color.WHITE);
		/*TextField PatientTextField = new TextField();
		PatientTextField.setPromptText("Patient Name");
		PatientTextField.setMaxSize(120, 5);
		*/
		
		// get all patients
		ComboBox<String> patientComboBox = new ComboBox<String>();
		allPatients = new ArrayList<Patient>();
		allPatients = DatabaseManager.getInstance().getAllPatients();
		for(int i = 0; i < allPatients.size(); i++) {
			patientComboBox.getItems().add(allPatients.get(i).getName());
		}
		// end get all patients

		// set label and text field for medication
		Label MedicationLabel = new Label("Medication:");
		MedicationLabel.setTextFill(Color.WHITE);
		TextField MedicationTextField = new TextField();
		MedicationTextField.setPromptText("Medication");
		MedicationTextField.setMaxSize(110, 5);
		
		// set label and text field for lab test request
		Label LabTestLabel = new Label("Lab Test:");
		LabTestLabel.setTextFill(Color.WHITE);
		TextField LabTestTextField = new TextField();
		LabTestTextField.setPromptText("Lab Test");
		LabTestTextField.setMaxSize(110, 5);

		hbox.getChildren().addAll(PatientLabel, patientComboBox);
		baseVbox.getChildren().add(hbox);
		
		HBox labTest = new HBox();
		labTest.setSpacing(10);
		
		labTest.getChildren().addAll(MedicationLabel, MedicationTextField, LabTestLabel, LabTestTextField);
		baseVbox.getChildren().add(labTest);

		HBox hbox2 = new HBox();
		hbox2.setSpacing(10);

		Button PrescribeMedBtn = new Button("Submit Medication");
		PrescribeMedBtn.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {
				// Pass the control of handling button clicks to the view controller
				
				int temp = patientComboBox.getSelectionModel().getSelectedIndex(); // is -1 if no patient selected
				//System.out.println("Index selected is: " + temp); // test
				
				if(temp == -1) {
					actionTarget.setFill(Color.RED);
					actionTarget.setText("Please select a patient from the dropdown menu!");
				}
				else {
					String patientName = allPatients.get(temp).getName();
					LocalDate currentDate = LocalDate.now(); // current date
					String prescribeMed = patientName + " prescribed " + MedicationTextField.getText() + " on: " + currentDate.toString();
					
					if(MedicationTextField.getText().equals("")) {
						actionTarget.setFill(Color.RED);
						actionTarget.setText("Error: Unable to Prescribe Medication!");
						actionTarget2.setText("");
					}
					else if(!items.contains(prescribeMed)) {
						// check to see if duplicate in database
						boolean duplicate = false;
						List<Prescription> allMeds = new ArrayList<Prescription>();
						Patient tempPatient = Helper.getPatientAtIndex(patientComboBox.getSelectionModel().getSelectedIndex());
						allMeds = DatabaseManager.getInstance().getPrescriptionsForPatient(tempPatient.getUserId());
						for(int i = 0; i < allMeds.size(); i++) {
							if(allMeds.get(i).isCurrent() && allMeds.get(i).getPrescriptionText().equals(MedicationTextField.getText())) {
								duplicate = true;
							}
						}
						
						if(duplicate == false) {
							// send to database
							Prescription newMed = new Prescription();
							tempPatient = Helper.getPatientAtIndex(patientComboBox.getSelectionModel().getSelectedIndex());
							newMed.setUserId(tempPatient.getUserId());
							newMed.setCurrent(true);
							newMed.setDate(currentDate.toString()); // set date prescribed on
							newMed.setPrescriptionText(MedicationTextField.getText());
							DatabaseManager.getInstance().newPrescription(newMed);
							
							// add to visible list
							items.add(prescribeMed);
							
							actionTarget.setFill(Color.GREEN);
							actionTarget.setText("Medication Prescribed!");
							actionTarget2.setText("");
						}
						else {
							actionTarget.setFill(Color.RED);
							actionTarget.setText("Error: Prescription is a duplicate!");
							actionTarget2.setText("");
						}
						
			
					}
					else {
						actionTarget.setFill(Color.RED);
						actionTarget.setText("Error: Prescription already entered!");
						actionTarget2.setText("");
					}
					
					/*// lab test
					temp = patientComboBox.getSelectionModel().getSelectedIndex();
					patientName = allPatients.get(temp).getName();
					currentDate = LocalDate.now(); // current date
					String prescribeTest = patientName + " needs to have lab test for: " + LabTestTextField.getText() + " on: " + currentDate.toString();
					if(LabTestTextField.getText().equals("")) {
						actionTarget2.setFill(Color.RED);
						actionTarget2.setText("Error: Unable to Prescribe Lab Test!");
					}
					else if(!items.contains(prescribeTest)) {
						
						// send to database
						Prescription newTest = new Prescription();
						Patient tempPatient = Helper.getPatientAtIndex(patientComboBox.getSelectionModel().getSelectedIndex());
						newTest.setUserId(tempPatient.getUserId());
						newTest.setCurrent(false); // to indicate lab test
						newTest.setDate(currentDate.toString()); // set date prescribed on
						newTest.setPrescriptionText(LabTestTextField.getText());
						DatabaseManager.getInstance().newPrescription(newTest);
						
						// add to visible list
						items.add(prescribeTest);
						
						actionTarget2.setFill(Color.GREEN);
						actionTarget2.setText("Lab Test Prescribed!");
					}
					else {
						actionTarget2.setFill(Color.RED);
						actionTarget2.setText("Error: Test already entered!");
					} // end else	
					*/
				} // end else for (temp == ?)
			} // end handle method
		}); // end setOnAction method
		
		Button PrescribeTestBtn = new Button("Submit Lab Test");
		PrescribeTestBtn.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {
				// Pass the control of handling button clicks to the view controller
				
				int temp = patientComboBox.getSelectionModel().getSelectedIndex(); // is -1 if no patient selected
				//System.out.println("Index selected is: " + temp); // test
				
				if(temp == -1) {
					actionTarget.setFill(Color.RED);
					actionTarget.setText("Please select a patient from the dropdown menu!");
				}
				else {
					/*String patientName = allPatients.get(temp).getName();
					LocalDate currentDate = LocalDate.now(); // current date
					String prescribeMed = patientName + " prescribed " + MedicationTextField.getText() + " on: " + currentDate.toString();
					
					if(MedicationTextField.getText().equals("")) {
						actionTarget.setFill(Color.RED);
						actionTarget.setText("Error: Unable to Prescribe Medication!");
					}
					else if(!items.contains(prescribeMed)) {
						
						// send to database
						Prescription newMed = new Prescription();
						Patient tempPatient = Helper.getPatientAtIndex(patientComboBox.getSelectionModel().getSelectedIndex());
						newMed.setUserId(tempPatient.getUserId());
						newMed.setCurrent(true);
						newMed.setDate(currentDate.toString()); // set date prescribed on
						newMed.setPrescriptionText(MedicationTextField.getText());
						DatabaseManager.getInstance().newPrescription(newMed);
						
						// add to visible list
						items.add(prescribeMed);
						
						actionTarget.setFill(Color.GREEN);
						actionTarget.setText("Medication Prescribed!");
					}
					else {
						actionTarget.setFill(Color.RED);
						actionTarget.setText("Error: Prescription already entered!");
					}*/
					
					// lab test
					temp = patientComboBox.getSelectionModel().getSelectedIndex();
					String patientName = allPatients.get(temp).getName();
					LocalDate currentDate = LocalDate.now(); // current date
					String prescribeTest = patientName + " needs to have lab test for: " + LabTestTextField.getText() + " on: " + currentDate.toString();
					if(LabTestTextField.getText().equals("")) {
						actionTarget2.setFill(Color.RED);
						actionTarget2.setText("Error: Unable to Prescribe Lab Test!");
						actionTarget.setText("");
					}
					else if(!items.contains(prescribeTest)) {
						// check to see if duplicate in database
						boolean duplicate = false;
						List<Prescription> allMeds = new ArrayList<Prescription>();
						Patient tempPatient = Helper.getPatientAtIndex(patientComboBox.getSelectionModel().getSelectedIndex());
						allMeds = DatabaseManager.getInstance().getPrescriptionsForPatient(tempPatient.getUserId());
						for(int i = 0; i < allMeds.size(); i++) {
							if(!allMeds.get(i).isCurrent() && allMeds.get(i).getPrescriptionText().equals(LabTestTextField.getText())) {
								duplicate = true;
							}
						}
						
						if(duplicate == false) {
							// send to database
							Prescription newTest = new Prescription();
							tempPatient = Helper.getPatientAtIndex(patientComboBox.getSelectionModel().getSelectedIndex());
							newTest.setUserId(tempPatient.getUserId());
							newTest.setCurrent(false); // to indicate lab test
							newTest.setDate(currentDate.toString()); // set date prescribed on
							newTest.setPrescriptionText(LabTestTextField.getText());
							DatabaseManager.getInstance().newPrescription(newTest);
							
							// add to visible list
							items.add(prescribeTest);
							
							actionTarget2.setFill(Color.GREEN);
							actionTarget2.setText("Lab Test Prescribed!");
							actionTarget.setText("");
						}
						
						else {
							actionTarget2.setFill(Color.RED);
							actionTarget2.setText("Error: Test is a duplicate!");
							actionTarget.setText("");
						}
						
					}
					else {
						actionTarget2.setFill(Color.RED);
						actionTarget2.setText("Error: Test already entered!");
						actionTarget.setText("");
					} // end else	
				} // end else for (temp == ?)
			} // end handle method
		}); // end setOnAction method
		
		Button showMedBtn = new Button("View");
		showMedBtn.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {
				// Pass the control of handling button clicks to the view controller
				
				if(patientComboBox.getSelectionModel().getSelectedIndex() != -1) {
					// clear list
					items.clear();
					
					// show all prescriptions for patient
					List<Prescription> allMeds = new ArrayList<Prescription>();
					Patient tempPatient = Helper.getPatientAtIndex(patientComboBox.getSelectionModel().getSelectedIndex());
					allMeds = DatabaseManager.getInstance().getPrescriptionsForPatient(tempPatient.getUserId());
					System.out.println("List " + tempPatient.getUserId() + "\n");
					for(int i = 0; i < allMeds.size(); i++) {
						if(allMeds.get(i).isCurrent()) {
							String currMedication = allMeds.get(i).getPrescriptionText() + " prescribed on: " + allMeds.get(i).getDate();
							items.add(currMedication);
						}
						else {
							String currTest = allMeds.get(i).getPrescriptionText() + " ordered for lab test on: " + allMeds.get(i).getDate();
							items.add(currTest);
						}
					}
					
					actionTarget.setFill(Color.GREEN);
					actionTarget.setText("Viewing!");
					actionTarget2.setText("");
				} // end if
				else {
					actionTarget.setFill(Color.RED);
					actionTarget.setText("Please select a patient from the dropdown menu!");
					actionTarget2.setText("");
				}
				
				
			} // end handle method
		}); // end setOnAction method

		HBox buttons = new HBox();
		buttons.setSpacing(10);
		
		// only able to prescribe if doctor
		if(UserSession.getInstance().getCurrentUser().getUsertype() == UserType.HSPSTAFF) {
			buttons.getChildren().add(showMedBtn);
		}
		else {
			buttons.getChildren().addAll(PrescribeMedBtn, PrescribeTestBtn, showMedBtn);
		}
		
		baseVbox.getChildren().add(buttons);

		return baseVbox;
	}
}
