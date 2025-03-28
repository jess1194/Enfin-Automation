package com.demoblaze.utils;

import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;


public class ScreenshotUtility {
	WebDriver driver;

	public ScreenshotUtility(WebDriver driver) {
		this.driver = driver;
	}

	public String captureScreenshot(String testName) throws IOException {
		File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String path = "screenshots/" + testName + ".png";
		FileUtils.copyFile(src, new File(path));
		return path;
	}
}