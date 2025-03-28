package com.demoblaze.utils;

import com.relevantcodes.extentreports.ExtentReports;

public class ExtentReportManager {
	private static ExtentReports extent;

	public static ExtentReports getInstance() {
		if (extent == null) {
			extent = new ExtentReports("test-output/ExtentReport.html", true);
		}
		return extent;
	}
}




