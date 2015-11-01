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

public class DatabaseManager {
	private static final DatabaseManager INSTANCE = new DatabaseManager();

	private Connection dbConnection = null;

	private static final int NUM_TABLES = 6;


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

	private  void checkTables()
	{
		if(dbConnection != null)
		{
			HashSet<String> existingTables = new HashSet<>(NUM_TABLES);
			try
			{
				DatabaseMetaData dbMeta = dbConnection.getMetaData();
				ResultSet tables = dbMeta.getTables(null, null, "%", null);
				while(tables.next())
				{
					existingTables.add(tables.getString(3));
				}

				if(existingTables.size() != NUM_TABLES)
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

	private  void fixTables(HashSet<String> existingTables)
	{
		if(dbConnection != null)
		{
			if(!existingTables.contains("User"))
			{
				try
				{
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
					Statement createLabRecord = dbConnection.createStatement();
					createLabRecord.executeUpdate("CREATE TABLE LabRecord("
							+ "recordId INTEGER PRIMARY KEY NOT NULL,"
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
					Statement createPrescription = dbConnection.createStatement();
					createPrescription.executeUpdate("CREATE TABLE Prescription("
							+ "prescriptionId INTEGER PRIMARY KEY NOT NULL,"
							+ "userId INTEGER NOT NULL,"
							+ "date TEXT NOT NULL,"
							+ "medicine TEXT NOT NULL,"
							+ "pastOrCurrent INTEGER NOT NULL"
							+ ")");
					createPrescription.close();
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

	private void logError(String errMessage)
	{

		Logger.getGlobal().log(Level.WARNING, errMessage);
	}

	public static DatabaseManager getInstance()
	{
		return INSTANCE;
	}

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

	public  void newUser(User user, String password)
	{
		if(dbConnection != null)
		{
			try
			{
				System.out.println("Trying to create user " + user.getName() + " " + user.getUsertype().ordinal());
				PreparedStatement insertPatient = dbConnection.prepareStatement("INSERT INTO User (name, userName, passwordHash, ssn, type, dob, address, email, phoneNumber, insurance, sex, race) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
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
				insertPatient.executeUpdate();
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
	}

	public  void newAppointment(Appointment appoinment)
	{
		if(dbConnection != null)
		{
			try
			{
				PreparedStatement insertAppointment = dbConnection.prepareStatement("INSERT INTO Appointments (patientId, doctorId, category, time, date) VALUES (?,?,?,?,?)");
				insertAppointment.setInt(1, appoinment.getPatient().getUserId());
				if (appoinment.getDoctor() != null) {
					insertAppointment.setInt(2, appoinment.getDoctor().getUserId());
				} else {
					insertAppointment.setNull(2, java.sql.Types.INTEGER);
				}
				
				insertAppointment.setString(3, appoinment.getCategory());
				insertAppointment.setString(4, appoinment.getTime());
				insertAppointment.setString(5, appoinment.getDate().toString());
				insertAppointment.executeUpdate();
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
	}

	public void updateAppoinemt(Appointment oldAppoinment, Appointment updatedAppoinment) {
		if(dbConnection != null)
		{
			try
			{
				PreparedStatement updateAppointment = dbConnection.prepareStatement("UPDATE Appointments SET patientId = ?, doctorId = ?, category = ?, time = ?, date = ? WHERE id = ?");
				updateAppointment.setInt(1, updatedAppoinment.getPatient().getUserId());
				updateAppointment.setInt(2, updatedAppoinment.getDoctor().getUserId());
				updateAppointment.setString(3, updatedAppoinment.getCategory());
				updateAppointment.setString(4, updatedAppoinment.getTime());
				updateAppointment.setString(5, updatedAppoinment.getDate().toString());
				updateAppointment.setInt(6, oldAppoinment.getAppointmentId());
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
	
	
	public void updateHealthCondition(HealthCondition oldCond, HealthCondition updatedCond) {
		if(dbConnection != null)
		{
			try
			{
				PreparedStatement updateAppointment = dbConnection.prepareStatement("UPDATE HealthCondition SET userId = ?, healthConcerns = ?, comments = ?, severity = ?, isCurrent = ? WHERE id = ?");
				updateAppointment.setInt(1, updatedCond.getPatientId());
				updateAppointment.setString(2, updatedCond.getHealthConcern());
				updateAppointment.setString(3, updatedCond.getComments());
				updateAppointment.setInt(4, updatedCond.getSeverity());
				updateAppointment.setInt(5, updatedCond.isCurrent()?1:0);
				updateAppointment.setInt(6, oldCond.getHealthConditionId());
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

	public void newHealthCondition(HealthCondition condition)
	{
		if(dbConnection != null)
		{
			try
			{
				PreparedStatement insertHealthCondition = dbConnection.prepareStatement("INSERT INTO HealthCondition (userId,healthConcerns,comments,severity,isCurrent) VALUES(?, ?, ?, ?, ?)");
				insertHealthCondition.setInt(1, condition.getPatientId());
				insertHealthCondition.setString(2, condition.getHealthConcern());
				insertHealthCondition.setString(3, condition.getComments());
				insertHealthCondition.setInt(4, condition.getSeverity());
				if(condition.isCurrent())
				{
					insertHealthCondition.setInt(5, 1);
				}
				else
				{
					insertHealthCondition.setInt(5, 0);
				}
				insertHealthCondition.executeUpdate();
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
	}

	public void newLabRecord(LabRecord record)
	{
		try
		{
			PreparedStatement insertRecord = dbConnection.prepareStatement("INSERT INTO LabRecord (userId, glucose, calcium, magnesium, sodium) VALUES (?, ?, ?, ?, ?");
			insertRecord.setInt(1, record.getPatientId());
			insertRecord.setFloat(2, record.getGlucose());
			insertRecord.setFloat(3, record.getCalcium());
			insertRecord.setFloat(4, record.getMagnesium());
			insertRecord.setFloat(5, record.getSodium());

			insertRecord.executeUpdate();
		}
		catch(Exception e)
		{
			logError("Could not add lab record to the database. Please check that the database has been set up properly.");
			logError(e.getMessage());
		}
	}

	public void newPrescription(Prescription prescription)
	{
		try
		{
			System.out.println("p1");
			// this statement ->
			PreparedStatement insertPrescription = dbConnection.prepareStatement("INSERT INTO Prescription (userId, date, medicine, pastOrCurrent) VALUES (?, ?, ?, ?)");
			System.out.println("p2");
			System.out.println("Setup " + prescription.getUserId() + "\n");
			insertPrescription.setInt(1, prescription.getUserId());
			insertPrescription.setString(2, prescription.getDate());
			insertPrescription.setString(3, prescription.getPrescriptionText());
			if(prescription.isCurrent())
			{
				insertPrescription.setInt(4, 1);
			}
			else
			{
				insertPrescription.setInt(4, 0);
			}

			insertPrescription.executeUpdate();
		}
		catch(Exception e)
		{
			logError("Could not add prescription the the database. Please check that the database has been set up properly.");
			logError(e.getMessage());
		}
	}

	public User getUser(String userName, String password) { //for login
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

	public User getUser(int userId) //for normal use
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

	public User getUser(String userName) //for normal use
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

	public List<Doctor> getAllDoctors()
	{
		List<Doctor> doctors = new ArrayList<>();

		try
		{
			Statement getDoctors = dbConnection.createStatement();
			ResultSet rs = getDoctors.executeQuery("SELECT * FROM User LEFT OUTER JOIN DoctorCategory ON User.userId = DoctorCategory.userId AND User.type=0");

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
				//patient.setCategory(rs.getString("username"));

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

	// get all lab records for specified patient
	public List<LabRecord> getLabRecordsForPatient(int patientId)
	{
		ArrayList<LabRecord> records = new ArrayList<>();
		try
		{
			/*PreparedStatement getPatients = dbConnection.prepareStatement("SELECT * FROM User WHERE type = ?");
			getPatients.setInt(1, UserType.PATIENT.ordinal());
			ResultSet rs = getPatients.executeQuery();*/
			
			PreparedStatement getRecords = dbConnection.prepareStatement("SELECT * FROM LabRecord WHERE userId = ?");		
			//ResultSet rs = getRecords.executeQuery("SELECT * FROM LabRecord WHERE userId = ?");
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

	// get all lab records for every patient
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
	//=================== DB Helpers ==================

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
