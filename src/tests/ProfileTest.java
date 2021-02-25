package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

public class ProfileTest extends BasicTest {
	
	@Test(priority = 10)
	public void editProfileTest() throws Exception {

		this.driver.get(baseURL + "/guest-user/login-form");

		

		location.closePopup();

		login.login(demoEmail, demoPass);
		String logms = notification. returnMessage();
		Assert.assertEquals(logms, "Login Successfull", "[ERROR] Login Failed.");

		this.driver.get(baseURL + "/member/profile");
		profile.updateProfile("Uros", "Mitic", "Dragise Cvetkovica 85", "06052393393", "18000", "United States", "Indiana",
				"Troy");
		String setMsg = notification.returnMessage();
		Assert.assertEquals(setMsg, "Setup Successful", "[ERROR] Profile didnt update.");

		auth.logoutAccount();
		String logoutMsg = notification.returnMessage();
		Assert.assertEquals(logoutMsg, "Logout Successfull!", "[ERROR] Login Failed.");

	}

	@Test(priority = 20)
	public void changeProfileImageTest() throws Exception {

		this.driver.get(baseURL + "/guest-user/login-form");

		

	     location.closePopup();

		login.login(demoEmail, demoPass);
		String logms = notification.returnMessage();
		Assert.assertEquals(logms, "Login Successfull", "[ERROR] Login Failed.");

		this.driver.get(baseURL + "/member/profile");

		profile.uploadImage();
		String sucImgUp = notification.returnMessage();
		Assert.assertEquals(sucImgUp, "Profile Image Uploaded Successfully", "[ERROR] Profile image didnt update.");
		notification.disapearMessage();

		profile.deleteImage();
		String delImg = notification.returnMessage();
		Assert.assertEquals(delImg, "Profile Image Deleted Successfully", "[ERROR] Profile image didnt delete.");
		notification.disapearMessage();

		auth.logoutAccount();
		String logoutMsg = notification.returnMessage();
		Assert.assertEquals(logoutMsg, "Logout Successfull!", "[ERROR] Logout Failed.");

	}

}


