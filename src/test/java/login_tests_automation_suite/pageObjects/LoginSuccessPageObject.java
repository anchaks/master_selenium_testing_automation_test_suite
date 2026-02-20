package login_tests_automation_suite.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginSuccessPageObject 
{
    WebDriver driver;

    public LoginSuccessPageObject(WebDriver driver)
    {
        this.driver=driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath="//h1[normalize-space()='Logged In Successfully']") WebElement loginSuccessMsgObject;
    @FindBy(xpath="//a[normalize-space()='Log out']") WebElement logoutBtn;

    public String getLoginSuccessText()
    {
        String txt=loginSuccessMsgObject.getText();
        return txt;
    }

    public boolean isLogoutBtnDisplayed()
    {
        Boolean val=logoutBtn.isDisplayed();
        return val;
    }

    public void clickLogoutBtn()
    {
        logoutBtn.click();
    }

}
