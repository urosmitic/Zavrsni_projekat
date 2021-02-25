package pages;

import java.io.File;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProfilePage extends BasicPage {

	public ProfilePage(WebDriver driver, WebDriverWait wait, JavascriptExecutor js) {
		super(driver, wait, js);
	}
	public WebElement getFirstName() {
		return this.driver.findElement(By.name("user_first_name"));
	}
	public WebElement getLastName() {
		return this.driver.findElement(By.name("user_last_name"));
	}
	public WebElement getAddress() {
		return this.driver.findElement(By.name("user_address"));
	}
	public WebElement getPhone() {
		return this.driver.findElement(By.name("user_phone"));
	}
	public WebElement getZipCode() {
		return this.driver.findElement(By.name("user_zip"));
	}
	public Select getCountry() {
		WebElement country = this.driver.findElement(By.name("user_country_id"));
		Select se1 = new Select(country);
		return se1;
	}
	public Select getState() {
		WebElement state = this.driver.findElement(By.name("user_state_id"));
		Select se2 = new Select(state);
		return se2;
	}
	public Select getCity() {
		WebElement city = this.driver.findElement(By.name("user_city"));
		Select se3 = new Select(city);
		return se3;
	}
	public WebElement getSaveBtn() {
		return this.driver.findElement(By.xpath("//*[@id='profileInfoFrm']/div[5]/div/fieldset/input"));
	}
	public WebElement getUploadImgBtn() {
		return this.driver.findElement(By.xpath("//*[@id=\"profileInfo\"]/div/div[1]/div/a"));
	}
	public void uploadImage() throws Exception {
		js.executeScript("arguments[0].click();", this.getUploadImgBtn());
		WebElement uploadImg = this.driver.findElement(By.xpath("//input[@name = 'file']"));
		String imgPath = new File("images/uros.jpg").getCanonicalPath();
		uploadImg.sendKeys(imgPath); 
	}
	public WebElement getDeleteImgBtn() {
		return this.driver.findElement(By.xpath("//*[@id='profileInfo']/div/div[1]/div/a[2]/i"));
	}

    public void deleteImage() throws Exception {
		js.executeScript("arguments[0].click();", this.getDeleteImgBtn());
   }
    public void updateProfile(String firstName, String lastName, String address, String phone, String zipCode,
			String country, String state, String city) throws Exception {
		this.getFirstName().clear();
		this.getLastName().clear();
		this.getAddress().clear();
		this.getPhone().clear();
		this.getZipCode().clear();

		this.getFirstName().sendKeys(firstName);
		this.getLastName().sendKeys(lastName);
		this.getAddress().sendKeys(address);
		this.getPhone().sendKeys(phone);
		this.getZipCode().sendKeys(zipCode);
		this.getCountry().selectByVisibleText(country);
		Thread.sleep(3000);
		this.getState().selectByVisibleText(state);
		Thread.sleep(3000);
		this.getCity().selectByVisibleText(city);

		this.getSaveBtn().submit();
    }
    }


