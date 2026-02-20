package jayscompany_staging_test_automation_suite.pageObjects;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CategoryLandingPageObjects 
{
    public WebDriver driver;
    public WebDriverWait wait;
    public Actions actions;

    public CategoryLandingPageObjects(WebDriver driver)
    {
        this.driver=driver;
        this.actions = new Actions(driver);
        PageFactory.initElements(driver, this);
        wait=new WebDriverWait(driver, Duration.ofSeconds(300));
    }

    //check for the header of the category landing page
    @FindBy(xpath="//h1[@id='page-title-heading']") WebElement categoryLandingPageHeader;

    //method to check if the landing page header exists
    public boolean isCategoryLandingPageHeaderDisplayed() 
    {
        try 
        {
            return categoryLandingPageHeader.isDisplayed();
        } 
        catch (Exception e) 
        {
            return false;
        }
    }

    //method to get the text of the landing page header
    public String getCategoryLandingPageHeaderText() 
    {
        return categoryLandingPageHeader.getText();
    }
    
    /* 
    //locators for a product in the category landing page using its name and the add to cart button
    @FindBy(xpath="//a[contains(text(),'BLANK DIRECT THERMAL LABEL WHITE - 3-1/2 X 1 - ARXDT')]") WebElement productBlankDirectThermalLabelWhite;
    @FindBy(xpath="//form[contains(@data-product-sku,'ARXDT')]//span[contains(@class,'amtheme-title')][normalize-space()='Add to Cart']") WebElement addToCartButtonForProductBlankDirectThermalLabelWhite;

    //method to hover over it and click on the add to cart button
    public void hoverOverProductAndClickAddToCart(String productTitle)
    {
        WebElement productLink = driver.findElement(By.xpath("//div[contains(@class,'products wrapper grid products-grid')]//li//a[@title='" + productTitle + "']")
);
        Actions actions = new Actions(driver);
        actions.moveToElement(productLink).perform();
        //productBlankDirectThermalLabelWhite.click();
        addToCartButtonForProductBlankDirectThermalLabelWhite.click();
    }
    */

    //locator for the product in the category landing page using its SKU 
    // Each product tile (li.item.product.product-item)
    @FindBy(css = "li.item.product.product-item")private List<WebElement> productItems;

    /**
     * Hover over product by SKU
     */

    /* 
    public void hoverOverProductBySku(String sku) {

        for (WebElement product : productItems) {

            String productSku = product.findElement(By.xpath(".//strong[contains(text(),'SKU')]")).getText();

            if (productSku.contains(sku)) {

                actions.moveToElement(product).perform();
                return;
            }
        }
        throw new RuntimeException("Product with SKU " + sku + " not found");
    }
    */

    /**
     * Find product link by its visible/title text, scroll into view and hover it.
     * Matches by equals or contains (case-insensitive).
     */
    public void hoverOverProductLinkByName(String linkName) {
        if (linkName == null || linkName.trim().isEmpty()) {
            throw new IllegalArgumentException("linkName must be provided");
        }
        String needle = linkName.trim().toLowerCase();

        List<WebElement> links = driver.findElements(By.xpath("//div[contains(@class,'products wrapper grid products-grid')]//li//div[@class='product-item-info']//a[@class='product-item-link']"));
        if (links.isEmpty()) {
            throw new RuntimeException("No product links found on the page");
        }

        JavascriptExecutor js = (JavascriptExecutor) driver;
        for (WebElement link : links) {
            try {
                String title = link.getAttribute("title");
                if (title == null || title.trim().isEmpty()) title = link.getText();
                if (title == null || title.trim().isEmpty()) continue;

                String t = title.trim().toLowerCase();
                if (t.equals(needle) || t.contains(needle)) {
                    js.executeScript("arguments[0].scrollIntoView({block:'center', inline:'nearest'});", link);
                    wait.until(ExpectedConditions.visibilityOf(link));
                    actions.moveToElement(link).perform();
                    return;
                }
            } catch (Exception ignored) {
                // continue to next link
            }
        }

        throw new RuntimeException("Product link '" + linkName + "' not found");
    }

    //click add to cart after hovering over the product if it is visible
    @FindBy(xpath="//div//form//button[@type='submit' and normalize-space(.)='Add to Cart']") WebElement addtoCartButton;

    public void clickAddToCart() 
    {
        wait.until(ExpectedConditions.elementToBeClickable(addtoCartButton));
        addtoCartButton.click();
    }




    /**
     * Hover over first product (simpler fallback)
     */
    public void hoverOverFirstProduct() {
        actions.moveToElement(productItems.get(0)).perform();
    }

    //click add to cart after hovering over the product
    public void clickAddToCartForFirstProduct() 
    {
        // Get the first product tile from the list of all product items
        WebElement firstProduct = productItems.get(0);

        // Within that product tile, find the "Add to Cart" button using a relative XPath
        WebElement addToCartButton = firstProduct.findElement(By.xpath(".//span[contains(@class,'amtheme-title') and normalize-space()='Add to Cart']"));

        // Wait until the "Add to Cart" button is clickable (visible and enabled)
        wait.until(ExpectedConditions.elementToBeClickable(addToCartButton));

        // Click the "Add to Cart" button
        addToCartButton.click();
    }


    //locator for the popup after adding to cart inside the category landing page
    @FindBy(xpath="//label[normalize-space()='Purchase only once.']") WebElement purchaseOnlyOnceLabelInPopup;
    @FindBy(xpath="//label[normalize-space()='AutoShip']") WebElement autoShipLabelInPopup;

    //method to check if the purchase only once label is displayed in the popup
    public boolean isPurchaseOnlyOnceLabelDisplayedInPopup()
    {
        try
        {
            return purchaseOnlyOnceLabelInPopup.isDisplayed();
        }
        catch(Exception e)
        {
            return false;
        }
    }

    //method to click on the purchase only once label in the popup
    public void clickPurchaseOnlyOnceLabelInPopup()
    {
        wait.until(ExpectedConditions.elementToBeClickable(purchaseOnlyOnceLabelInPopup));
        purchaseOnlyOnceLabelInPopup.click();
    }

    //click add to to cart in the popup
    @FindBy(xpath="//button[normalize-space()='Add to cart']") WebElement addToCartButtonInPopup;

    public void clickAddToCartButtonInPopup()
    {
        wait.until(ExpectedConditions.elementToBeClickable(addToCartButtonInPopup));
        addToCartButtonInPopup.click();
    }

    //click the cart icon to go to the cart popup in the header
    @FindBy(xpath="//a[@title='My Cart']//*[name()='svg']") WebElement cartIconInHeader;
    public void clickCartIconInHeader()
    {
        wait.until(ExpectedConditions.elementToBeClickable(cartIconInHeader));
        cartIconInHeader.click();
    }

    //click the view cart button in the popup after clicking add to cart in the the previous popup
    @FindBy(xpath="//button[normalize-space()='View Cart']") WebElement viewCartButtonInPopup;

    //check if the view cart button is displayed in the popup
    public boolean isViewCartButtonDisplayedInPopup()
    {
        try
        {
            return viewCartButtonInPopup.isDisplayed(); 
        }
        catch(Exception e)
        {
            return false;
        }
    }
    public void clickViewCartButtonInPopup()
    {
        wait.until(ExpectedConditions.elementToBeClickable(viewCartButtonInPopup));
        viewCartButtonInPopup.click();
    }

    

}
