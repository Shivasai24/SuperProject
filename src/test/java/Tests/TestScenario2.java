package Tests;

import ProjectPages.BasePage;
import ProjectPages.Login;
import ProjectPages.Logout;
import org.testng.Assert;
import org.testng.annotations.Test;


public class TestScenario2 extends TestBase {
    @Test
    public void Logout() throws InterruptedException {
        Login lp = new Login(driver);
        logger.info("Entered the url");
        lp.setName(username);
        logger.info("Entered username");
        lp.setPassword(password);
        logger.info("Entered Password");
        lp.setSubmit();
        logger.info("Clicked on submit");

        Thread.sleep(3000);

        Logout lo = new Logout(driver);
        lo.setLogout();
        logger.info("Clicked on Logout");

        boolean res =driver.getPageSource().contains("Use next credentials to execute Login:");
        logger.info("Assering the title");
        if(res==true){
            Assert.assertTrue(true);
        }
        else {
            Assert.assertTrue(false);
        }

    }

}
