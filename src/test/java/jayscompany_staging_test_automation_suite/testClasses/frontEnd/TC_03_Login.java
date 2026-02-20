package jayscompany_staging_test_automation_suite.testClasses.frontEnd;

import org.testng.annotations.Test;

import jayscompany_staging_test_automation_suite.baseClasses.BaseClass;
import jayscompany_staging_test_automation_suite.pageObjects.AccountPageObjects;
import jayscompany_staging_test_automation_suite.pageObjects.HomePageObjects;
import jayscompany_staging_test_automation_suite.pageObjects.LoginPageObjects;


public class TC_03_Login extends BaseClass
{
    @Test
    public void loginTest() 
    {
        log.info("Starting Login Test");
        
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

        log.info("Login Test Completed");
    }
}
