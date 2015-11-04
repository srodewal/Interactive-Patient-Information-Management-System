package com.ipims.database;

import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.ipims.models.*;
import com.ipims.models.User.UserType;

//TODO: make sure everything that uses a database connection checks for a null connection
//TODO: make sure every statement gets closed

/**
 * 
 * @author Matthew Hanna
 *	
 *	Singleton class that will manage all database queries.
 */
public class DatabaseManager {
	private static final DatabaseManager INSTANCE = new DatabaseManager(); //greedily instantiate an instance of this singleton as we always need a database connection

	private Connection dbConnection = null;

	private static final int NUM_TABLES = 7; //used for sizing of the set containing tables that should exist

	//initiates connection to the database and then calls method to check that necessary tables exist
	private DatabaseManager()
	{

		try
		{
			Class.forName("org.sqlite.JDBC");
			dbConnection = DriverManager.getConnection("jdbc:sqlite:ipims.db");
			checkTables();
		}
		catch(Exception e)
		{
			logError("Could not open database file. Please check your file permissions.");
			logError(e.getMessage());
		}
	}
	
	//gets existing table names and then calls method to add any missing tables if any are not present
	private  void checkTables()
	{
		if(dbConnection != null)
		{
			HashSet<String> existingTables = new HashSet<>(NUM_TABLES); // use a set to store the existing tables as this makes it easy to check for any missing tables
			try
			{
				DatabaseMetaData dbMeta = dbConnection.getMetaData();
				ResultSet tables = dbMeta.getTables(null, null, "%", null);
				while(tables.next())
				{
					existingTables.add(tables.getString(3));
				}

				if(existingTables.size() < NUM_TABLES)
				{
					fixTables(existingTables);
				}
			}
			catch(Exception e)
			{
				logError("Could not grab database metadata. Please check that the database exists and is valid.");
				logError(e.getMessage());
			}
		}
	}
	/**
	 * Adds any missing tables to the database
	 * @param existingTables Set of tables that already exist in the database
	 */
	private  void fixTables(HashSet<String> existingTables)
	{
		if(dbConnection != null)
		{
			if(!existingTables.contains("User"))
			{
				try
				{
					/* This table contains anything related to a generic user, any information specific to a type of patient
					 * should be stored in another table indexed by user id
					 */
					Statement createUser = dbConnection.createStatement();
					createUser.executeUpdate("CREATE TABLE User("
							+ "userId INTEGER PRIMARY KEY AUTOINCREMENT,"
							+ "name TEXT NOT NULL,"
							+ "userName TEXT NOT NULL,"
							+ "passwordHash TEXT NOT NULL,"
							+ "ssn TEXT NOT NULL,"
							+ "type INTEGER NOT NULL,"
							+ "dob TEXT NOT NULL,"
							+ "address TEXT NOT NULL,"
							+ "email TEXT NOT NULL,"
							+ "phoneNumber TEXT NOT NULL,"
							+ "insurance TEXT NOT NULL,"
							+ "sex TEXT,"
							+ "race TEXT"
							+ ")");
					createUser.close();
				}
				catch(Exception e)
				{
					logError("Could not write missing tables to database. Please check that the database exists and is valid.");
					logError(e.getMessage());
				}
			}
			if(!existingTables.contains("DoctorCategory"))
			{
				try
				{
					/*
					 * This table contains the category of doctors
					 */
					Statement createDoctorCategory = dbConnection.createStatement();
					createDoctorCategory.executeUpdate("CREATE TABLE DoctorCategory( userId INTEGER PRIMARY KEY NOT NULL,"
							+ " category TEXT NOT NULL )");
					createDoctorCategory.close();
				}
				catch(Exception e)
				{
					logError("Could not write missing tables to database. Please check that the database exists and is valid.");
					logError(e.getMessage());
				}
			}
			if(!existingTables.contains("Appointment"))
			{
				try
				{
					/*
					 * This table contains appointments for patients to see a certain doctor. Only id's are stored for users.
					 */
					Statement createAppointment = dbConnection.createStatement();
					createAppointment.executeUpdate("CREATE TABLE Appointments( "
							+ "id INTEGER PRIMARY KEY AUTOINCREMENT,"
							+ "patientId INTEGER NOT NULL,"
							+ "doctorId INTEGER,"
							+ "category TEXT,"
							+ "time TEXT NOT NULL,"
							+ "date TEXT NOT NULL"
							+ ")");
					createAppointment.close();
				}
				catch(Exception e)
				{
					logError("Could not write missing tables to database. Please check that the database exists and is valid.");
					logError(e.getMessage());
				}
			}
			if(!existingTables.contains("HealthCondition"))
			{
				try
				{
					/*
					 * This table contains any health conditions that a patient might have. The only patient information that is
					 * stored is their id.
					 */
					Statement createHealthCondition = dbConnection.createStatement();
					createHealthCondition.executeUpdate("CREATE TABLE HealthCondition("
							+ "id INTEGER PRIMARY KEY AUTOINCREMENT,"
							+ "userId INTEGER NOT NULL,"
							+ "healthConcerns TEXT NOT NULL,"
							+ "comments TEXT,"
							+ "severity INTEGER NOT NULL,"
							+ "isCurrent INTEGER NOT NULL"
							+ ")");
					createHealthCondition.close();

				}
				catch(Exception e)
				{
					logError("Could not write missing tables to database. Please check that the database exists and is valid.");
					logError(e.getMessage());
				}
			}
			if(!existingTables.contains("LabRecord"))
			{
				try
				{
					/*
					 * This contains lab records associated with a patient. The only patient information stored is their id.
					 */
					Statement createLabRecord = dbConnection.createStatement();
					createLabRecord.executeUpdate("CREATE TABLE LabRecord("
							+ "recordId INTEGER PRIMARY KEY AUTOINCREMENT,"
							+ "userId INTEGER NOT NULL,"
							+ "glucose REAL NOT NULL,"
							+ "sodium REAL NOT NULL,"
							+ "magnesium REAL NOT NULL,"
							+ "calcium REAL NOT NULL"
							+ ")");
					createLabRecord.close();
				}
				catch(Exception e)
				{
					logError("Could not write missing tables to database. Please check that the database exists and is valid.");
					logError(e.getMessage());
				}
			}
			if(!existingTables.contains("Prescription"))
			{
				try
				{
					/*
					 * This table contains prescriptions associates with a patient. The only patient information stored is their
					 * id.
					 */
					Statement createPrescription = dbConnection.createStatement();
					createPrescription.executeUpdate("CREATE TABLE Prescription("
							+ "prescriptionId INTEGER PRIMARY KEY AUTOINCREMENT,"
							+ "userId INTEGER NOT NULL,"
							+ "date TEXT NOT NULL,"
							+ "medicine TEXT NOT NULL,"
							+ "pastOrCurrent INTEGER NOT NULL,"
							+ "testOrMedicine INTEGER NOT NULL"
							+ ")");
					createPrescription.close();
				}
				catch(Exception e)
				{
					logError("Could not write missing tables to database. Please check that the database exists and is valid.");
					logError(e.getMessage());
				}
			}
			if(!existingTables.contains("Alert"))
			{
				try
				{
					/*
					 * This table contains any alerts that a doctor should see. It is associated with a certain doctor and a
					 * certain health condition.
					 */
					Statement createAlert = dbConnection.createStatement();
					createAlert.executeUpdate("CREATE TABLE Alert("
							+ "alertId INTEGER PRIMARY KEY AUTOINCREMENT,"
							+ "conditionId INTEGER NOT NULL,"
							+ "doctorId INTEGER NOT NULL"
							+ "message TEXT NOT NULL,"
							+ "read INTEGER NOT NULL"
							+ ")");
					createAlert.close();
				}
				catch(Exception e)
				{
					logError("Could not write missing tables to database. Please check that the database exists and is valid.");
					logError(e.getMessage());
				}
			}
		}
		else
		{
			logError("Could not write missing tables to database. Please check that the database exists and is valid.");
		}
	}
	
