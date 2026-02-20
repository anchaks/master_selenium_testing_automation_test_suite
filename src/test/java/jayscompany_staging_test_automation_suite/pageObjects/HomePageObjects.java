
package jayscompany_staging_test_automation_suite.pageObjects;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePageObjects 
{
    public WebDriver driver;
    public WebDriverWait wait;
    
    public HomePageObjects(WebDriver driver)
    {
        this.driver=driver;
        PageFactory.initElements(driver, this);   
        wait=new WebDriverWait(driver, Duration.ofSeconds(300));
    }

    /* 
    private WebElement waitForVisibility(WebElement element) 
    {
        return new WebDriverWait(driver, Duration.ofSeconds(45)).until(ExpectedConditions.visibilityOf(element));
    }
        */


    //Elements in the homepage
    @FindBy(css="div.amtheme-header-icon.user-account") WebElement userIcon;
    @FindBy(xpath="//a[normalize-space()='Create an Account']") WebElement createAccountLink;
    @FindBy(xpath="//a[normalize-space()='Sign In']") WebElement signInLink;

    //methods to access the elements

    // Hover over the user icon
    public void hoverOverUserIcon() 
    {
        wait.until(ExpectedConditions.visibilityOf(userIcon));
        Actions actions = new Actions(driver);
        actions.moveToElement(userIcon).perform();
    }

    // Click the Sign In link after hovering, with JS fallback
    public void clickSignInAfterHover() {
        hoverOverUserIcon();
        try {
            wait.until(ExpectedConditions.visibilityOf(signInLink));
            signInLink.click();
        } catch (Exception e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", signInLink);
        }
    }

    // Click the Create Account link after hovering, with JS fallback
    public void clickCreateAccountAfterHover() {
        hoverOverUserIcon();
        try {
            wait.until(ExpectedConditions.visibilityOf(createAccountLink));
            createAccountLink.click();
        } catch (Exception e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", createAccountLink);
        }
    }

    //click the create account radio button when the modal popup appears after click the create account link
    
    //xpath for the modal popup block
    @FindBy(xpath="//div[@id='popup-modal-spire-login']") WebElement modalPopupBlock;
    @FindBy(xpath="//input[@id='create_magento_account']") WebElement createAccountRadioButton;
    @FindBy(xpath="//label[@for='create_magento_account']") WebElement createAccountRadioButtonLabel;
    @FindBy(xpath="//input[@id='spire_account']") WebElement loginUsingExistingAccountRadioButton;
    @FindBy(xpath="//span[normalize-space()='Continue']") WebElement continueButtonInModalPopup;    

    public void clickCreateAccountRadioButtonInModalPopup()
    {
        wait.until(ExpectedConditions.visibilityOf(modalPopupBlock));
        try {
            wait.until(ExpectedConditions.elementToBeClickable(createAccountRadioButtonLabel));
            createAccountRadioButtonLabel.click();
        } catch (Exception e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", createAccountRadioButtonLabel);
        }
        wait.until(ExpectedConditions.elementSelectionStateToBe(createAccountRadioButton, true));

    }

    //click the login using existing account radio button when the modal popup appears after click the sign in link
    public void clickLoginUsingExistingAccountRadioButtonInModalPopup()
    {
        wait.until(ExpectedConditions.visibilityOf(modalPopupBlock));
        wait.until(ExpectedConditions.elementToBeClickable(loginUsingExistingAccountRadioButton));
        loginUsingExistingAccountRadioButton.click();
    }

    //click the continue button in the modal popup
    public void clickContinueButtonInModalPopup()
    {
        //wait.until(ExpectedConditions.elementToBeClickable(continueButtonInModalPopup));
        //continueButtonInModalPopup.click();

        try {
            wait.until(ExpectedConditions.visibilityOf(continueButtonInModalPopup));
            continueButtonInModalPopup.click();
        } catch (Exception e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", continueButtonInModalPopup);
        }
    }

    //locator for the top menu items

    @FindBy(xpath="//span[normalize-space()='ALL PRODUCTS']") WebElement shopAllMenuItem;
    @FindBy(xpath="//span[normalize-space()='CONTACT']") WebElement contactMenuItem;

    //locator for the submentu items
    @FindBy(xpath="//span[normalize-space()='AUXILIARY LABELS']") WebElement auxilaryLabelsSubMenuItem;
    @FindBy(xpath="//span[normalize-space()='RX DIRECT THERMAL LABELS']") WebElement rxDirectThermalLabelsSubMenuItem;

    //hover over the shop all menu item and then click the text link auxilary labels
    public void hoverAndClickAllMenuItemaAndClickAuxillaryLabel() 
    {
        wait.until(ExpectedConditions.visibilityOf(shopAllMenuItem));
        Actions actions = new Actions(driver);
        actions.moveToElement(shopAllMenuItem).perform();
        //click the category auxilary labels
        wait.until(ExpectedConditions.visibilityOf(auxilaryLabelsSubMenuItem));
        auxilaryLabelsSubMenuItem.click();
    }

    //hover the shop all menu item and then click the text link rx direct thermal labels
    public void hoverAndClickRxDirectThermalLabels()
    {
        wait.until(ExpectedConditions.visibilityOf(shopAllMenuItem));
        Actions actions = new Actions(driver);
        actions.moveToElement(shopAllMenuItem).perform();
        //click the category rx direct thermal labels
        wait.until(ExpectedConditions.visibilityOf(rxDirectThermalLabelsSubMenuItem));
        rxDirectThermalLabelsSubMenuItem.click();
    }

    //click the contact menu item
    public void clickContactMenuItem()
    {
        wait.until(ExpectedConditions.visibilityOf(contactMenuItem));
        contactMenuItem.click();
    }

    
}
