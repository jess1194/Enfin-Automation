package demoblaze.testsuit;

import java.io.IOException;
import java.lang.reflect.Method;
import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.demoblaze.pages.CartPage;
import com.demoblaze.pages.HomePage;
import com.demoblaze.pages.LoginPage;
import com.demoblaze.pages.ProductPage;
import com.demoblaze.utils.ExtentReportManager;
import com.demoblaze.utils.LoggerUtility;
import com.demoblaze.utils.ScreenshotUtility;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class DemoBlazeTests {

	WebDriver driver;

	HomePage homePage;
	LoginPage loginPage;
	ProductPage productPage;
	CartPage cartPage;
	WebDriverWait wait;
	LoggerUtility logger;
	ScreenshotUtility screenshotUtility;
	ExtentReports extent;
	ExtentTest test;
	SoftAssert softAssert = new SoftAssert();
	

	@BeforeClass
	public void setup() {
		System.setProperty("webdriver.chrome.driver", "src\\test\\resources\\Browser Drivers\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.demoblaze.com/");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		logger = new LoggerUtility();
		screenshotUtility = new ScreenshotUtility(driver);
		extent = ExtentReportManager.getInstance();
		logger.info("Test Execution Started");

		homePage = new HomePage(driver, wait);

		loginPage = new LoginPage(driver, wait);
		productPage = new ProductPage(driver);
		cartPage = new CartPage(driver);
		
		 
	}

	@BeforeMethod
	public void startTest(Method method) {
		test = extent.startTest(method.getName());
	}

	@Test(priority = 1)
	public void testUserRegistration() throws InterruptedException {
		homePage.navigateToSignUp();
		homePage.registerUser("Jesvinel120", "Jesvinel120");
		System.out.println("Alert Message:" + homePage.getAlertMessage());
		softAssert.assertEquals(homePage.getAlertMessage(), " Sign up successful.", "Registration failed");
		Thread.sleep(1000);
	}

	@Test(priority = 2)
	public void testLogin() throws InterruptedException {
		homePage.navigateToLogin();
		loginPage.loginUser("Jesvinel120", "Jesvinel120");
		softAssert.assertEquals(loginPage.getLoggedUsername(), "Welcome " + "Jesvinel120");
		Thread.sleep(2000);
	}

	@Test(priority = 3)
	public void testProductSelection() {
		homePage.selectProduct("Samsung galaxy s6");
		productPage.clickAddToCart();
		softAssert.assertEquals(productPage.verifyConfirmation(), "Product added.", "Failed");
	}

	@Test(priority = 4)

	public void testCartVerification() {
		homePage.navigateToCart();
		cartPage.verifyProductInCart("Samsung galaxy s6");
	}

	@Test(priority = 5)
	public void testCheckout() throws InterruptedException {
		cartPage.clickPlaceOrder();
		cartPage.fillPurchaseDetails();
		cartPage.clickPurchase();
		Assert.assertEquals(cartPage.getPurchaseConfirmation(), "Thank you for your purchase!",
				"Confirmation message verification failed");
		Thread.sleep(2000);
		
	}

	@Test(priority = 6)
	public void testLogout() {
		homePage.logout();
		
	}

	@AfterMethod
	public void captureFailure(ITestResult result) {
		if (test == null) {
			test = extent.startTest(result.getName()); // Ensure `test` is initialized
		}

		if (ITestResult.FAILURE == result.getStatus()) {
			try {
				String screenshotPath = screenshotUtility.captureScreenshot(result.getName());
				test.log(LogStatus.FAIL, "Test Failed: " + result.getThrowable());
				test.log(LogStatus.FAIL, test.addScreenCapture(screenshotPath));
				logger.error("Test Failed: " + result.getName());
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			test.log(LogStatus.PASS, "Test Passed: " + result.getName());
		}

		extent.endTest(test);
	}

	@AfterClass
	public void teardown() {
		driver.quit();
		// logger.info("Test Execution Completed");
	}

}
