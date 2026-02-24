package login_tests_automation_suite.pageObjects;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginSuccessPageObject 
{
    public WebDriver driver;
    public WebDriverWait wait;

    public LoginSuccessPageObject(WebDriver driver)
    {
        this.driver=driver;
        wait=new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath="//h1[normalize-space()='Logged In Successfully']") WebElement loginSuccessMsgObject;
    @FindBy(xpath="//a[normalize-space()='Log out']") WebElement logoutBtn;

    public String getLoginSuccessText()
    {
        wait.until(ExpectedConditions.visibilityOf(loginSuccessMsgObject));
        String txt=loginSuccessMsgObject.getText();
        return txt;
    }

    public boolean isLogoutBtnDisplayed()
    {
        wait.until(ExpectedConditions.visibilityOf(logoutBtn));
        Boolean val=logoutBtn.isDisplayed();
        return val;
    }

    public void clickLogoutBtn()
    {
        wait.until(ExpectedConditions.elementToBeClickable(logoutBtn));
        logoutBtn.click();
    }

}
