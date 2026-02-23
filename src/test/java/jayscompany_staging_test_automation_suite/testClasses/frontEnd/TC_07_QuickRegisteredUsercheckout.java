package jayscompany_staging_test_automation_suite.testClasses.frontEnd;



//import org.testng.Assert;
import org.testng.annotations.Test;

import jayscompany_staging_test_automation_suite.baseClasses.BaseClass;
import jayscompany_staging_test_automation_suite.pageObjects.AccountPageObjects;
import jayscompany_staging_test_automation_suite.pageObjects.CartPageObjects;
import jayscompany_staging_test_automation_suite.pageObjects.CategoryLandingPageObjects;
import jayscompany_staging_test_automation_suite.pageObjects.CheckoutReviewAndPaymentsPageObjects;
import jayscompany_staging_test_automation_suite.pageObjects.CheckoutShippingPageObjects;
import jayscompany_staging_test_automation_suite.pageObjects.HomePageObjects;
import jayscompany_staging_test_automation_suite.pageObjects.LoginPageObjects;



public class TC_07_QuickRegisteredUsercheckout extends BaseClass
{
    @Test
    public void loginTest() throws InterruptedException 
    {
        log.info("Starting Registered User Checkout Test");
        
        //get original url
        String originalURL = driver.getCurrentUrl();
        log.info("Original URL: {}", originalURL);

        HomePageObjects homePage=new HomePageObjects(driver);
        homePage.clickSignInAfterHover();
        
        //get current url
        String currentURL = driver.getCurrentUrl();
        log.info("Current URL after clicking Sign In: {}", currentURL);

        String email=properties.getProperty("front_end_user");
        String password=properties.getProperty("front_end_user_password");
        
        LoginPageObjects loginPage=new LoginPageObjects(driver);
        loginPage.enterEmailAndPassword(email, password);
        loginPage.clickSignInButton();

        AccountPageObjects accountPage=new AccountPageObjects(driver);
        Thread.sleep(2000);
        boolean isAccountDashboardDisplayed=accountPage.isAccountDashboardSectionDisplayed();
        
        //if account dashboard is displayed, get the contact information text and verify the email
        if(isAccountDashboardDisplayed)
        {
            log.info("Account Dashboard is displayed");
            String contactInfoText=accountPage.getContactInformationText();
            log.info("Contact Information Text: {}", contactInfoText);
            
            if(contactInfoText.contains(email))
            {
                log.info("Login Successful: Email found in Contact Information");
            }
            else
            {
                log.error("Login Failed: Email not found in Contact Information");
            }
        }
        else
        {
            log.error("Account Dashboard is not displayed. Login might have failed.");
        }

        accountPage.clickOnTopLogo();
        String newURL = driver.getCurrentUrl();
        log.info("URL after clicking Top Logo: {}", newURL);
        if(newURL.equals(originalURL))
        {
            log.info("Navigation to Home Page Successful");
        }
        else
        {
            log.error("Navigation to Home Page Failed");
        }

        //hover the all products menu and click on the first category
        //homePage.hoverAndClickAllMenuItemaAndClickAuxillaryLabel();
        homePage.hoverAndClickRxDirectThermalLabels();
        String categoryURL = driver.getCurrentUrl();
        log.info("Category URL after clicking first category: {}", categoryURL);
        String expectedCategoryPageHeader="RX DIRECT THERMAL LABELS";
        //verify the category landing page header
        CategoryLandingPageObjects categoryPage=new CategoryLandingPageObjects(driver);
        String actualCategoryPageHeader=categoryPage.getCategoryLandingPageHeaderText();
        if(actualCategoryPageHeader.equalsIgnoreCase(expectedCategoryPageHeader))
        {
            log.info("Category Landing Page Header is correct");
            log.info("You are on: {}", actualCategoryPageHeader);
        }
        else
        {
            log.error("Category Landing Page Header is incorrect");
        }

        Thread.sleep(5000);

        //hover over a product and click add to cart
        //String productTitle="BLANK DIRECT THERMAL LABEL WHITE - 3-1/2 X 1 - ARXDT";
        //categoryPage.hoverOverProductAndClickAddToCart(productTitle);
        //categoryPage.hoverOverFirstProduct();
    
        //get the SKU from the properties file
        //String productSKU=properties.getProperty("productSKU");

        //get the product title from the properties file
        //String productTitle=properties.getProperty("productTitle1");
        String productTitle=properties.getProperty("productTitle2");

        

        //print the sku
        //log.info("Product SKU to be added to cart: {}", productSKU);

        //print the product title
        log.info("Product Title to be added to cart: {}", productTitle);

        //hover over product by SKU
        //categoryPage.hoverOverProductBySku(productSKU);

        categoryPage.hoverOverProductLinkByName(productTitle);

        Thread.sleep(2000);
        categoryPage.clickAddToCart();
        Thread.sleep(5000);

        //if purchase only once label is displayed, click on it
        boolean isPurchaseOnlyOnceLabelDisplayed=categoryPage.isPurchaseOnlyOnceLabelDisplayedInPopup();
        if(isPurchaseOnlyOnceLabelDisplayed)
        {
            log.info("Purchase Only Once Label is displayed in popup");
            categoryPage.clickPurchaseOnlyOnceLabelInPopup();
        }
        else
        {
            log.info("Purchase Only Once Label is not displayed in popup");
        }
        Thread.sleep(2000);


        categoryPage.clickAddToCartButtonInPopup();
        log.info("Clicked Add to Cart button in popup");

        Thread.sleep(3000);

        //click the view cart icon in the popup or wait 5 seconds and click the cart icon in the header
        boolean isViewCartButtonDisplayedInPopup=categoryPage.isViewCartButtonDisplayedInPopup();
        if(isViewCartButtonDisplayedInPopup)
        {
            log.info("View Cart Button is displayed in popup");
            categoryPage.clickViewCartButtonInPopup();
        }
        else
        {
            log.info("View Cart Button is not displayed in popup. Waiting 5 seconds and clicking cart icon in header");
            Thread.sleep(5000);
            categoryPage.clickCartIconInHeader();
        }

        CartPageObjects cartPage=new CartPageObjects(driver);
        String cartPageTitle=cartPage.getCartPageTitleText();
        String expectedCartPageTitle="Shopping Cart";

        if(cartPageTitle.equalsIgnoreCase(expectedCartPageTitle))
        {
            log.info("Navigated to Cart Page successfully");
            log.info("Cart Page Title: {}", cartPageTitle);
        }
        else
        {
            log.error("Failed to navigate to Cart Page");
        }
        Thread.sleep(2000);

        //click the checkout button on the cart page
        cartPage.clickCheckoutButton();
        log.info("Clicked Checkout Button on Cart Page");

        CheckoutShippingPageObjects checkoutShippingPage=new CheckoutShippingPageObjects(driver);
        //enter shipping information and continue to payment page
        Thread.sleep(2000);
        /* 
        boolean isSummaryContentVisible=checkoutShippingPage.isSummaryContentSectionDisplayed();
        if(isSummaryContentVisible)
        {
            log.info("Summary Content Section is displayed on Shipping Page");
        }
        else
        {
            log.error("Summary Content Section is not displayed on Shipping Page");
        }
        */

        //click the NEXT button in shipping address section
        checkoutShippingPage.clickNextButtonInShippingAddressSection();
        log.info("Clicked NEXT button in Shipping Address Section");
        Thread.sleep(2000);

        CheckoutReviewAndPaymentsPageObjects checkoutReviewAndPaymentsPage=new CheckoutReviewAndPaymentsPageObjects(driver);
        //check if ship to section is visible
        boolean isShipToSectionVisible=checkoutReviewAndPaymentsPage.isShipToSectionVisible();
        if(isShipToSectionVisible)
        {
            log.info("Ship To Section is visible on Review and Payments Page");
        }
        else
        {
            log.error("Ship To Section is not visible on Review and Payments Page");
        }

        //enter purchase order number, order name and order notes
        String poNumber="PO123457";
        checkoutReviewAndPaymentsPage.enterPurchaseOrderNumber(poNumber);
        log.info("Entered Purchase Order Number: {}", poNumber);
        String orderName="Test Order";
        checkoutReviewAndPaymentsPage.enterOrderName(orderName);
        log.info("Entered Order Name: {}", orderName);
        String orderNotes="Please deliver between 9 AM to 5 PM.";
        checkoutReviewAndPaymentsPage.enterOrderNotes(orderNotes);
        log.info("Entered Order Notes: {}", orderNotes);

        //verify on account payment option is selected
        boolean isOnAccountPaymentSelected=checkoutReviewAndPaymentsPage.isOnAccountPaymentOptionSelected();
        if(isOnAccountPaymentSelected)
        {
            log.info("On Account Payment Option is selected by default");
        }
        else
        {
            log.error("On Account Payment Option is not selected by default");
        }
        checkoutReviewAndPaymentsPage.clickPlaceOrderButton();
        log.info("Clicked Place Order Button");

        log.info("Current URL: {}", driver.getCurrentUrl());

        log.info("Current URL: {}", driver.getCurrentUrl());
        String orderConfirmationMessage=checkoutReviewAndPaymentsPage.getOrderConfirmationMessageText();
        log.info("Order Confirmation Message: {}", orderConfirmationMessage);
        String orderNumber=checkoutReviewAndPaymentsPage.getOrderNumberText();
        log.info("PO Number: {}", orderNumber);
        /*
        //check of any order error is displayed
        boolean isOrderErrorDisplayed=checkoutReviewAndPaymentsPage.isAnyErrorValidationMessagePresent();
        if(isOrderErrorDisplayed)
        {
            String orderErrorMessage=checkoutReviewAndPaymentsPage.getErrorValidationMessageText();
            log.error("Order could not be placed. Error Message: {}", orderErrorMessage);
            Assert.fail("Order Placement Failed due to error: " + orderErrorMessage);
        }
        else
        {
            log.info("Order placed successfully without any errors and validation messages.");
        }

        //String currentUrl = driver.getCurrentUrl();
         
        log.info("Current URL: {}", driver.getCurrentUrl());
        String orderConfirmationMessage=checkoutReviewAndPaymentsPage.getOrderConfirmationMessageText();
        log.info("Order Confirmation Message: {}", orderConfirmationMessage);
        String orderNumber=checkoutReviewAndPaymentsPage.getOrderNumberText();
        log.info("PO Number: {}", orderNumber);

        
        CheckoutOverviewPageObjects checkoutOverviewPage=new CheckoutOverviewPageObjects(driver);
        boolean isOrderConfirmationMessageVisible=checkoutOverviewPage.isOrderConfirmationMessageVisible();
        if(isOrderConfirmationMessageVisible)
        {
            String orderConfirmationMessageText=checkoutOverviewPage.getOrderConfirmationMessageText();
            log.info("Order Confirmation Message is visible: {}", orderConfirmationMessageText);
        }

            String orderNumber=checkoutOverviewPage.getOrderNumberText();
            log.info("PO Number: {}", orderNumber);
        */





        log.info("Quick Registered User Checkout Test Completed");
    }


}
