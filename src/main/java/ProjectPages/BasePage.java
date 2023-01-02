package ProjectPages;


import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.*;
import utilities.ReadConfig;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class BasePage {

    ReadConfig rc=new ReadConfig();
    public String baseurl= rc.getApplicationURL();
    public String username=rc.getUserName();
    public String password=rc.getPassword();
    public static WebDriver driver;

    @Parameters("browser")
    @BeforeTest
    public void setup(String br) {
    if (br.equals("chrome")) {
        System.setProperty("webdriver.chrome.driver", rc.getChromePath());
        driver = new ChromeDriver();
    } else if (br.equals("firefox")) {
        System.setProperty("webdriver.gecko.driver",rc.getFirefoxPath());
        driver = new FirefoxDriver();

    }
    driver.get(baseurl);
    driver.manage().window().maximize();

}
    @AfterMethod(alwaysRun = true)
    public void captureScreenShot(ITestResult result, java.lang.reflect.Method methodname, ITestContext context){
        if (result.getStatus() == ITestResult.FAILURE) {
            getScreenshot(driver,result.getMethod().getMethodName() );
        }
    }
    public static String getScreenshot(WebDriver driver, String screenshotName) {
        String destination = null;
        try {
            String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
            TakesScreenshot ts = (TakesScreenshot) driver;
            File source = ts.getScreenshotAs(OutputType.FILE);
            destination = System.getProperty("user.dir") + "/target/Screenshots/" + screenshotName+"_"+dateName + ".png";
            File finalDestination = new File(destination);
            FileUtils.copyFile(source, finalDestination);
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            return destination;
        }
    }

    @AfterTest
    public void teardown(){
        driver.quit();

    }


}
