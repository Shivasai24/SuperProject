package ProjectPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Login {
    WebDriver driver;

    public Login(WebDriver driver){
        PageFactory.initElements(driver,this);
    }


    @FindBy(name="username")
    WebElement Name;

    @FindBy(name="password")
    WebElement Password;

    @FindBy(id="submit" )
    WebElement submit;

    public void setName( String username) {
        Name.sendKeys(username);
    }

    public void setPassword(String password) {
        Password.sendKeys(password);
    }
    public void setSubmit(){
        submit.click();
    }

}

