package jayscompany_staging_test_automation_suite.pageObjects;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CheckoutReviewAndPaymentsPageObjects 
{
    public WebDriver driver;
    public WebDriverWait wait;

    public CheckoutReviewAndPaymentsPageObjects(WebDriver driver)
    {
        this.driver=driver;
        PageFactory.initElements(driver, this);
        wait=new WebDriverWait(driver, Duration.ofSeconds(300));
    }

    //check if the ship to section is visible or not
    @FindBy(xpath="//div[@class='ship-to']")private WebElement shipToSection;

    public boolean isShipToSectionVisible() 
    {
        try 
        {
            wait.until(ExpectedConditions.visibilityOf(shipToSection));
            return true;
        } 
        catch (Exception e) 
        {
            return false;
        }
    }

    //locator for the additional information text area
    @FindBy(xpath="//input[@name='eaorder_fieldea_po_no']") private WebElement purchaseOrderNumberTextArea;
    public void enterPurchaseOrderNumber(String poNumber) 
    {
        wait.until(ExpectedConditions.visibilityOf(purchaseOrderNumberTextArea));
        purchaseOrderNumberTextArea.clear();
        purchaseOrderNumberTextArea.sendKeys(poNumber);
    }

    @FindBy(xpath="//input[@name='eaorder_fieldea_order_name']") private WebElement orderNameTextArea;
    
    public void enterOrderName(String orderName) 
    {
        wait.until(ExpectedConditions.visibilityOf(orderNameTextArea));
        orderNameTextArea.clear();
        orderNameTextArea.sendKeys(orderName);
    }

    @FindBy(xpath="//input[@name='eaorder_fieldea_note']") private WebElement orderNotesTextArea;

    public void enterOrderNotes(String orderNotes) 
    {
        wait.until(ExpectedConditions.visibilityOf(orderNotesTextArea));
        orderNotesTextArea.clear();
        orderNotesTextArea.sendKeys(orderNotes);
    }


    //locator and method to check the on account payment option is selected
    @FindBy(xpath="//label[@for='cashondelivery']") private WebElement onAccountPaymentOption;
    public boolean isOnAccountPaymentOptionSelected() 
    {
        wait.until(ExpectedConditions.visibilityOf(onAccountPaymentOption));
        return onAccountPaymentOption.isSelected();
    }

    //locator and method to click the Place Order button
    @FindBy(xpath="//button[@title='Place Order']") private WebElement placeOrderButton;

    public void clickPlaceOrderButton() 
    {
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div.loading-mask")));
        wait.until(ExpectedConditions.elementToBeClickable(placeOrderButton));
        placeOrderButton.click();
    }   

    //locator and method to check if any error validation message is coming or not and get that text
    @FindBy(xpath="//div[@data-ui-id='checkout-cart-validationmessages-message-error']") private WebElement errorValidationMessage;

    public boolean isAnyErrorValidationMessagePresent() 
    {
        try 
        {
            wait.until(ExpectedConditions.visibilityOf(errorValidationMessage));
            return true;
        } 
        catch (Exception e) 
        {
            return false;
        }
    }

    public String getErrorValidationMessageText() 
    {
        wait.until(ExpectedConditions.visibilityOf(errorValidationMessage));
        return errorValidationMessage.getText();
    }
    


     //locator for the PO number 
    @FindBy(xpath="//h1[normalize-space()='Thank you for your purchase!']") private WebElement orderConfirmationMessage;

    public boolean isOrderConfirmationMessageVisible() 
    {
        try 
        {
            wait.until(ExpectedConditions.visibilityOf(orderConfirmationMessage));
            return true;
        } 
        catch (Exception e) 
        {
            return false;
        }
    }

    //get the order confirmation message text
    public String getOrderConfirmationMessageText()
    {
        wait.until(ExpectedConditions.visibilityOf(orderConfirmationMessage));
        return orderConfirmationMessage.getText();
    }


    //locator for the order number link
    @FindBy(xpath="//a[@class='order-number']") private WebElement orderNumberLink;
    
    //locator for the strong tag inside the order number link
    @FindBy(xpath="//a[@class='order-number']/strong") private WebElement orderNumberText;

    public String getOrderNumberText() 
    {
        wait.until(ExpectedConditions.visibilityOf(orderNumberText));
        return orderNumberText.getText();
    }

    public boolean isOrderNumberVisible() 
    {
        try 
        {
            wait.until(ExpectedConditions.visibilityOf(orderNumberText));
            return true;
        } 
        catch (Exception e) 
        {
            return false;
        }
    }


}
