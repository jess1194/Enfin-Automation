package com.demoblaze.pages;
import java.time.Duration;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {

	    WebDriver driver;
	    WebDriverWait wait;
	    By login = By.id("login2");
	    By username = By.id("loginusername");
		By password = By.id("loginpassword");
		By loginButton = By.cssSelector("button[onclick='logIn()']");
		By nameOfUser = By.id("nameofuser");
		
	    
	    public LoginPage(WebDriver driver, WebDriverWait wait) {
	        this.driver = driver;
	        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5)); 
	        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
	    }
	    
	    public void loginUser(String user, String pass) {

			driver.findElement(username).sendKeys(user);
			driver.findElement(password).sendKeys(pass);
			driver.findElement(loginButton).click();
		}
	    public String getLoggedUsername() {
	    	String loggedUserName = driver.findElement(nameOfUser).getText();
			return loggedUserName;
	    	
	    }
	}
