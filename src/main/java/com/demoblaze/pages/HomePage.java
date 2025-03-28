package com.demoblaze.pages;
import java.time.Duration;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {

	WebDriver driver;
	WebDriverWait wait;
	By signUp = By.id("signin2");
	By login = By.id("login2");
	By logout = By.id("logout2");
	By cart = By.id("cartur");
	By username = By.id("sign-username");
	By password = By.id("sign-password");
	By signUpButton = By.xpath("//button[text()='Sign up']");
	By loginButton = By.cssSelector("button[onclick='logIn()']");
	By nameOfUser = By.id("nameofuser");
	String productXpath = "//a[text()='{}']";



	public HomePage(WebDriver driver, WebDriverWait wait) {
		this.driver = driver;
		// driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
	}

	public void navigateToSignUp() {
		driver.findElement(signUp).click();
	}

	public void registerUser(String user, String pass) {

		driver.findElement(username).sendKeys(user);
		driver.findElement(password).sendKeys(pass);
		driver.findElement(signUpButton).click();
	}

	public String getAlertMessage() {

		try {
			System.out.println("Waiting for alert...");
			Thread.sleep(1000);// 
			Alert alert = driver.switchTo().alert();
			String alertText = alert.getText();
			System.out.println("Alert found: " + alertText);
			alert.accept();
			return alertText;
		} catch (Exception e) {
			System.out.println("No alert found. Exception: " + e.getMessage());
			return "No alert present";
		}
	}


	public void navigateToLogin() {
		System.out.println("Login clicked");
		driver.findElement(login).click();
	}	


	public void navigateToCart() {
		driver.findElement(cart).click();
	}

	public void logout() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement logoutButton = wait.until(ExpectedConditions.elementToBeClickable(logout));
		logoutButton.click();
		System.out.println("Logged out");
	}

	public boolean verifyLogout() {
		return driver.findElement(login).isDisplayed();
	}
	public void selectProduct(String productName) {
		String updatedProductXpath = productXpath.replace("{}", productName);
		System.out.println("product xpath: "+updatedProductXpath);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement product = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(updatedProductXpath)));
		product.click();
	}
}
