
package login_tests_automation_suite.utilities;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class TakeScreenshot 
{
    /**
     * Captures screenshot and returns as base64 encoded string for Extent Reports
     * @param driver WebDriver instance
     * @return base64 encoded screenshot string
     */
    public static String captureScreenshotAsBase64(WebDriver driver) 
    {
        try 
        {
            TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
            String base64Screenshot = takesScreenshot.getScreenshotAs(OutputType.BASE64);
            return "data:image/png;base64," + base64Screenshot;
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
            return "Screenshot capture failed: " + e.getMessage();
        }
    }
    
    /**
     * Captures screenshot and saves to file, then returns base64 encoded string
     * @param driver WebDriver instance
     * @param testName Test name for file naming
     * @return base64 encoded screenshot string
     */
    public static String captureScreenshotAsBase64WithFile(WebDriver driver, String testName) 
    {
        try 
        {
            TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
            
            // Capture as file first
            File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
            
            // Create timestamp for unique filename
            String timeStamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
            String fileName = testName + "_" + timeStamp + ".png";
            
            // Define target file path (OS independent)
            String targetFilePath = System.getProperty("user.dir") + File.separator + "screenshots" + File.separator + fileName;
            File targetFile = new File(targetFilePath);
            
            // Create screenshots directory if it doesn't exist
            targetFile.getParentFile().mkdirs();
            
            // Copy file to target location
            Files.copy(sourceFile.toPath(), targetFile.toPath());
            
            // Read file and convert to base64
            byte[] fileContent = Files.readAllBytes(targetFile.toPath());
            String base64Screenshot = Base64.getEncoder().encodeToString(fileContent);
            
            return "data:image/png;base64," + base64Screenshot;
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
            return "Screenshot capture failed: " + e.getMessage();
        }
    }
    
    /**
     * Simple method to capture screenshot as byte array and return base64
     * @param driver WebDriver instance
     * @return base64 encoded screenshot string
     */
    public static String getBase64Screenshot(WebDriver driver) 
    {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
    }
}
