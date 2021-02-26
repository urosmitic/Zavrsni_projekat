package tests;

import java.io.File;
import java.io.FileInputStream;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class SearchTest extends BasicTest {
	
	@Test(priority = 10)
	public void searchResultsTest() throws Exception {
		
		this.driver.get(baseURL + "/meals");

		SoftAssert soft = new SoftAssert();

		location.setLocationName("City Center - Albany");
		Thread.sleep(2000);
		
		File file = new File("data/data.xlsx");
		FileInputStream fis = new FileInputStream(file);
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet sheet = wb.getSheetAt(0);

		for (int i = 1; i <= 6; i++) {
			Thread.sleep(1000);
			XSSFRow row = sheet.getRow(i);

			location.selectLocation();
			String Location = row.getCell(0).getStringCellValue();
			location.setLocationName(Location);
			Thread.sleep(2000);
			
			String urlFromData = row.getCell(1).getStringCellValue();
			this.driver.get(urlFromData);

			int realCount = search.numOfFoundMelas();
			int resultCount = (int) row.getCell(2).getNumericCellValue();
			Assert.assertEquals(realCount, resultCount, "[ERROR] Number of results is not the same.");
			Thread.sleep(2000);
			
		
		for (int j = 3; j < 3 + row.getCell(2).getNumericCellValue(); j++) {
			String realResult = search.namesOfMeals().get(j - 3);
			String result = row.getCell(j).getStringCellValue();
			soft.assertTrue(realResult.contains(result), "[ERROR] The order of the results is not the same.");
			Thread.sleep(2000);
		}
		soft.assertAll();
	}

	fis.close();
	wb.close();

}
}
