package com.demoblaze.pages;

import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CartPage {
	WebDriver driver;
	WebDriverWait wait;
	By tableElements = By.xpath("//tbody[@id='tbodyid']/descendant::td");
	By placeOrder = By.xpath("//button[text()='Place Order']");
	By name = By.id("name");
	By country = By.id("country");
	By city = By.id("city");
	By card = By.id("card");
	By month = By.id("month");
	By year = By.id("year");
	By purchaseButton = By.xpath("//button[text()='Purchase']");
	By purchaseMessage = By.cssSelector("div.sweet-alert h2");
	
	 public CartPage(WebDriver driver) {
	        this.driver = driver;
	    }

	public void verifyProductInCart(String productName) {
		List<WebElement> products = driver.findElements(tableElements);
		for (WebElement w : products) {
			if (w.getText().equalsIgnoreCase(productName)) {
				System.out.println("Samsung Galaxy S6 is present in the cart.");
			} else {
				System.out.println("Samsung Galaxy S6 is NOT present in the cart.");
			}

		}
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

	public void clickPlaceOrder() {
		driver.findElement(placeOrder).click();
	}
	
	public void fillPurchaseDetails() {
		driver.findElement(name).sendKeys("John");
		driver.findElement(country).sendKeys("India");
		driver.findElement(city).sendKeys("TVM");
		driver.findElement(card).sendKeys("1122 2255 2222");
		driver.findElement(month).sendKeys("10");
		driver.findElement(year).sendKeys("2030");
	}
	public void clickPurchase() {
		driver.findElement(purchaseButton).click();
	}
	public String getPurchaseConfirmation() {
		String confirmationMessage = driver.findElement(purchaseMessage).getText();
		driver.findElement(By.className("sa-confirm-button-container")).click();
		return confirmationMessage;
	}
	
	

}
