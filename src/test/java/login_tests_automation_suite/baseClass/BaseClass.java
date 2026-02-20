package login_tests_automation_suite.baseClass;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class BaseClass 
{
    public static WebDriver driver;
    public Properties property;

    @Parameters("browser")
    @BeforeClass
    public void setup(String browserName) throws IOException
    {
        property = new Properties();
        try
        {
            // Use classpath to load config file in OS-independent way
            FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "/src/main/resources/loginTest.properties");
            property.load(fis);

        }
        catch(FileNotFoundException e)
        {
            throw new RuntimeException("Configuration file not found", e);
        }
        
       switch (browserName.toLowerCase()) 
       {
            case "chrome":
                driver = new ChromeDriver();
                break;
            case "edge":
                driver = new EdgeDriver();
                break;
            default:
                throw new IllegalArgumentException("Unsupported browser: " + browserName);
        }
        driver.manage().deleteAllCookies();
        driver.get(property.getProperty("appURL"));
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }
    @AfterClass
    public void tearDown()
    {
        driver.quit();
    }

    public static String captureScreen(String tname) throws IOException
    {
        String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
        TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
        File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
        String targetFilePath = System.getProperty("user.dir") + File.separator + "screenshots" + File.separator + tname + "_" + timeStamp + ".jpg";
        File targetFile = new File(targetFilePath);
        sourceFile.renameTo(targetFile);
        return targetFilePath;
    }

    
}
