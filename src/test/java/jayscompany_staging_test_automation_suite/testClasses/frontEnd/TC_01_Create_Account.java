package jayscompany_staging_test_automation_suite.testClasses.frontEnd;

import org.testng.Assert;
import org.testng.annotations.Test;

import jayscompany_staging_test_automation_suite.baseClasses.BaseClass;
import jayscompany_staging_test_automation_suite.pageObjects.AccountPageObjects;
import jayscompany_staging_test_automation_suite.pageObjects.CreateAccountPageObjects;
import jayscompany_staging_test_automation_suite.pageObjects.HomePageObjects;



public class TC_01_Create_Account extends BaseClass
{
    @Test
    public void createAccount() throws InterruptedException
    {
        //get original url
        String originalURL = driver.getCurrentUrl();
        log.info("Original URL: {}", originalURL);

        HomePageObjects homePage = new HomePageObjects(driver);
        homePage.clickCreateAccountAfterHover();
        log.info("modal popup opened after clicking create account link");
        homePage.clickCreateAccountRadioButtonInModalPopup();
        homePage.clickContinueButtonInModalPopup();

        

        log.info("Current URL after clicking Create Account: {}", driver.getCurrentUrl());

        CreateAccountPageObjects createAccountPage = new CreateAccountPageObjects(driver);

         // Verify that the Create Account page title is displayed
        Assert.assertTrue(createAccountPage.isCreateAccountPageTitleDisplayed(), "Create Account page title is not displayed.");
        log.info("Create Account page title is displayed.");
        
        String company_name=properties.getProperty("company_name_required");
        createAccountPage.enterCompanyName(company_name);
        log.info("Entered Company Name: {}", company_name);

        String first_name=properties.getProperty("contact_first_name2_required");
        createAccountPage.enterFirstName(first_name);
        log.info("Entered First Name: {}", first_name);

        String last_name=properties.getProperty("contact_last_name1_required");
        createAccountPage.enterLastName(last_name);
        log.info("Entered Last Name: {}", last_name);

        Thread.sleep(2000);

        String phone_number=properties.getProperty("company_phone_number1_required");
        createAccountPage.enterTelephone(phone_number);
        log.info("Entered Phone Number: {}", phone_number);

        String street_address=properties.getProperty("street_address_required");
        createAccountPage.enterStreetAddress(street_address);
        log.info("Entered Street Address: {}", street_address);

        String city=properties.getProperty("city_required");
        createAccountPage.enterCity(city);
        log.info("Entered City: {}", city);

        //select the state from the dropdown
        String state=properties.getProperty("state_dropdown_text_required");
        createAccountPage.selectState(state);
        log.info("Selected State: {}", state);

        String zipCode=properties.getProperty("zip_code_required");
        createAccountPage.enterZipCode(zipCode);
        log.info("Entered Zip Code: {}", zipCode);

        String email=properties.getProperty("email2_required");
        createAccountPage.enterEmailAddress(email);
        log.info("Entered Email Address: {}", email);

        String password=properties.getProperty("password_required");
        createAccountPage.enterPassword(password);
        log.info("Entered Password: {}", password);

        Thread.sleep(1500); // Wait for password field to process and confirm field to be ready

        String confirm_password=properties.getProperty("confirm_password_required");
        createAccountPage.enterConfirmPassword(confirm_password);
        log.info("Entered Confirm Password: {}", confirm_password);
        
        createAccountPage.clickCreateAccountButton();
        log.info("Clicked on Create Account button");

        //check if duplicate account error message is displayed, if yes, log the error and return else go ahead with verification of account creation
        if(createAccountPage.isDuplicateAccountErrorMessageDisplayed())
        {
            String errorMessage=createAccountPage.getDuplicateAccountErrorMessage();
            log.error("Create Account Failed: {}", errorMessage);
            return;
        }
        
         // Verify that the account was created successfully by checking for a success message or redirection
         //if account dashboard is displayed, get the contact information text and verify the email
         AccountPageObjects accountPage=new AccountPageObjects(driver);
        boolean isAccountDashboardDisplayed=accountPage.isAccountDashboardSectionDisplayed();
        if(isAccountDashboardDisplayed)
        {
            log.info("Account Dashboard is displayed");
            String contactInfoText=accountPage.getContactInformationText();
            log.info("Contact Information Text: {}", contactInfoText);
            
            if(contactInfoText.contains(email))
            {
                log.info("Create Account Successful: Email found in Contact Information");
            }
            else
            {
                log.error("Create Account Failed: Email not found in Contact Information");
            }
        }
        else
        {
            log.error("Account Dashboard is not displayed. Create Account might have failed.");
        }
       

       
        
       

        
        
        

    }
    

}
