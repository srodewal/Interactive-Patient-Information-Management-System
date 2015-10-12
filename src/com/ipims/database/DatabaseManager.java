package com.ipims.database;

import java.sql.*;
import java.nio.file.*;
import java.nio.charset.*;
import java.io.BufferedWriter;
import java.util.HashSet;
import com.ipims.models.Patient;
import com.ipims.models.HealthCondition;

public class DatabaseManager {
	private static final DatabaseManager INSTANCE = new DatabaseManager();
	private static Connection dbConnection = null;
	private static BufferedWriter writer = null;
	private static final int NUM_TABLES = 4;
	
	
	private DatabaseManager()
	{
		try
		{
			Charset charset = Charset.forName("UTF-8");
			Path path = FileSystems.getDefault().getPath("logs", "error.log");
			writer = Files.newBufferedWriter(path, charset, StandardOpenOption.APPEND);
		}
		catch(Exception e)
		{
			System.err.println(String.format("Could not write to log file. Please check that file permissions are set correctly.%n") + e.getMessage() + "%n");
		}

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
	
	private static void checkTables()
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
	
	private static void fixTables(HashSet<String> existingTables)
	{
		if(dbConnection != null)
		{
			if(!existingTables.contains("User"))
			{
				try
				{
					Statement createUser = dbConnection.createStatement();
					createUser.executeUpdate("CREATE TABLE User("
							+ "userId INT PRIMARY KEY AUTOINCREMENT,"
							+ "name TEXT NOT NULL,"
							+ "userName TEXT NOT NULL,"
							+ "passwordHash TEXT NOT NULL,"
							+ "ssn TEXT NOT NULL,"
							+ "type INT NOT NULL,"
							+ "dob TEXT NOT NULL"
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
					createDoctorCategory.executeUpdate("CREATE TABLE DoctorCategory( userId INT PRIMARY KEY NOT NULL, categoryId INT NOT NULL )");
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
					createAppointment.executeUpdate("CREATE TABLE Appointment( patientId INT PRIMARY KEY NOT NULL, doctorId INT NOT NULL, time TEXT NOT NULL )");
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
							+ "userId INT PRIMARY KEY NOT NULL,"
							+ "healthConcerns TEXT NOT NULL,"
							+ "comments TEXT NOT NULL,"
							+ "severity INT NOT NULL,"
							+ "pastOrCurrent INT NOT NULL"
							+ ")");
					
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
	
	private static void logError(String errMessage)
	{
		if(writer != null)
		{
			try
			{
				writer.write(errMessage);
				writer.newLine();
			}
			catch(Exception e)
			{
				System.err.println(String.format("Could not write to log file. Please check that file permissions are set correctly.%n") + e.getMessage() + "%n");
				writer = null;
			}
		}
	}
	
	public static DatabaseManager getInstance()
	{
		return INSTANCE;
	}
	
	public static void newPatient(Patient patient)
	{
		if(dbConnection != null)
		{
			try
			{
				PreparedStatement insertPatient = dbConnection.prepareStatement("INSERT INTO User (name, userName, passwordHash, ssn, type, dob) VALUES (?, ?, ?, ?, ?, ?, ?)");
				insertPatient.setString(1, patient.getName());
				insertPatient.setString(2, patient.getUserName());
				insertPatient.setString(3, patient.getPasswordHash());
				insertPatient.setString(4, patient.getSsn());
				insertPatient.setInt(5, patient.getUsertype().ordinal());
				insertPatient.setString(6, patient.getDateOfBirth());
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
	
	public static void newAppointment(int patientId, int doctorId, String time)
	{
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
	
	public static void newHealthCondition(HealthCondition condition)
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
	
	public static void close()
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
		if(writer != null)
		{
			try
			{
				writer.close();
			}
			catch(Exception e)
			{
				System.err.println(String.format("Could not write to log file. Please check that file permissions are set correctly.%n") + e.getMessage() + "%n");
			}
		}
	}
}
