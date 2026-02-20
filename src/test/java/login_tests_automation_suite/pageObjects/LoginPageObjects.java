package login_tests_automation_suite.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPageObjects 
{
    WebDriver driver;

    public LoginPageObjects(WebDriver driver)
    {
        this.driver=driver;
        PageFactory.initElements(driver, this);
    }
    
    @FindBy(xpath="//h2[normalize-space()='Test login']") WebElement headerMsg;
    @FindBy(xpath="//input[@id='username']") WebElement userName;
    @FindBy(xpath="//input[@id='password']") WebElement passWord;
    @FindBy(xpath="//button[@id='submit']") WebElement submitBtn;
   
    @FindBy(xpath="//div[@id='error']") WebElement userNameInvalidError;

    public void enterUserName(String name)
    {
        userName.sendKeys(name);
    }

    public void enterPassword(String pwd)
    {
        passWord.sendKeys(pwd);
    }

    public void clickSubmit()
    {
        submitBtn.click();
    }

    public String getUserErrorMsg()
    {
        String txt=userNameInvalidError.getText();
        return txt;
    }

    public String getHeaderMsg()
    {
        String txt=headerMsg.getText();
        return txt;
    }
}
