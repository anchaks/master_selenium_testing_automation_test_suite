package jayscompany_staging_test_automation_suite.baseClasses;

import java.io.File;
import java.io.FileInputStream;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import java.util.Base64;


public class BaseClass implements DriverProvider
{
    // WebDriver instance for browser automation (protected for subclass access)
    protected static WebDriver driver;
    // Properties object to hold configuration loaded from file
    public Properties properties;
    // Logger instance for this class, using SLF4J for consistent logging across the framework
    protected final Logger log = LoggerFactory.getLogger(getClass());

   /**
     * Setup method to initialize WebDriver and load configuration properties.
     * Runs once before any test methods in the class (TestNG @BeforeClass).
     */
    @BeforeClass

    /**
     * Captures a screenshot and saves it to the screenshots directory with the given name.
     * Returns the absolute file path for ExtentReports attachment.
     * @param screenshotName Name to use for the screenshot file (without extension)
     * @return Absolute file path of the saved screenshot, or null if capture fails.
     */



    public void setUp() 
    {
        // Initialize the Properties object
        properties = new Properties();
        try
        {
            // Get the current project directory
            String userDir = System.getProperty("user.dir");
            String fileSeparator = File.separator;

            // Build the path to the config.properties file in src/main/resources
            String configPath = userDir + fileSeparator + "src" + fileSeparator + "main" + fileSeparator + "resources" + fileSeparator + "jayscompany_frontend.properties";
            // Open the config.properties file for reading
            FileInputStream fis = new FileInputStream(configPath);
            // Load properties from the file
            properties.load(fis);

            /*
            // (Optional) Print all loaded properties for debugging
            System.out.println("Loaded properties:");
            for (String key : properties.stringPropertyNames()) 
            {
                System.out.println(key + " = " + properties.getProperty(key));
            }
            */
        }
        catch(Exception e)
        {
            // Print stack trace if loading properties fails
            e.printStackTrace();
        }

        // Initialize the Chrome browser driver
        driver = new ChromeDriver();
        // Maximize the browser window
        driver.manage().window().maximize();
        // Clear all cookies to ensure a clean session
        driver.manage().deleteAllCookies();

        // Retrieve the website URL from the loaded properties file (key: webURL2)
        String url = properties.getProperty("frontend_staging_URL");
        // Log the website URL using SLF4J logger for traceability
        log.info("Website url: {}", url);
        // Launch the browser and navigate to the specified URL
        driver.get(url);
    }
    

    /**
     * Returns the WebDriver instance for use in test classes.
     * This method is required by the DriverProvider interface.
     */
    @Override
    public WebDriver getDriver() {
        return driver;
    }

    /**
     * Cleanup method to close the browser after all tests in the class have run.
     * Runs once after all test methods in the class (TestNG @AfterClass).
     */
    @AfterClass
    public void tearDown() 
    {
        // Quit the browser and release resources if driver is initialized
        if (driver != null) {
            driver.quit();
        }
    }

    /**
     * Captures a screenshot and returns it as a Base64-encoded string (for embedding in reports if needed).
     * @return Base64-encoded screenshot string, or null if capture fails.
     */
    public String captureScreenBase64() {
        try {
            if (driver instanceof TakesScreenshot) {
                byte[] screenshotBytes = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
                return Base64.getEncoder().encodeToString(screenshotBytes);
            }
        } catch (Exception e) {
            log.error("Failed to capture screenshot as Base64: ", e);
        }
        return null;
    }
}
