package jayscompany_staging_test_automation_suite.pageObjects;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CartPageObjects 
{
    public WebDriver driver;
    public WebDriverWait wait;

    public CartPageObjects(WebDriver driver)
    {
        this.driver=driver;
        PageFactory.initElements(driver, this);
        wait=new WebDriverWait(driver, Duration.ofSeconds(300));
    }

    //locator and method to get the cart page title
    @FindBy(xpath="//h1[normalize-space()='Shopping Cart']") WebElement cartPageTitle;

    public String getCartPageTitleText()
    {
        wait.until(ExpectedConditions.visibilityOf(cartPageTitle));
        return cartPageTitle.getText();
    }

    

    //locator and method to click the checkout button
    @FindBy(xpath="//button[@data-role='proceed-to-checkout']") WebElement checkoutButton;

    public void clickCheckoutButton()
    {
        wait.until(ExpectedConditions.elementToBeClickable(checkoutButton));
        checkoutButton.click();
    } 
    
    //locator and method to edit the cart by clicking the edit button
    //this will take us back to the PDP page

    @FindBy(xpath="//a[normalize-space()='Edit']") WebElement editCartButton;
    
    public void clickEditCartButton()
    {
        wait.until(ExpectedConditions.elementToBeClickable(editCartButton));
    }


    //locator and method to click the remove and add to wishlist button
    @FindBy(xpath="//a[@class='use-ajax action towishlist action-towishlist']") WebElement removeAndAddToWishlistButton;

    public void clickRemoveAndAddToWishlistButton()
    {
        wait.until(ExpectedConditions.elementToBeClickable(removeAndAddToWishlistButton));
    }

    
}
