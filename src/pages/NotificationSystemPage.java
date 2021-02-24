package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class NotificationSystemPage extends BasicPage {

	public NotificationSystemPage(WebDriver driver, WebDriverWait wait, JavascriptExecutor js) {
		super(driver, wait, js);
	}
	public WebElement getShownMessage() {
		return this.driver.findElement(By.xpath("//*[contains(@class, 'alert--success') or contains(@class, 'alert--danger')][contains(@style,'display: block')]"));
	}
	public String returnMessage() {
		String msg = getShownMessage().getText();
		return msg;
	}
	public void disapearMessage() {
		wait.until(ExpectedConditions.presenceOfElementLocated(
				By.xpath("//*[contains(@class, 'system_message')][contains(@style, 'display: none;')]")));

	}
	}
