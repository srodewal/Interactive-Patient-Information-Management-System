package com.ipims.models;

import java.time.LocalDate;

public class LabRecord {
	
	private String testResult;
	private LocalDate testDate;
	
	public String getTestResult()
	{
		return testResult;
	}
	
	public void setTestResult(String testResult)
	{
		this.testResult = testResult;
	}
	
	public LocalDate getTestDate()
	{
		return testDate;
	}
	
	public void setTestDate(LocalDate testDate)
	{
		this.testDate = testDate;
	}
}
