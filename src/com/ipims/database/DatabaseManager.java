package com.ipims.database;

import java.sql.*;
import java.nio.file.*;
import java.nio.charset.*;
import java.io.BufferedWriter;

public class DatabaseManager {
	private static final DatabaseManager INSTANCE = new DatabaseManager();
	private static Connection dbConnection;
	
	private DatabaseManager()
	{
		try{
			Class.forName("org.sqlite.JDBC");
			dbConnection = DriverManager.getConnection("jdbc:sqlite:ipims.db");
		}
		catch(Exception e)
		{
			Charset charset = Charset.forName("UTF-8");
			Path path = FileSystems.getDefault().getPath("logs", "error.log");
			try{
				BufferedWriter writer = Files.newBufferedWriter(path, charset);
				String errMessage = "Could not open database file. Please check that file permissions are set correctly.";
				writer.write(errMessage);
				writer.newLine();
				writer.write(e.getMessage());
				writer.newLine();
				writer.close();
			}
			catch(Exception j)
			{
				System.err.println(String.format("Could not write to log file. Please check that file permissions are set correctly.%n") + e.getMessage() + "%n");
			}
		}
	}
	
	public static DatabaseManager getInstance()
	{
		return INSTANCE;
	}
	
	public static void newPatient()
	{
		
	}
}
