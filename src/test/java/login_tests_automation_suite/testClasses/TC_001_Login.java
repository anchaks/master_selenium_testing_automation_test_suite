package login_tests_automation_suite.testClasses;

// TestNG framework imports for test execution and assertions
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import login_tests_automation_suite.baseClass.BaseClass;
import login_tests_automation_suite.pageObjects.LoginPageObjects;
import login_tests_automation_suite.pageObjects.LoginSuccessPageObject;
import login_tests_automation_suite.utilities.ExtentReportManager;

// Log4j2 imports for logging mechanism
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

// Project-specific imports


/**
 * Test Class: TC_001_Login
 * Purpose: Validates the login functionality of the ParaBank application
 * Author: Test Automation Team
 * 
 * This test class uses the Page Object Model (POM) pattern and follows these steps:
 * 1. Navigate to login page (handled in BaseClass setup)
 * 2. Enter valid credentials
 * 3. Click submit button
 * 4. Verify successful login by checking success message and logout button presence
 * 5. Generate comprehensive test reports using ExtentReports
 */
@Listeners(ExtentReportManager.class)  // Attaches ExtentReport listener for detailed HTML reporting
public class TC_001_Login extends BaseClass
{
    // Initialize Log4j2 logger for this test class - enables structured logging
    private static final Logger logger = LogManager.getLogger(TC_001_Login.class);
    
    /**
     * Test Method: login()
     * Test ID: TC_001
     * Objective: Verify that a user can successfully login with valid credentials
     * 
     * Test Data:
     * - Username: "student"
     * - Password: "Password123"
     * 
     * Expected Result:
     * - Success message: "Logged In Successfully"
     * - Logout button should be visible
     */
    @Test(description="Verify user can log in with valid credentials")
    public void login()
    {
        logger.info("=== Starting Login Test Case (TC_001) ===");
        
        try {
            // Step 1: Initialize Login Page Object Model
            logger.info("Step 1: Initializing Login Page Object");
            LoginPageObjects lp = new LoginPageObjects(driver);
            logger.debug("Login Page Object created successfully");

            // Step 2: Enter username credentials
            logger.info("Step 2: Entering username credentials");
            String username = "student";
            lp.enterUserName(username);
            logger.debug("Username '{}' entered successfully", username);

            // Step 3: Enter password credentials  
            logger.info("Step 3: Entering password credentials");
            String password = "Password123";
            lp.enterPassword(password);
            logger.debug("Password entered successfully (length: {} characters)", password.length());

            // Step 4: Click submit button to perform login
            logger.info("Step 4: Clicking submit button to perform login");
            lp.clickSubmit();
            logger.debug("Submit button clicked successfully");

            // Step 5: Initialize Login Success Page Object for verification
            logger.info("Step 5: Initializing Login Success Page Object for verification");
            LoginSuccessPageObject lspo = new LoginSuccessPageObject(driver);
            logger.debug("Login Success Page Object created successfully");

            // Step 6: Capture actual results for verification
            logger.info("Step 6: Capturing actual results for verification");
            
            // Get the success message displayed on the page
            String actualMessage = lspo.getLoginSuccessText();
            logger.debug("Actual success message captured: '{}'", actualMessage);
            
            // Check if logout button is present/displayed
            boolean isLogoutBtnPresent = lspo.isLogoutBtnDisplayed();
            logger.debug("Logout button presence status: {}", isLogoutBtnPresent);

            // Step 7: Define expected results for comparison
            logger.info("Step 7: Setting up expected results for validation");
            String expectedMessage = "Logged In Successfully";
            logger.debug("Expected success message: '{}'", expectedMessage);

            // Step 8: Perform assertions and validations
            logger.info("Step 8: Performing test validations");
            
            // Log current state for debugging
            logger.info("Validation Results:");
            logger.info("  → Logout button displayed: {}", isLogoutBtnPresent);
            logger.info("  → Actual message: '{}'", actualMessage);
            logger.info("  → Expected message: '{}'", expectedMessage);
            logger.info("  → Message match: {}", actualMessage.equals(expectedMessage));

            // Perform comprehensive validation
            if (actualMessage.equals(expectedMessage) && isLogoutBtnPresent) {
                logger.info("✅ TEST CASE PASSED - Login functionality working correctly");
                logger.info("  ✓ Success message matches expected value");
                logger.info("  ✓ Logout button is visible and accessible");
            } else {
                // Log detailed failure information before failing the test
                logger.error("❌ TEST CASE FAILED - Login validation failed");
                
                if (!actualMessage.equals(expectedMessage)) {
                    logger.error("  ✗ Message mismatch - Expected: '{}', Actual: '{}'", 
                                expectedMessage, actualMessage);
                }
                
                if (!isLogoutBtnPresent) {
                    logger.error("  ✗ Logout button is not displayed or accessible");
                }
                
                // Fail the test with descriptive message
                Assert.fail("Login test failed - Success message or logout button validation failed. " +
                           "Expected message: '" + expectedMessage + "', Actual: '" + actualMessage + "', " +
                           "Logout button present: " + isLogoutBtnPresent);
            }
            
        } catch (Exception e) {
            // Catch any unexpected exceptions during test execution
            logger.error("❌ TEST CASE FAILED - Unexpected exception occurred during login test", e);
            logger.error("Exception type: {}", e.getClass().getSimpleName());
            logger.error("Exception message: {}", e.getMessage());
            
            // Re-throw as assertion failure with context
            Assert.fail("Login test failed due to unexpected exception: " + e.getMessage(), e);
            
        } finally {
            // Final logging regardless of test outcome
            logger.info("=== Login Test Case (TC_001) Execution Completed ===");
        }
    }
}
