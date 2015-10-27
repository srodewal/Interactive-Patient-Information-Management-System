package com.ipims.database;

import java.sql.*;
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
					createAppointment.executeUpdate("CREATE TABLE Appointment( patientId INTEGER PRIMARY KEY NOT NULL,"
							+ "doctorId INT NOT NULL, "
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
							+ "userId INTEGER PRIMARY KEY NOT NULL,"
							+ "healthConcerns TEXT NOT NULL,"
							+ "comments TEXT NOT NULL,"
							+ "severity INTEGER NOT NULL,"
							+ "pastOrCurrent INTEGER NOT NULL"
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
							+ "userId INTEGER PRIMARY KEY NOT NULL,"
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
							+ "userId INTEGER PRIMARY KEY NOT NULL,"
							+ "date TEXT NOT NULL,"
							+ "medicine TEXT NOT NULL"
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
				System.out.println("Trying to create user");
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
	
	public  void newAppointment(int patientId, int doctorId, String time)
	{
		//TODO: match model
		if(dbConnection != null)
		{
			try
			{
				PreparedStatement insertAppointment = dbConnection.prepareStatement("INSERT INTO Appointment VALUES (?,?,?)");
				insertAppointment.setInt(1, patientId);
				insertAppointment.setInt(2, doctorId);
				insertAppointment.setString(3, time);
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
	
	public void newHealthCondition(HealthCondition condition)
	{
		if(dbConnection != null)
		{
			try
			{
				PreparedStatement insertHealthCondition = dbConnection.prepareStatement("INSERT INTO HealthCondition VALUES(?, ?, ?, ?, ?)");
				insertHealthCondition.setInt(1, condition.getHealthConditionId());
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
		
		//TODO: complete once I'm sure about what the field in the model represent
//		try
//		{
//			PreparedStatement getAppointments = dbConnection.prepareStatement("SELECT * FROM Appointments WHERE patientId = ?");
//			getAppointments.setInt(1, patient.getUserId());
//			
//			ResultSet rs = getAppointments.executeQuery();
//			
//			while(rs.next())
//			{
//				Appointment appointment = new Appointment();
//			}
//		}
//		catch(Exception e)
//		{
//			logError("Could not get appointments. Please check the database has been set up correctly.");
//			logError(e.getMessage());
//		}
		
		return appointmentList;
	}
	
	public List<Doctor> getAllDoctors()
	{
		List<Doctor> doctors = new ArrayList<>();
		
		try
		{
			Statement getDoctors = dbConnection.createStatement();
			ResultSet rs = getDoctors.executeQuery("SELECT * FROM User JOIN DoctorCategory ON User.userId = DoctorCategory.userId;");
			
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
	
	public List<HealthCondition> getPatientConditions(int patientId)
	{
		List<HealthCondition> conditions = new ArrayList<>();
		//TODO: complete once I know what the fields in the model represent.
//		try
//		{
//			PreparedStatement getPatients = dbConnection.prepareStatement("SELECT * FROM HealthConditions WHERE userId = ?");
//			getPatients.setInt(1, patientId);
//			
//			ResultSet rs = getPatients.executeQuery();
//			
//			while(rs.next())
//			{
//				HealthCondition condition = new HealthCondition();
//				condition.set
//			}
//		}
//		catch(Exception e)
//		{
//			logError("Could not get patient conditions. Please check that the database has been set up properly.");
//			logError(e.getMessage());
//		}
		
		return conditions;
	}
	
	//=================== DB Helpers ==================
	
	private User createUser(ResultSet rs) throws SQLException {
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
	
}
