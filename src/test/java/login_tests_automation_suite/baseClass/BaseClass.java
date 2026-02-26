package login_tests_automation_suite.baseClass;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
//import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class BaseClass 
{
    public static WebDriver driver;
    public Properties properties;
    protected final Logger log = LoggerFactory.getLogger(getClass());

    @Parameters("browser")
    @BeforeClass
    public void setup(String browserName) throws IOException
    {
        properties = new Properties();
        try
        {
            //Get the current project directory
            String userDir = System.getProperty("user.dir");
            String fileSeparator = File.separator;

            // Build the path to the config.properties file in src/main/resources
            String configPath = userDir + fileSeparator + "src" + fileSeparator + "main" + fileSeparator + "resources" + fileSeparator + "loginTest.properties";
            // Open the config.properties file for reading
            FileInputStream fis = new FileInputStream(configPath);
            // Load properties from the file
            properties.load(fis);

        }
        catch(FileNotFoundException e)
        {
            throw new RuntimeException("Configuration file not found", e);
        }
        
       switch (browserName.toLowerCase()) 
       {
            case "chrome":
                String mode = properties.getProperty("execution_mode", "headed");
                ChromeOptions options = new ChromeOptions();

                if ("headless".equalsIgnoreCase(mode))
                {
                    options.addArguments("--headless=new");
                    options.addArguments("--no-sandbox");
                    options.addArguments("--disable-dev-shm-usage");
                    options.addArguments("--disable-gpu");
                    options.addArguments("--window-size=1920,1080");
                    log.info("Running in headless mode");
                }
                else
                {
                    log.info("Running in headed mode");
                }

                driver = new ChromeDriver(options);
                break;
            case "edge":
                driver = new EdgeDriver();
                break;
            default:
                throw new IllegalArgumentException("Unsupported browser: " + browserName);
        }
        driver.manage().deleteAllCookies();
        String url = properties.getProperty("appURL");
        log.info("Website url: {}", url);
        driver.get(url);

        // Maximize the browser window
        driver.manage().window().setSize(new Dimension(1920, 1080));
        //driver.manage().window().maximize();
        //driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
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
