package com.demoblaze.pages;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProductPage {
	WebDriver driver;
	WebDriverWait wait;
	By addToCart = By.xpath("//a[text()='Add to cart']");
	
	 public ProductPage(WebDriver driver) {
	        this.driver = driver;
	    }

	public void clickAddToCart() {
		driver.findElement(addToCart).click();
	}
	public String verifyConfirmation() {
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
}
