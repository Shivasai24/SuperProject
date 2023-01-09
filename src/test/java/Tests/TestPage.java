package Tests;

import ProjectPages.BasePage;
import ProjectPages.Login;
import org.testng.Assert;
import org.testng.annotations.Test;



public class TestPage extends TestBase {
    @Test(description = "Verifying Login")
    public void loginTest() {

        Login lp = new Login(driver);
      logger.info("Opening the URL");
        lp.setName(username);
        logger.info("UserName Entered");
        lp.setPassword(password);
        logger.info("Entered Password");
        lp.setSubmit();
        logger.info("Clicked on submit button");
        String title = driver.getTitle();
        logger.info("Getting the title");
        System.out.println(title);
        boolean res = driver.getPageSource().contains("Logged In Successfully");
        logger.info("Asserting title");
        if (res == true) {
            Assert.assertTrue(true);
        } else {
            Assert.assertTrue(false);
        }
    }

}
