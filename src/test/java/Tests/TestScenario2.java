package Tests;

import ProjectPages.BasePage;
import ProjectPages.Login;
import ProjectPages.Logout;
import org.testng.Assert;
import org.testng.annotations.Test;

@Test
public class TestScenario2 extends BasePage {
    public void Logout() throws InterruptedException {
        Login lp = new Login(driver);
        lp.setName(username);
        lp.setPassword(password);
        lp.setSubmit();

        Thread.sleep(3000);

        Logout lo = new Logout(driver);
        lo.setLogout();

        boolean res =driver.getPageSource().contains("Use next credentials to execute Login:");

        if(res==true){
            Assert.assertTrue(true);
        }
        else {
            Assert.assertTrue(false);
        }

    }

}
