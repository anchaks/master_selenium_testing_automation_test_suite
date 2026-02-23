package jayscompany_staging_test_automation_suite.pageObjects;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CheckoutShippingPageObjects 
{
    public WebDriver driver;
    public WebDriverWait wait;

    public CheckoutShippingPageObjects(WebDriver driver)
    {
        this.driver=driver;
        PageFactory.initElements(driver, this);
        wait=new WebDriverWait(driver, Duration.ofSeconds(300));
    }

    /*
    when we reach the shipping page , shipping address section comes
    -we can select the already given address or create a new address
    -if selected already given address , we can click on NEXT button to proceed
    -if creating a new address, we need to fill all the address details, click on the checkbox save in address book
         and then click on SHIP HERE button to proceed
    -max of 2 shipping addresses can be saved in the address book. if there are 2  addresses already saved, the new address button will not appear
    -select any 1 of the saaved address by clicking Ship Here button and then click on the NEXT button to proceed

    - also check if the summary content section is visible or not
    */

    //locator for summary content section and checking if it being displayed or not

    @FindBy(xpath="//div[@class='amtheme-summary-content']") WebElement summaryContentSection;

    public boolean isSummaryContentSectionDisplayed()
    {
        wait.until(ExpectedConditions.visibilityOf(summaryContentSection));
        return summaryContentSection.isDisplayed();
    }

    //locator and action method to click the NEXT button in shipping address section

    @FindBy(xpath="//button[@class='button action continue primary']") WebElement nextButtonInShippingAddressSection;

    public void clickNextButtonInShippingAddressSection()
    {
        wait.until(ExpectedConditions.elementToBeClickable(nextButtonInShippingAddressSection));
        nextButtonInShippingAddressSection.click();
    }

    
    
    //locator for new address button and action method to click on it
    @FindBy(xpath="//button[@class='action action-show-popup']") WebElement newAddressButton;

    public void clickNewAddressButton()
    {
        wait.until(ExpectedConditions.elementToBeClickable(newAddressButton));
        newAddressButton.click();
    }

    //form appears after clicking new address button, locators and action methods to fill the form and save the new address

    @FindBy(xpath="//div[@name='shippingAddress.company']//div[@class='control']//input") WebElement companyNameFieldInNewAddressForm;

    public void enterCompanyNameInNewAddressForm(String companyName)
    {
        wait.until(ExpectedConditions.visibilityOf(companyNameFieldInNewAddressForm));
        companyNameFieldInNewAddressForm.sendKeys(companyName);
    }

    @FindBy(xpath="//div[@name='shippingAddress.street.0']//div[@class='control']//input") WebElement streetAddressFieldInNewAddressForm;
    

}
