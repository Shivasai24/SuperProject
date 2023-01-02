package Tests;

import ProjectPages.BasePage;
import ProjectPages.Login;
import org.testng.Assert;
import org.testng.annotations.Test;

@Test
public class TestPage extends BasePage {

    public void loginTest() {

        Login lp = new Login(driver);
        lp.setName(username);

        lp.setPassword(password);

        lp.setSubmit();

        String title = driver.getTitle();
        System.out.println(title);
        boolean res = driver.getPageSource().contains("Logged In Successfully");
        if (res == true) {
            Assert.assertTrue(true);
        } else {
            Assert.assertTrue(false);
        }
    }

}
