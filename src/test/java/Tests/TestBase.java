package Tests;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;
import org.testng.annotations.*;
import utilities.ReadConfig;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Logger;


public class TestBase extends TestListenerAdapter {

    ReadConfig rc=new ReadConfig();
    public String baseurl= rc.getApplicationURL();
    public String username=rc.getUserName();
    public String password=rc.getPassword();
    public static WebDriver driver;
    public static ExtentHtmlReporter htmlReporter;
    public static ExtentReports extent;
    public static ExtentTest test;

    public static Logger logger;
    @BeforeSuite
    public void beforeSuite() {
        createFolderExtendReport();
        htmlReporter = new ExtentHtmlReporter(
                System.getProperty("user.dir") + "/ExtendReport/" + "TestReport" + ".html");
        htmlReporter.loadXMLConfig(System.getProperty("user.dir") + "/extent-config.xml");
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
        extent.setSystemInfo("Host name", "QA");
        extent.setSystemInfo("Environment", System.getProperty("env"));
        htmlReporter.config().setDocumentTitle("Super Automation report"); // Tile of report
        htmlReporter.config().setReportName("Super Automation report"); // name of the report
        htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP); // location of the chart
        htmlReporter.config().setTheme(Theme.STANDARD);

    }
    public void createFolderExtendReport() {
        try {
            File file = new File(System.getProperty("user.dir") + "/" + "ExtendReport");
            file.mkdir();
            boolean flag = file.mkdir();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @BeforeMethod(alwaysRun = true)
    public void beforeMethod(ITestResult result, java.lang.reflect.Method methodName) {
        //Extend Report
        test = extent.createTest(methodName.getName()); // create new entry in the report
    }
    @Parameters("browser")
    @BeforeMethod(alwaysRun = true)
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

        logger=Logger.getLogger("Superproject");
        PropertyConfigurator.configure("log4j.properties");
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
    @AfterMethod(alwaysRun = true)
    public void teardown(){
        driver.quit();
    }
    @AfterSuite(alwaysRun = true)
    public void quit() throws Exception {
        extent.flush();
    }


}

