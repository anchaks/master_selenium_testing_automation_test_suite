package jayscompany_staging_test_automation_suite.testClasses.frontEnd;

import org.testng.Assert;
import org.testng.annotations.Test;

import jayscompany_staging_test_automation_suite.baseClasses.BaseClass;
import jayscompany_staging_test_automation_suite.pageObjects.CreateAccountPageObjects;
import jayscompany_staging_test_automation_suite.pageObjects.HomePageObjects;



public class TC_01_Create_Account extends BaseClass
{
    @Test
    public void createAccount()
    {
        //get original url
        String originalURL = driver.getCurrentUrl();
        log.info("Original URL: {}", originalURL);

        HomePageObjects homePage = new HomePageObjects(driver);
        homePage.clickCreateAccountAfterHover();
        homePage.clickCreateAccountRadioButtonInModalPopup();

        //get current url
        String currentURL = driver.getCurrentUrl();
        String expectedURL = currentURL + "/customer/account/create/";

        log.info("Current URL after clicking Create Account: {}", currentURL);

        CreateAccountPageObjects createAccountPage = new CreateAccountPageObjects(driver);
        
        // Verify that the URL has changed
        if (!currentURL.equals(originalURL) && currentURL.equals(expectedURL)) {
            log.info("Navigation to Create Account page successful.");
        } else {
            log.error("Navigation to Create Account page failed.");
            Assert.fail("Navigation to Create Account page failed.");
        }
        
        // Verify that the Create Account page title is displayed
        Assert.assertTrue(createAccountPage.isCreateAccountPageTitleDisplayed(), "Create Account page title is not displayed.");
        log.info("Create Account page title is displayed.");

        //
        
        

    }
    

}
