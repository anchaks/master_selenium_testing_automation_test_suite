package jayscompany_staging_test_automation_suite.pageObjects;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
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
        wait=new WebDriverWait(driver, Duration.ofSeconds(30));
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


    //locator and action method for Purchase order validation message
    @FindBy(xpath="///span[@data-bind='text: element.error']") private WebElement purchaseOrderValidationMessage;
    public boolean isPurchaseOrderValidationMessageVisible() 
    {
        try 
        {
            wait.until(ExpectedConditions.visibilityOf(purchaseOrderValidationMessage));
            return true;
        } 
        catch (Exception e) 
        {
            return false;
        }
    }

    public String getPurchaseOrderValidationMessageText() 
    {
        wait.until(ExpectedConditions.visibilityOf(purchaseOrderValidationMessage));
        return purchaseOrderValidationMessage.getText();
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

    private final By loadingMaskLocator = By.cssSelector("div.loading-mask");
    private final By successHeadingLocator = By.xpath("//h1[contains(normalize-space(),'Thank you for your purchase')]");
    private final By orderNumberLocator = By.cssSelector("a.order-number strong");
    private final By genericPageTitleLocator = By.cssSelector("h1.page-title span.base");

    public void clickPlaceOrderButton() 
    {
        wait.until(ExpectedConditions.invisibilityOfElementLocated(loadingMaskLocator));
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
        boolean orderPlacementCompleted = waitForOrderPlacementToComplete();

        List<WebElement> successHeadingElements = driver.findElements(successHeadingLocator);
        if (!successHeadingElements.isEmpty() && successHeadingElements.get(0).isDisplayed())
        {
            return successHeadingElements.get(0).getText();
        }

        List<WebElement> genericHeadingElements = driver.findElements(genericPageTitleLocator);
        if (!genericHeadingElements.isEmpty() && genericHeadingElements.get(0).isDisplayed())
        {
            return genericHeadingElements.get(0).getText();
        }

        if (!orderPlacementCompleted && isAnyErrorValidationMessagePresent())
        {
            return "Order placement failed: " + getErrorValidationMessageText();
        }

        return "Order placed - confirmation heading not found";
    }


    //locator for the order number link
    @FindBy(xpath="//a[@class='order-number']") private WebElement orderNumberLink;
    
    //locator for the strong tag inside the order number link
    @FindBy(xpath="//a[@class='order-number']/strong") private WebElement orderNumberText;

    public String getOrderNumberText() 
    {
        boolean orderPlacementCompleted = waitForOrderPlacementToComplete();

        List<WebElement> orderNumberElements = driver.findElements(orderNumberLocator);
        if (!orderNumberElements.isEmpty() && orderNumberElements.get(0).isDisplayed())
        {
            return orderNumberElements.get(0).getText();
        }

        if (!orderPlacementCompleted && isAnyErrorValidationMessagePresent())
        {
            return "Order placement failed";
        }

        return "Order number not found";
    }

    public boolean isOrderNumberVisible() 
    {
        try 
        {
            waitForOrderPlacementToComplete();
            return !driver.findElements(orderNumberLocator).isEmpty();
        } 
        catch (Exception e) 
        {
            return false;
        }
    }

    private boolean waitForOrderPlacementToComplete()
    {
        wait.until(ExpectedConditions.invisibilityOfElementLocated(loadingMaskLocator));
        try
        {
            wait.until(driver ->
                driver.getCurrentUrl().contains("checkout/onepage/success")
                || !driver.findElements(successHeadingLocator).isEmpty()
                || !driver.findElements(orderNumberLocator).isEmpty()
                || !driver.findElements(By.xpath("//div[@data-ui-id='checkout-cart-validationmessages-message-error']")).isEmpty()
            );
            return true;
        }
        catch (TimeoutException e)
        {
            return false;
        }
    }


}
