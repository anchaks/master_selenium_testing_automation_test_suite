package jayscompany_staging_test_automation_suite.pageObjects;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AccountPageObjects 
{

public WebDriver driver;
public WebDriverWait wait;

    public AccountPageObjects(WebDriver driver)
    {
        this.driver=driver;
        PageFactory.initElements(driver, this);
        wait=new WebDriverWait(driver, Duration.ofSeconds(300));
    }

    @FindBy(xpath="//div[@class='column main']") WebElement accountDashboardSection;

    //check if this main accunt dashboard is displayed
    public boolean isAccountDashboardSectionDisplayed()
    {
        return accountDashboardSection.isDisplayed();
    }

    //check if the contact information is displayed in the account dashboard section.   
    @FindBy(xpath="//div[@class='box box-information']//div[@class='box-content']") WebElement contactInformationSection;

    public boolean isContactInformationSectionDisplayed()
    {
        return contactInformationSection.isDisplayed();
    }

    //get the text of the contact information section
    public String getContactInformationText()
    {
        wait.until(ExpectedConditions.visibilityOf(contactInformationSection));
        return contactInformationSection.getText();
    }

    //Locator for the logo at the top
    @FindBy(xpath="//img[@class='logo-image']") WebElement topLogo;

    //check if the top logo is displayed
    public boolean isTopLogoDisplayed()
    {
        return topLogo.isDisplayed();
    }

    //click on the top logo
    public void clickOnTopLogo()
    {
        wait.until(ExpectedConditions.elementToBeClickable(topLogo));
        topLogo.click();
    }
}

