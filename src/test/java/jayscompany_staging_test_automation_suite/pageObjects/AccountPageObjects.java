package jayscompany_staging_test_automation_suite.pageObjects;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.TimeoutException;
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
        wait=new WebDriverWait(driver, Duration.ofSeconds(60));
    }

    //locator and action method for welcome text in the account dashboard section
    @FindBy(xpath="//div//h1[@class='page-title']") WebElement welcomeText;

    public String getWelcomeText()
    {
        wait.until(ExpectedConditions.visibilityOf(welcomeText));
        return welcomeText.getText();
    }

    public boolean isWelcomeTextDisplayed()
    {
        try
        {
            wait.until(ExpectedConditions.visibilityOf(welcomeText));
            return welcomeText.isDisplayed();
        }
        catch (TimeoutException e)
        {
            return false;
        }
    }

    //locator and action method for the address dashboard section
    @FindBy(xpath="//div[@class='amtheme-addresses-block -dashboard-addresses']") WebElement addressDashboardSection;
    public boolean isAddressDashboardSectionDisplayed()
    {
        try
        {
            wait.until(ExpectedConditions.visibilityOf(addressDashboardSection));
            return addressDashboardSection.isDisplayed();
        }
        catch (TimeoutException e)
        {
            return false;
        }
    }   



    @FindBy(xpath="//main//h1[contains(@class,'page-title') and contains(normalize-space(),'My Account')]") WebElement accountDashboardSection;

    //check if this main account dashboard is displayed
    public boolean isAccountDashboardSectionDisplayed()
    {
        try
        {
            wait.until(ExpectedConditions.visibilityOf(accountDashboardSection));
            return accountDashboardSection.isDisplayed();
        }
        catch (TimeoutException e)
        {
            return false;
        }
    }

    //check if the contact information is displayed in the account dashboard section.   
    @FindBy(xpath="//div[contains(@class,'box-information')]//*[contains(@class,'box-content')]") WebElement contactInformationSection;

    public boolean isContactInformationSectionDisplayed()
    {
        try
        {
            wait.until(ExpectedConditions.visibilityOf(contactInformationSection));
            return contactInformationSection.isDisplayed();
        }
        catch (TimeoutException e)
        {
            return false;
        }
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

