package login_tests_automation_suite.pageObjects;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPageObjects 
{
    WebDriver driver;
    public WebDriverWait wait;

    public LoginPageObjects(WebDriver driver)
    {
        this.driver=driver;
        wait=new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);

    }
    
    @FindBy(xpath="//h2[normalize-space()='Test login']") WebElement headerMsg;
    @FindBy(xpath="//input[@id='username']") WebElement userName;
    @FindBy(xpath="//input[@id='password']") WebElement passWord;
    @FindBy(xpath="//button[@id='submit']") WebElement submitBtn;
   
    @FindBy(xpath="//div[@id='error']") WebElement userNameInvalidError;

    public void enterUserName(String name)
    {
        wait.until(ExpectedConditions.visibilityOf(userName));
        userName.sendKeys(name);
    }

    public void enterPassword(String pwd)
    {
        wait.until(ExpectedConditions.visibilityOf(passWord));
        passWord.sendKeys(pwd);
    }

    public void clickSubmit()
    {
        wait.until(ExpectedConditions.elementToBeClickable(submitBtn));
        submitBtn.click();
    }

    public String getUserErrorMsg()
    {
        wait.until(ExpectedConditions.visibilityOf(userNameInvalidError));
        String txt=userNameInvalidError.getText();
        return txt;
    }

    public String getHeaderMsg()
    {
        wait.until(ExpectedConditions.visibilityOf(headerMsg));
        String txt=headerMsg.getText();
        return txt;
    }
}
