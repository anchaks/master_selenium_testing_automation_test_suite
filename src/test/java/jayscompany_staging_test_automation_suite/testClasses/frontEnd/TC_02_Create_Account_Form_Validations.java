package jayscompany_staging_test_automation_suite.testClasses.frontEnd;

import org.testng.Assert;
import org.testng.annotations.Test;

import jayscompany_staging_test_automation_suite.baseClasses.BaseClass;
import jayscompany_staging_test_automation_suite.pageObjects.CreateAccountPageObjects;
import jayscompany_staging_test_automation_suite.pageObjects.HomePageObjects;



public class TC_02_Create_Account_Form_Validations extends BaseClass
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

        //check whether the  blank validation message are coming or not by scrolling down to the Create Account button and then clicking it. 
        //createAccountPage.clickCreateAccountButton();
        createAccountPage.scrollToAndClickCreateAccountButton();

        Assert.assertTrue(createAccountPage.isCompanyNameBlankErrorMessageDisplayed(), "Company Name blank error message is not displayed.");
        log.info("Company Name blank error message is displayed.");
        Assert.assertTrue(createAccountPage.isFirstNameBlankErrorMessageDisplayed(), "First Name blank error message is not displayed.");
        log.info("First Name blank error message is displayed.");
        Assert.assertTrue(createAccountPage.isLastNameBlankErrorMessageDisplayed(), "Last Name blank error message is not displayed.");
        log.info("Last Name blank error message is displayed.");
        Assert.assertTrue(createAccountPage.isTelephoneBlankErrorMessageDisplayed(), "Telephone blank error message is not displayed.");
        log.info("Telephone blank error message is displayed.");
        Assert.assertTrue(createAccountPage.isStreetAddress1BlankErrorMessageDisplayed(), "Street Address 1 blank error message is not displayed.");
        log.info("Street Address 1 blank error message is displayed.");
        Assert.assertTrue(createAccountPage.isCityBlankErrorMessageDisplayed(), "City blank error message is not displayed.");
        log.info("City blank error message is displayed.");
        Assert.assertTrue(createAccountPage.isPostCodeBlankErrorMessageDisplayed(), "Post Code blank error message is not displayed.");
        log.info("Post Code blank error message is displayed.");
        Assert.assertTrue(createAccountPage.isEmailAddressBlankErrorMessageDisplayed(), "Email Address blank error message is not displayed.");
        log.info("Email Address blank error message is displayed.");
        Assert.assertTrue(createAccountPage.isPasswordBlankErrorMessageDisplayed(), "Password blank error message is not displayed.");
        log.info("Password blank error message is displayed.");
        Assert.assertTrue(createAccountPage.isConfirmPasswordBlankErrorMessageDisplayed(), "Confirm Password blank error message is not displayed.");
        log.info("Confirm Password blank error message is displayed.");

        //now we check if password are matching or not after entering all mandatory fields
        createAccountPage.enterCompanyName("Test Company");
        createAccountPage.enterFirstName("John");
        createAccountPage.enterLastName("Doe");
        createAccountPage.enterTelephone("1234567890");
        createAccountPage.enterStreetAddress("123 Test St");
        createAccountPage.enterCity("Test City");
        createAccountPage.enterPostCode("12345");
        createAccountPage.enterEmailAddress("john.doe@example.com");
        createAccountPage.enterPassword("Test@1234");
        createAccountPage.enterConfirmPassword("Test@12345");
        createAccountPage.clickCreateAccountButton();

        String expectedPasswordErrorMessage="Please enter the same value again.";
        String actualPasswordErrorMessage=createAccountPage.getPasswordErrorMessage();
        Assert.assertEquals(actualPasswordErrorMessage, expectedPasswordErrorMessage, "Password mismatch error message is not as expected.");
        log.info("Password mismatch error message is displayed as expected.");
        
        

    }
    

}
