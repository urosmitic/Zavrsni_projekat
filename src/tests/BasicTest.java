package tests;

import java.awt.AWTException;
import java.awt.HeadlessException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;

import pages.AuthPage;
import pages.CartSummaryPage;
import pages.LocationPopupPage;
import pages.LoginPage;
import pages.MealPage;
import pages.NotificationSystemPage;
import pages.ProfilePage;
import pages.SearchResultPage;

public abstract class BasicTest {
	
	protected JavascriptExecutor js;
	protected WebDriver driver;
	protected WebDriverWait waiter;
	protected LocationPopupPage location;
	protected LoginPage login;
	protected NotificationSystemPage notification;
	protected ProfilePage profile;
	protected AuthPage auth;
	protected MealPage meal;
	protected CartSummaryPage cart;
	protected SearchResultPage search;
	
	protected String baseURL = "http://demo.yo-meals.com";
	protected String demoEmail = "customer@dummyid.com";
	protected String demoPass = "12345678a";

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", "driver-lib\\chromedriver.exe");
		this.driver = new ChromeDriver();
		this.js = (JavascriptExecutor) driver;
		this.waiter = new WebDriverWait(driver, 30);
		this.location = new LocationPopupPage(driver , waiter , js);
		this.login = new LoginPage(driver , waiter , js);
		this.notification = new NotificationSystemPage(driver , waiter , js);
		this.profile = new ProfilePage (driver , waiter , js );
		this.auth = new AuthPage(driver , waiter , js);
		this.meal = new MealPage(driver , waiter , js);
		this.cart = new CartSummaryPage(driver , waiter , js);
        this.search = new SearchResultPage (driver , waiter , js);
		this.driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
		this.driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		this.driver.manage().window().maximize();
	}
	@AfterMethod
	
	public void takeScreenshot(ITestResult result) throws HeadlessException, AWTException, IOException {
		String testTime = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss'.jpg'").format(new Date());
		if (ITestResult.FAILURE == result.getStatus()) {
		BufferedImage screenshoot = new Robot()
		.createScreenCapture((new Rectangle(Toolkit.getDefaultToolkit().getScreenSize())));
		File screenshot = new File("screenshot.jpg");
		ImageIO.write(screenshoot, "jpg", new File("screenshots\\" + testTime));
	}
	this.driver.manage().deleteAllCookies();
	this.driver.navigate().refresh();

}

@AfterClass
public void afterClass() {
	this.driver.quit();
}
}


