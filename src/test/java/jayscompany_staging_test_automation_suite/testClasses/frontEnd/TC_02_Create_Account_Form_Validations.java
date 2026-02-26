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

        log.info("=====STARTING CREATE ACCOUNT FORM VALIDATIONS TEST======");
       //get original url
        String originalURL = driver.getCurrentUrl();
        log.info("Original URL: {}", originalURL);

        HomePageObjects homePage = new HomePageObjects(driver);
        homePage.clickCreateAccountAfterHover();
        log.info("Modal popup opened after clicking create account link");
        homePage.clickCreateAccountRadioButtonInModalPopup();
        homePage.clickContinueButtonInModalPopup();

        String expectedURL ="https://staging-5em2ouy-3xwbb4bntbucg.us.platformsh.site/customer/account/create";
        log.info("Expected URL for Create Account page: {}", expectedURL);


        //get current url
        String currentURL = driver.getCurrentUrl();
        log.info("Current URL after clicking Create Account: {}", currentURL);

        CreateAccountPageObjects createAccountPage = new CreateAccountPageObjects(driver);
        
        // Verify that the URL has changed
        if (currentURL.equalsIgnoreCase(expectedURL)) {
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

        //check blank submissions on clicking the create account button and get the screenshot of the error message

        log.info("====STARTING BLANK SUBMISSION VALIDATIONS FOR CREATE ACCOUNT FORM====");

        String companyNameErrorText= createAccountPage.getCompanyNameErrorMessage();
        log.info("Company Name Error Message: {}", companyNameErrorText);
        Assert.assertTrue(createAccountPage.isCompanyNameErrorMessageDisplayed(), "Company Name blank error message is not displayed.");

        String firstNameErrorText = createAccountPage.getFirstNameErrorMessage();
        log.info("First Name Error Message: {}", firstNameErrorText);
        Assert.assertTrue(createAccountPage.isFirstNameErrorMessageDisplayed(), "First Name blank error message is not displayed.");
        

        String lastNameErrorText = createAccountPage.getLastNameErrorMessage();
        log.info("Last Name Error Message: {}", lastNameErrorText);
        Assert.assertTrue(createAccountPage.isLastNameErrorMessageDisplayed(), "Last Name blank error message is not displayed.");

        String phoneNumberErrorText= createAccountPage.getTelephoneErrorMessage();
        log.info("Phone Number Error Message: {}", phoneNumberErrorText);
        Assert.assertTrue(createAccountPage.isTelephoneErrorMessageDisplayed(), "Phone Number blank error message is not displayed.");

        String streetAddresErrorText= createAccountPage.getStreetAddress1ErrorMessage();
        log.info("Street Address Error Message: {}", streetAddresErrorText);
        Assert.assertTrue(createAccountPage.isStreetAddress1ErrorMessageDisplayed(), "Street Address blank error message is not displayed.");

        String cityErrorText= createAccountPage.getCityErrorMessage();
        log.info("City Error Message: {}", cityErrorText);
        Assert.assertTrue(createAccountPage.isCityErrorMessageDisplayed(), "City blank error message is not displayed.");

        String stateErrorText= createAccountPage.getStateErrorMessage();
        log.info("State Error Message: {}", stateErrorText);
        Assert.assertTrue(createAccountPage.isStateErrorMessageDisplayed(), "State blank error message is not displayed.");

        String postCodeErrorText= createAccountPage.getPostCodeErrorMessage();
        log.info("Post Code Error Message: {}", postCodeErrorText);
        Assert.assertTrue(createAccountPage.isPostCodeErrorMessageDisplayed(), "Post Code blank error message is not displayed.");

        String emailErrorText = createAccountPage.getEmailAddressErrorMessage();
        log.info("Email Error Message: {}", emailErrorText);
        Assert.assertTrue(createAccountPage.isEmailAddressErrorMessageDisplayed(), "Email blank error message is not displayed.");


        String passwordErrorText= createAccountPage.getPasswordErrorMessage();
        log.info("Password Error Message: {}", passwordErrorText);
        Assert.assertTrue(createAccountPage.isPasswordErrorMessageDisplayed(), "Password blank error message is not displayed.");

        String confirmPasswordErrorText= createAccountPage.getConfirmPasswordErrorMessage();
        log.info("Confirm Password Error Message: {}", confirmPasswordErrorText);
        Assert.assertTrue(createAccountPage.isConfirmPasswordErrorMessageDisplayed(), "Confirm Password blank error message is not displayed.");

        log.info("====BLANK SUBMISSION VALIDATIONS FOR CREATE ACCOUNT FORM COMPLETED====");


        log.info("==EMAIL FORMATTING VALIDATION STARTED==");

        //enter invalid email and check the validation message
        String invalidEmail=properties.getProperty("invalid_email_format");
        log.info("Entering invalid email: {}", invalidEmail);
        createAccountPage.enterEmailAddress(invalidEmail);
        createAccountPage.scrollToAndClickCreateAccountButton();
        String emailFormatErrorText = createAccountPage.getEmailAddressErrorMessage();
        log.info("Email Format Error Message: {}", emailFormatErrorText);
        if(createAccountPage.isEmailAddressErrorMessageDisplayed())
        {
            log.info("Email formatting validation is working as expected.");
        }
        else
        {
            log.error("Email formatting validation is not working.");
            Assert.fail("Email formatting validation is not working.");
        }

        log.info("==EMAIL FORMATTING VALIDATION COMPLETED==");

        log.info("==PASSWORD STRENGTH VALIDATION STARTED==");

        //enter weak password and check the validation message
        String weakPassword=properties.getProperty("weak_password");
        log.info("Entering weak password: {}", weakPassword);
        createAccountPage.enterPassword(weakPassword);
        //stength meter message 
        String passwordStrengthMeterText = createAccountPage.getPasswordStrengthLevel();
        log.info("Password Strength Meter Text: {}", passwordStrengthMeterText);
        //get the error message on entering weak password and verify
        String passwordStrengthErrorText = createAccountPage.getPasswordErrorMessage();
        log.info("Password Strength Error Message: {}", passwordStrengthErrorText);
        
        if(passwordStrengthMeterText.equalsIgnoreCase("weak"))
        {
            log.info("Password strength validation is working as expected.It is showing the weak password error message: {}");
        }
        else
        {
            log.error("Password strength validation is not working.");
            Assert.fail("Password strength validation is not working.");
        }

        String strongPassword=properties.getProperty("strong_password");
        log.info("Entering strong password: {}", strongPassword);
        createAccountPage.enterPassword(strongPassword);
        passwordStrengthMeterText = createAccountPage.getPasswordStrengthLevel();
        log.info("Password Strength Meter Text: {}", passwordStrengthMeterText);

        if(passwordStrengthMeterText.equalsIgnoreCase("strong"))
        {
            log.info("Password strength meter is showing strong password strength as expected.");
        }
        else
        {
            log.error("Password strength meter is not showing medium password strength as expected.");
            Assert.fail("Password strength meter is not showing medium password strength as expected.");
        }

        String mediumPassword=properties.getProperty("medium_password");
        log.info("Entering medium password: {}", mediumPassword);
        createAccountPage.enterPassword(mediumPassword);
        passwordStrengthMeterText = createAccountPage.getPasswordStrengthLevel();
        log.info("Password Strength Meter Text: {}", passwordStrengthMeterText);

        if(passwordStrengthMeterText.equalsIgnoreCase("medium"))
        {
            log.info("Password strength meter is showing medium password strength as expected.");
        }
        else
        {
            log.error("Password strength meter is not showing medium password strength as expected.");
            Assert.fail("Password strength meter is not showing medium password strength as expected.");
        }

        log.info("==PASSWORD STRENGTH VALIDATION COMPLETED==");

        log.info("=====CONFIRM PASSWORD MATCH VALIDATION STARTED=====");

        //enter password and confirm password and check whether the validation for confirm password is working or not
        String password=properties.getProperty("front_end_user_password");
        String nonMatchingConfirmPassword="NonMatching"+password;
        log.info("Entering password: {}", password);
        log.info("Entering non-matching confirm password: {}", nonMatchingConfirmPassword); 
        createAccountPage.enterPassword(password);
        createAccountPage.enterConfirmPassword(nonMatchingConfirmPassword);

        createAccountPage.scrollToAndClickCreateAccountButton();
        String confirmPasswordErrorText2= createAccountPage.getConfirmPasswordErrorMessage();
        log.info("Confirm Password Error Message on non-matching passwords: {}", confirmPasswordErrorText2);
        if(createAccountPage.isConfirmPasswordErrorMessageDisplayed())
        {
            log.info("Confirm password match validation is working as expected.");
        }
        else
        {
            log.error("Confirm password match validation is not working.");
            Assert.fail("Confirm password match validation is not working.");
        }

        


        log.info("=====CREATE ACCOUNT FORM VALIDATIONS TEST COMPLETED======");

    }


        
    

}
