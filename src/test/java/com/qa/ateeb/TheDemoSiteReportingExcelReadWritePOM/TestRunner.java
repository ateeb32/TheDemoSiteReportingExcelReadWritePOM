package com.qa.ateeb.TheDemoSiteReportingExcelReadWritePOM;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class TestRunner {
	
	public static void main(String[] args) {
		
		Result resulttt = JUnitCore.runClasses(TestSuite.class);
		
		for (Failure failureee : resulttt.getFailures()) {
			
			System.out.println(failureee.getMessage());
			
		}
		
		System.out.println(resulttt.wasSuccessful());
		
	}

}
