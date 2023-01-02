package ProjectPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Logout
{
    WebDriver driver;
    public Logout(WebDriver driver){
        PageFactory.initElements(driver,this);}

        @FindBy(linkText = "Log out")
        WebElement logout;

    public void setLogout()
    {
        logout.click();
    }

    }

