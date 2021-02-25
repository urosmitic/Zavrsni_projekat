package tests;

import java.io.File;
import java.io.FileInputStream;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;



public class MealItemTest extends BasicTest {
	
	@Test(priority = 10)
	public void addMealToCart() throws Exception  {
		
		this.driver.get(baseURL + "/meal/lobster-shrimp-chicken-quesadilla-combo");
		
		location.closePopup();
		
		meal.addMealToCart(3);
		
		String errorMsg = notification.returnMessage();
		Assert.assertEquals(errorMsg, "The Following Errors Occurred:" + "\n" + "Please Select Location", "[ERROR] Error message not exist.");
		notification.disapearMessage();
		
		location.selectLocation();
		location.setLocationName("City Center - Albany");
		Thread.sleep(2000);
		
		meal.addMealToCart(3);
		Thread.sleep(2000);
		
		String mealAdd = notification.returnMessage();
		Assert.assertEquals(mealAdd, "Meal Added To Cart", "[ERROR] Meal is not added to cart.");
		Thread.sleep(2000);
	}
	
     	@Test(priority = 20)
	  public void addMealToFavorite() throws Exception {
     		
     		this.driver.get(baseURL + "/meal/lobster-shrimp-chicken-quesadilla-combo");
     		
     		location.closePopup();
     		
     		meal.addMealFavorite();
    		Thread.sleep(2000);
    		
    		String PleaseLogin = notification.returnMessage();
    		Assert.assertEquals(PleaseLogin, "Please login first!", "[ERROR] Login Failed.");
    		
    		this.driver.get(baseURL + "/guest-user/login-form");
    		login.login(demoEmail, demoPass);
    		
    		this.driver.get(baseURL + "/meal/lobster-shrimp-chicken-quesadilla-combo");
    		meal.addMealFavorite();
    		Thread.sleep(2000);
    		
    		String addFavorite = notification.returnMessage();
    		Assert.assertEquals(addFavorite, "Product has been added to your favorites.", "[ERROR] Product is not added to favorite."); 
    		}
     	@Test(priority = 30)
    	public void clearCart() throws Exception {
     		
     		this.driver.get(baseURL + "/meals");
    		SoftAssert soft = new SoftAssert();
    		
    		location.setLocationName("City Center - Albany");
    		Thread.sleep(2000);
    		
    		File file = new File("data/data.xlsx");
    		FileInputStream fis = new FileInputStream(file);
    		XSSFWorkbook wb = new XSSFWorkbook(fis);
    		XSSFSheet sheet = wb.getSheet("Meals");
    		
    		for (int i = 1; i < sheet.getLastRowNum(); i++) {
    			XSSFRow row = sheet.getRow(i);
    			
    			XSSFCell url1 = row.getCell(0);
    			String url = url1.getStringCellValue();

    			this.driver.get(url);
    			Thread.sleep(2000);

    			meal.addMealToCart(2);
    			Thread.sleep(2000);
    			
    			soft.assertEquals(notification.returnMessage(), "Meal Added To Cart", "[ERROR] Cart is empty.");
    			Thread.sleep(2000);
    			
    			soft.assertAll();

    			cart.clearAll();
    			
    			String removeAll = notification.returnMessage();
    			Assert.assertEquals(removeAll, "All meals removed from Cart successfully", "[ERROR] Meals are not removed." );
    			
    			fis.close();
    			wb.close();
    		}
            }
            }
     	
            