	//wrapper for global log
	private void logError(String errMessage)
	{

		Logger.getGlobal().log(Level.WARNING, errMessage);
	}

	//get current instance of this singleton
	public static DatabaseManager getInstance()
	{
		return INSTANCE;
	}

	//close the database connection once we don't need it anymore (will likely be used only on exit)
	public void close()
	{
		if(dbConnection != null)
		{
			try
			{
				dbConnection.close();
			}
			catch(Exception e)
			{
				logError(e.getMessage());
			}
		}

	}

	/**
	 * Add a user to the database. This only works for a generic user, any additional information will require a different method
	 * or a wrapper around this one.
	 * @param user The user that should be added to the database
	 * @param password The user's password
	 * @return Returns the given user with their id set to the id generated by the database
	 */
	public  User newUser(User user, String password)
	{
		if(dbConnection != null)
		{
			try
			{
				System.out.println("Trying to create user " + user.getName() + " " + user.getUsertype().ordinal());
				PreparedStatement insertPatient = dbConnection.prepareStatement("INSERT INTO User (name, userName, passwordHash, ssn, type, dob, address, email, phoneNumber, insurance, sex, race) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
				insertPatient.setString(1, user.getName());
				insertPatient.setString(2, user.getUserName());
				insertPatient.setString(3, password);
				insertPatient.setString(4, user.getSsn());
				insertPatient.setInt(5, user.getUsertype().ordinal());
				insertPatient.setString(6, user.getDateOfBirth());
				insertPatient.setString(7, user.getAddress());
				insertPatient.setString(8, user.getEmail());
				insertPatient.setString(9, user.getPhoneNumber());
				insertPatient.setString(10, user.getInsurance());
				insertPatient.setString(11, user.getSex());
				insertPatient.setString(12, user.getRace());
				
				int affectedRows = insertPatient.executeUpdate();
				if(affectedRows != 0)
				{
					ResultSet rs = insertPatient.getGeneratedKeys();
					if(rs.next())
					{
						user.setUserId(rs.getInt(1));
					}
				}
				insertPatient.close();
			}
			catch(Exception e)
			{
				logError("Cannot insert new patient. Check that inputs are correct and the database has been set up properly.");
				logError(e.getMessage());
			}
		}
		else
		{
			logError("Cannot insert new patient as there is no current database connection.");
		}
		
		return user;
	}

	/**
	 * Adds an appointment to the database
	 * @param appointment The appointment that should be added to the database
	 * @return Returns the given appointment with its id set to the id generated by the database.
	 */
	public  Appointment newAppointment(Appointment appointment)
	{
		if(dbConnection != null)
		{
			try
			{
				PreparedStatement insertAppointment = dbConnection.prepareStatement("INSERT INTO Appointments (patientId, doctorId, category, time, date) VALUES (?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
				insertAppointment.setInt(1, appointment.getPatient().getUserId());
				if (appointment.getDoctor() != null) {
					insertAppointment.setInt(2, appointment.getDoctor().getUserId());
				} else {
					insertAppointment.setNull(2, java.sql.Types.INTEGER);
				}
				
				insertAppointment.setString(3, appointment.getCategory());
				insertAppointment.setString(4, appointment.getTime());
				insertAppointment.setString(5, appointment.getDate().toString());
				
				int affectedRows = insertAppointment.executeUpdate();
				
				if(affectedRows != 0)
				{
					ResultSet rs = insertAppointment.getGeneratedKeys();
					if(rs.next())
					{
						appointment.setAppointmentId(rs.getInt(1));
					}
				}
				
				insertAppointment.close();
				
			}
			catch(Exception e)
			{
				logError("Cannot insert new appointment. Check that inputs are correct and the datbase has been set up properly.");
				logError(e.getMessage());
			}
		}
		else
		{
			logError("Cannot insert new appointment as there is no current database connection.");
		}
		
		return appointment;
	}

	/**
	 * Adds a patient's health condition to the database. This can be either a current condition or part of the patient's medical
	 * history.
	 * @param condition Condition that should be added to the database
	 * @return Returns the condition given with its id set to the id generated by the database
	 */
	public HealthCondition newHealthCondition(HealthCondition condition)
	{
		if(dbConnection != null)
		{
			try
			{
				PreparedStatement insertHealthCondition = dbConnection.prepareStatement("INSERT INTO HealthCondition (userId,healthConcerns,comments,severity,isCurrent) VALUES(?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
				insertHealthCondition.setInt(1, condition.getPatientId());
				insertHealthCondition.setString(2, condition.getHealthConcern());
				insertHealthCondition.setString(3, condition.getComments());
				insertHealthCondition.setInt(4, condition.getSeverity());
				insertHealthCondition.setInt(5, condition.isCurrent() ? 1 : 0);
				
				int affectedRows = insertHealthCondition.executeUpdate();
				
				if(affectedRows != 0)
				{
					ResultSet rs = insertHealthCondition.getGeneratedKeys();
					if(rs.next())
					{
						condition.setHealthConditionId(rs.getInt(1));
					}
				}
				
				insertHealthCondition.close();
			}
			catch(Exception e)
			{
				logError("Cannot insert new health condition. Check that inputs are correct and the database has been set up properly.");
				logError(e.getMessage());
			}
		}
		else
		{
			logError("Cannot insert new health condition as there is no current database connection.");
		}
		
		return condition;
	}

	/**
	 * Adds a patient's lab record to the database.
	 * @param record Record that should be added to the database.
	 * @return Returns the given record with its id set to the id generated by the database.
	 */
	public LabRecord newLabRecord(LabRecord record)
	{
		try
		{
			PreparedStatement insertRecord = dbConnection.prepareStatement("INSERT INTO LabRecord (userId, glucose, calcium, magnesium, sodium) VALUES (?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
			insertRecord.setInt(1, record.getPatientId());
			insertRecord.setFloat(2, record.getGlucose());
			insertRecord.setFloat(3, record.getCalcium());
			insertRecord.setFloat(4, record.getMagnesium());
			insertRecord.setFloat(5, record.getSodium());

			int affectedRows = insertRecord.executeUpdate();
			
			if(affectedRows != 0)
			{
				ResultSet rs = insertRecord.getGeneratedKeys();
				if(rs.next())
				{
					record.setLabRecordId(rs.getInt(1));
				}
			}
			
			insertRecord.close();
		}
		catch(Exception e)
		{
			logError("Could not add lab record to the database. Please check that the database has been set up properly.");
			logError(e.getMessage());
		}
		
		return record;
	}

	/**
	 * Adds a patient's prescription to the database. This can either be a prescription for a medicine or a test. It can also
	 * be a current prescription or part of the patient's medical history.
	 * @param prescription The prescription that should be added to the database.
	 * @return Returns the given prescription with its id set to the id generated by the database
	 */
	public Prescription newPrescription(Prescription prescription)
	{
		try
		{
			System.out.println("p1");
			PreparedStatement insertPrescription = dbConnection.prepareStatement("INSERT INTO Prescription (userId, date, medicine, pastOrCurrent, testOrMedicine) VALUES (?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
			System.out.println("p2");
			System.out.println("Setup " + prescription.getUserId() + "\n");
			insertPrescription.setInt(1, prescription.getUserId());
			insertPrescription.setString(2, prescription.getDate());
			insertPrescription.setString(3, prescription.getPrescriptionText());
			insertPrescription.setInt(4, prescription.isCurrent() ? 1 : 0);
			insertPrescription.setInt(5, prescription.isMedicine() ? 1 : 0);

			int affectedRows = insertPrescription.executeUpdate();
			
			if(affectedRows != 0)
			{
				ResultSet rs = insertPrescription.getGeneratedKeys();
				if(rs.next())
				{
					prescription.setPrescriptionId(rs.getInt(1));
				}
			}
			
			insertPrescription.close();
		}
		catch(Exception e)
		{
			logError("Could not add prescription the the database. Please check that the database has been set up properly.");
			logError(e.getMessage());
		}
		
		return prescription;
	}
	
	/**
	 * Adds an alert to the database.
	 * @param alert alert that should be added to the database
	 * @return Returns the given alert with its id set to the id generated by the database
	 */
	public Alert newAlert(Alert alert)
	{
		try
		{
			PreparedStatement insertAlert = dbConnection.prepareStatement("INSERT INTO Alert (conditionId, doctorId, message, read) VALUES(?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
			insertAlert.setInt(1, alert.getConditionId());
			insertAlert.setInt(2, alert.getDoctorId());
			insertAlert.setString(3, alert.getMessage());
			insertAlert.setInt(4, alert.isRead() ? 1 : 0);
			
			int affectedRows = insertAlert.executeUpdate();
			
			if(affectedRows != 0)
			{
				ResultSet rs = insertAlert.getGeneratedKeys();
				if(rs.next())
				{
					alert.setAlertId(rs.getInt(1));
				}
			}
			
			insertAlert.close();
		}
		catch(Exception e)
		{
			logError("Could not add alert to the database. Please check that the database has been set up properly.");
			logError(e.getMessage());
		}
		
		return alert;
	}

	/**
	 * Logs in a user and returns their information.
	 * @param userName username of the desired user
	 * @param password password of the desired user
	 * @return Returns the user's information
	 */
	public User getUser(String userName, String password) {
		User user = null;
		try {
			PreparedStatement stat = dbConnection.prepareStatement("SELECT * FROM User WHERE userName = ? AND passwordHash = ?");
			stat.setString(1, userName);
			stat.setString(2, password);
			ResultSet rs = stat.executeQuery();
			if (rs.isClosed() == false) {
				user = createUser(rs);
			}

		} catch (SQLException e) {

			e.printStackTrace();
		}
		return user;
	}

	/**
	 * Gets information about a user based on their id. This is the general use version of the above.
	 * @param userId id of the desired user
	 * @return Returns the user's information
	 */
	public User getUser(int userId)
	{
		User user = null;

		try
		{
			PreparedStatement userStatement = dbConnection.prepareStatement("SELECT * FROM User WHERE userId = ?");
			userStatement.setInt(1, userId);

			ResultSet rs = userStatement.executeQuery();
			if(rs.isClosed() == false)
			{
				user = createUser(rs);
			}
		}
		catch(Exception e)
		{
			logError("Could not get user. Please check that the database has been set up properly.");
			logError(e.getMessage());
		}

		return user;
	}

	/**
	 * Gets information about a user based on their username. This is an alternative to retrieving by id.
	 * @param userName username of the desired user
	 * @return Returns the user's information
	 */
	public User getUser(String userName)
	{
		User user = null;

		try
		{
			PreparedStatement userStatement = dbConnection.prepareStatement("SELECT * FROM User WHERE userName = ?");
			userStatement.setString(1, userName);

			ResultSet rs = userStatement.executeQuery();
			if(rs.isClosed() == false)
			{
				user = createUser(rs);
			}
		}
		catch(Exception e)
		{
			logError("Could not get user. Please check that the database has been set up properly.");
			logError(e.getMessage());
		}

		return user;
	}

	/**
	 * Gets a list of all users with the patient type.
	 * @return List of all patients
	 */
	public List<User> getPatientList() {

		List<User> userList = new ArrayList<>();

		try {
			Statement stat = dbConnection.createStatement();
			ResultSet rs = stat.executeQuery("select * from USER WHERE type = " + UserType.PATIENT.ordinal() + ";");

			while (rs.next()) {

				User user = createUser(rs);
				userList.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return userList;
	}

	/**
	 * Gets a list of all appointments associated with a certain patient.
	 * @param patient The patient associated with the appointments
	 * @return Returns a list of appointments associated with the patient's id
	 */
	public List<Appointment> getAppointmentForPatient(Patient patient)
	{
		List<Appointment> appointmentList = new ArrayList<>();
		try
		{

			PreparedStatement getAppointments = dbConnection.prepareStatement("SELECT * FROM Appointments WHERE patientId = ?");
			getAppointments.setInt(1, patient.getUserId());

			ResultSet rs = getAppointments.executeQuery();

			while(rs.next())
			{
				Appointment appointment = createAppoinment(rs);
				appointmentList.add(appointment);
			}
		}
		catch(Exception e)
		{
			logError("Could not get appointments. Please check the database has been set up correctly.");
			logError(e.getMessage());
		}

		return appointmentList;
	}

	/**
	 * Get every appointment in the database. This is useful for checking availability.
	 * @return Returns a list of all appointments
	 */
	public List<Appointment> getAllAppointments()
	{
		List<Appointment> appointmentList = new ArrayList<>();
		try
		{

			PreparedStatement getAppointments = dbConnection.prepareStatement("SELECT * FROM Appointments");
			ResultSet rs = getAppointments.executeQuery();

			while(rs.next())
			{
				Appointment appointment = createAppoinment(rs);
				appointmentList.add(appointment);
			}
		}
		catch(Exception e)
		{
			logError("Could not get appointments. Please check the database has been set up correctly.");
			logError(e.getMessage());
		}

		return appointmentList;
	}

	/**
	 * Gets all prescriptions associated with a certain patient.
	 * @param patientId id of the patient associated with the prescriptions.
	 * @return Returns a list of all prescriptions associated with given id
	 */
	public List<Prescription> getPrescriptionsForPatient(int patientId)
	{
		List<Prescription> prescriptions = new ArrayList<>();

		try
		{
			PreparedStatement getPrescriptions = dbConnection.prepareStatement("SELECT * FROM Prescription WHERE userId = ?");
			getPrescriptions.setInt(1, patientId);

			ResultSet rs = getPrescriptions.executeQuery();
			while(rs.next())
			{
				Prescription prescription = new Prescription();
				prescription.setPrescriptionId(rs.getInt("prescriptionId"));
				prescription.setUserId(patientId);
				prescription.setDate(rs.getString("date"));
				prescription.setPrescriptionText(rs.getString("medicine"));

				if(rs.getInt("pastOrCurrent") == 0)
				{
					prescription.setCurrent(false);
				}
				else
				{
					prescription.setCurrent(true);
				}
				
				if(rs.getInt("testOrMedicine") == 0)
				{
					prescription.setMedicine(false);
				}
				else
				{
					prescription.setMedicine(true);
				}

				prescriptions.add(prescription);
			}
		}
		catch(Exception e)
		{
			logError("Could not get prescriptions. Please check the database has been set up correctly.");
			logError(e.getMessage());
		}

		return prescriptions;
	}
	
	/**
	 * Gets all alerts associated with a certain doctor.
	 * @param doctorId id of the doctor associated with the alerts
	 * @return Returns all alerts associated with the given id
	 */
	public List<Alert> getAlertsForDoctor(int doctorId)
	{
		List<Alert> alerts = new ArrayList<>();
		
		try
		{
			PreparedStatement getAlerts = dbConnection.prepareStatement("SELECT * FROM Alert WHERE doctorId = ?");
			getAlerts.setInt(1, doctorId);
			
			ResultSet rs = getAlerts.executeQuery();
			while(rs.next())
			{
				Alert alert = null;
				if(rs.getInt("read") == 0)
				{
					alert = new Alert(rs.getInt("alertId"), rs.getInt("conditionId"), doctorId, rs.getString("message"), false);
				}
				else
				{
					alert = new Alert(rs.getInt("alertId"), rs.getInt("conditionId"), doctorId, rs.getString("message"), true);
				}
				
				alerts.add(alert);
			}
			
			getAlerts.close();
		}
		catch(Exception e)
		{
			logError("Could not get alerts. Please check that the database has been set up correctly.");
			logError(e.getMessage());
		}
		
		return alerts;
	}

	/**
	 * Gets a list of all users with the doctor type
	 * @return Returns a list of all doctors
	 */
	public List<Doctor> getAllDoctors()
	{
		List<Doctor> doctors = new ArrayList<>();

		try
		{
			Statement getDoctors = dbConnection.createStatement();
			ResultSet rs = getDoctors.executeQuery("SELECT * FROM User LEFT OUTER JOIN DoctorCategory ON User.userId = DoctorCategory.userId AND User.type=" + UserType.DOCTOR.ordinal());

			while(rs.next())
			{
				Doctor doctor = (Doctor) createUser(rs);
				doctor.setCategory(rs.getString("category"));

				doctors.add(doctor);
			}
		}
		catch(Exception e)
		{
			logError("Could not get doctors from the database. Please check that the database has been set up correctly.");
			logError(e.getMessage());
		}

		return doctors;
	}

	/**
	 * Get a list of all patients. Different from getPatientList in that it coerces the type of each user to Patient.
	 * @return Returns a list of all patients
	 */
	public List<Patient> getAllPatients()
	{
		List<Patient> patients = new ArrayList<>();

		try
		{
			PreparedStatement getPatients = dbConnection.prepareStatement("SELECT * FROM User WHERE type = ?");
			getPatients.setInt(1, UserType.PATIENT.ordinal());
			ResultSet rs = getPatients.executeQuery();

			while(rs.next())
			{
				Patient patient = (Patient) createUser(rs);

				patients.add(patient);
			}
		}
		catch(Exception e)
		{
			logError("Could not get patients from the database. Please check that the database has been set up correctly.");
			logError(e.getMessage());
		}

		return patients;
	}

	/**
	 * Lists all conditions associated with a certain patient.
	 * @param patient Patient associated with the conditions
	 * @return Returns a list of all conditions associated with the given patient
	 */
	public List<HealthCondition> getPatientConditions(Patient patient)
	{
		List<HealthCondition> conditions = new ArrayList<>();
		try
		{

			PreparedStatement getAppointments = dbConnection.prepareStatement("SELECT * FROM HealthCondition WHERE userId = ?");
			getAppointments.setInt(1, patient.getUserId());

			ResultSet rs = getAppointments.executeQuery();
			while(rs.next()) {
				HealthCondition healthCondition  = new HealthCondition();				
				healthCondition.setHealthConditionId(rs.getInt("id"));
				healthCondition.setHealthConcern(rs.getString("healthConcerns"));
				healthCondition.setComments(rs.getString("comments"));
				healthCondition.setSeverity(rs.getInt("severity"));
				healthCondition.setCurrent(rs.getInt("isCurrent")==1);
				healthCondition.setPatientId(rs.getInt("userId"));
				conditions.add(healthCondition);
			}
		}
		catch(Exception e)
		{
			logError("Could not get patient condition. Please check the database has been set up correctly.");
			logError(e.getMessage());
		}

		return conditions;
	}

	/**
	 * Gets all lab records associated with a certain patient.
	 * @param patientId id of the patient associated with the records
	 * @return Returns a list of lab records associated with the given id
	 */
	public List<LabRecord> getLabRecordsForPatient(int patientId)
	{
		ArrayList<LabRecord> records = new ArrayList<>();
		try
		{	
			PreparedStatement getRecords = dbConnection.prepareStatement("SELECT * FROM LabRecord WHERE userId = ?");		
			getRecords.setInt(1, patientId);
			ResultSet rs = getRecords.executeQuery();
			
			while(rs.next())
			{
				LabRecord record = new LabRecord();
				record.setPatientId(rs.getInt("userId"));
				record.setLabRecordId(rs.getInt("recordId"));
				record.setGlucose(rs.getFloat("glucose"));
				record.setSodium(rs.getFloat("sodium"));
				record.setCalcium(rs.getFloat("calcium"));
				record.setMagnesium(rs.getFloat("magnesium"));
				records.add(record);
			}
		}
		catch(Exception e)
		{
			logError("Could not retrieve list of lab records. Please check that the database has been properly set up.");
			logError(e.getMessage());
		}

		return records;
	}
	
	/**
	 * Counts the number of users with the patient type in the database. This is used for statistical reports.
	 * @return Returns the number of patients in the database
	 */
	public int getNumberOfRegisteredPatients() 
	{
		int NumberOfPatients = 0; 
		try {
			Statement getNumber = dbConnection.createStatement();
			
			ResultSet rs = getNumber.executeQuery("SELECT COUNT(*) AS NumberOfPatients FROM User WHERE type=" + UserType.PATIENT.ordinal() + ";");
			NumberOfPatients = rs.getInt(1);
		}
		catch(Exception e) 
		{
			logError("Could not retrieve patient count. Please check that the database has been properly set up.");
			logError(e.getMessage());
		}
		
		return NumberOfPatients;
	}

	/**
	 * Gets all lab records in the database. This is used for statistical reports.
	 * @return
	 */
	public List<LabRecord> getAllLabRecord()
	{
		ArrayList<LabRecord> records = new ArrayList<>();
		try
		{	
			PreparedStatement getRecords = dbConnection.prepareStatement("SELECT * FROM LabRecord");		
			ResultSet rs = getRecords.executeQuery();
			
			while(rs.next())
			{
				LabRecord record = new LabRecord();
				record.setPatientId(rs.getInt("userId"));
				record.setLabRecordId(rs.getInt("recordId"));
				record.setGlucose(rs.getFloat("glucose"));
				record.setSodium(rs.getFloat("sodium"));
				record.setCalcium(rs.getFloat("calcium"));
				record.setMagnesium(rs.getFloat("magnesium"));
				records.add(record);
			}
		}
		catch(Exception e)
		{
			logError("Could not retrieve list of lab records. Please check that the database has been properly set up.");
			logError(e.getMessage());
		}

		return records;
	}
	
	/**
	 * Updates an appointment that already exists in the database.
	 * @param appoinment Appointment that needs updating
	 */
	public void updateAppointment(Appointment appoinment) {
		if(dbConnection != null)
		{
			try
			{
				PreparedStatement updateAppointment = dbConnection.prepareStatement("UPDATE Appointments SET patientId = ?, doctorId = ?, category = ?, time = ?, date = ? WHERE id = ?");
				updateAppointment.setInt(1, appoinment.getPatient().getUserId());
				updateAppointment.setInt(2, appoinment.getDoctor().getUserId());
				updateAppointment.setString(3, appoinment.getCategory());
				updateAppointment.setString(4, appoinment.getTime());
				updateAppointment.setString(5, appoinment.getDate().toString());
				updateAppointment.setInt(6, appoinment.getAppointmentId());
				updateAppointment.executeUpdate();
				updateAppointment.close();
			}
			catch(Exception e)
			{
				logError("Cannot update the appointment. Check that inputs are correct and the datbase has been set up properly.");
				logError(e.getMessage());
			}
		}
		else
		{
			logError("Cannot update appoinment as there is no current database connection.");
		}
	}
	
	/**
	 * Updates a condition that already exists in the database
	 * @param condition condition to be updated
	 */
	public void updateHealthCondition(HealthCondition condition) {
		if(dbConnection != null)
		{
			try
			{
				PreparedStatement updateAppointment = dbConnection.prepareStatement("UPDATE HealthCondition SET userId = ?, healthConcerns = ?, comments = ?, severity = ?, isCurrent = ? WHERE id = ?");
				updateAppointment.setInt(1, condition.getPatientId());
				updateAppointment.setString(2, condition.getHealthConcern());
				updateAppointment.setString(3, condition.getComments());
				updateAppointment.setInt(4, condition.getSeverity());
				updateAppointment.setInt(5, condition.isCurrent() ? 1 : 0);
				updateAppointment.setInt(6, condition.getHealthConditionId());
				updateAppointment.executeUpdate();
				updateAppointment.close();
			}
			catch(Exception e)
			{
				logError("Cannot update the Health Condition. Check that inputs are correct and the datbase has been set up properly.");
				logError(e.getMessage());
			}
		}
		else
		{
			logError("Cannot update health condition as there is no current database connection.");
		}
	}
	
	/**
	 * Updates a lab record that already exists in the database.
	 * @param record lab record that should be updated
	 */
	public void updateLabRecord(LabRecord record)
	{
		try
		{
			if(record.getLabRecordId() != -1)
			{
				PreparedStatement updateRecord = dbConnection.prepareStatement("Update LabRecord SET userId = ?, glucose = ?, sodium = ?, calcium = ?, magnesium = ? WHERE recordId = ?");
				updateRecord.setInt(1, record.getPatientId());
				updateRecord.setFloat(2, record.getGlucose());
				updateRecord.setFloat(3, record.getSodium());
				updateRecord.setFloat(4, record.getCalcium());
				updateRecord.setFloat(5, record.getMagnesium());
				updateRecord.setInt(6, record.getLabRecordId());
				
				updateRecord.executeUpdate();
			}
			else
			{
				logError("Cannot update a lab record without an id.");
			}
		}
		catch(Exception e)
		{
			logError("Could not update given lab record. Please check that the database has been properly set up.");
			logError(e.getMessage());
		}
	}
	
	/**
	 * Deletes an appointment from the database. This is useful to get rid of old or cancelled appointments.
	 * @param appoinment the appointment that should be deleted
	 */
	public void deleteAppoinment(Appointment appoinment) {
		try
		{
			System.out.println("Deleting appoinment with id " + appoinment.getAppointmentId() + " ****");
			PreparedStatement delApp = dbConnection.prepareStatement("DELETE FROM Appointments WHERE id = ?");
			delApp.setInt(1, appoinment.getAppointmentId());
			delApp.executeUpdate();
			delApp.close();


		}
		catch(Exception e)
		{
			logError("Could not delete the appoinment");
			logError(e.getMessage());
		}

	}
	
	/**
	 * Deletes a patient's condition from the database.
	 * @param condition condition that should be deleted from the database.
	 */
	public void deleteHealthCondition(HealthCondition condition) {
		try
		{
			System.out.println("Deleting Condition with id " + condition.getHealthConditionId() + " ****");
			PreparedStatement stat = dbConnection.prepareStatement("DELETE FROM HealthCondition WHERE id = ?");
			stat.setInt(1, condition.getHealthConditionId());
			stat.executeUpdate();
			stat.close();


		}
		catch(Exception e)
		{
			logError("Could not delete the condition");
			logError(e.getMessage());
		}

	}
	
	/**
	 * Deletes a lab record from the database.
	 * @param labrecord record that should be deleted.
	 */
	public void deleteLabRecord(LabRecord labrecord) {
		try
		{
			System.out.println("Deleting Lab Record with id " + labrecord.getLabRecordId() + " ****");
			PreparedStatement stat = dbConnection.prepareStatement("DELETE FROM LabRecord WHERE recordId = ?");
			stat.setInt(1, labrecord.getLabRecordId());
			stat.executeUpdate();
			stat.close();


		}
		catch(Exception e)
		{
			logError("Could not delete the Lab Record");
			logError(e.getMessage());
		}

	}

	/**
	 * Marks an alert as read
	 * @param alertId id of the alert to mark as read
	 */
	public void markAlertRead(int alertId)
	{
		try
		{
			PreparedStatement markRead = dbConnection.prepareStatement("UPDATE Alert SET read=1 WHERE alertId = ? AND read=0");
			markRead.setInt(1, alertId);
			
			markRead.executeUpdate();
			markRead.close();
		}
		catch(Exception e)
		{
			logError("Could not mark alert read. Check that the inputs are correct and the database has been set up properly.");
			logError(e.getMessage());
		}
	}
	
	//=================== DB Helpers ==================

	/**
	 * Creates a user from a ResultSet. This is useful when many users need to be created, i.e. when getting a list of users.
	 * @param rs ResultSet containing the information about a user or users
	 * @return Returns a user's information
	 * @throws SQLException
	 */
	private User createUser(ResultSet rs) throws SQLException {
		System.out.println("Found user " + rs.getString("name") + " " + rs.getInt("type"));
		User user = User.createUser(UserType.fromInteger( rs.getInt("type")));
		user.setName(rs.getString("name"));
		user.setUserName(rs.getString("userName"));
		user.setUserId(rs.getInt("userId"));
		user.setPasswordHash(rs.getString("passwordHash"));
		user.setSsn(rs.getString("ssn"));
		user.setDateOfBirth(rs.getString("dob"));
		user.setAddress(rs.getString("address"));
		user.setEmail(rs.getString("email"));
		user.setPhoneNumber(rs.getString("phoneNumber"));
		user.setInsurance(rs.getString("insurance"));
		user.setSex(rs.getString("sex"));
		user.setRace(rs.getString("race"));
		return user;
	}

	/**
	 * Creates an appointment from a ResultSet. This is useful when many appointments need to be created, i.e. listing appointments for a user.
	 * @param rs ResultSet containing the information about an appointment or appointments.
	 * @return Returns the appointment's information
	 * @throws SQLException
	 */
	private Appointment createAppoinment(ResultSet rs) throws SQLException {
		System.out.println("Found Appoinment");
		int appoinmentId = rs.getInt("id");
		int patientId = rs.getInt("patientId");
		int doctorId = rs.getInt("doctorId");
		String category = rs.getString("category");
		String time = rs.getString("time");
		String date = rs.getString("date");
		Patient patient = (Patient)getUser(patientId);
		Doctor doc = (Doctor)getUser(doctorId);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate localdate = LocalDate.parse(date, formatter);
		return new Appointment(appoinmentId, localdate, time, doc, patient, category);

	}

}
