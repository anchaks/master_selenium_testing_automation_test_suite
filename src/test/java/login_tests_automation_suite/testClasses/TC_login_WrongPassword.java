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
 * Test Class: TC_login_WrongPassword
 * Purpose: Validates that login fails appropriately with incorrect password
 * Author: Test Automation Team
 * 
 * This negative test case follows these validation steps:
 * 1. Navigate to login page (handled in BaseClass setup)
 * 2. Enter valid username but invalid password
 * 3. Click submit button
 * 4. Verify login failure by checking:
 *    - Logout button should NOT be present
 *    - Appropriate error message should be displayed
 * 5. Generate comprehensive test reports using ExtentReports
 * 
 * Expected Behavior: Application should reject login and show error message
 */
@Listeners(ExtentReportManager.class)  // Attaches ExtentReport listener for detailed HTML reporting
public class TC_login_WrongPassword extends BaseClass
{
    // Initialize Log4j2 logger for this test class - enables structured logging
    private static final Logger logger = LogManager.getLogger(TC_login_WrongPassword.class);
    
    /**
     * Test Method: login()
     * Test ID: TC_login_WrongPassword
     * Test Type: Negative Testing
     * Objective: Verify that login fails appropriately when incorrect password is provided
     * 
     * Test Data:
     * - Username: "student" (valid)
     * - Password: "inCorrectPassword" (invalid)
     * 
     * Expected Results:
     * - Login should fail
     * - Logout button should NOT be visible
     * - Error message: "Your password is invalid!"
     */
    @Test
    public void login()
    {
        logger.info("=== Starting Negative Login Test Case (Wrong Password) ===");
        
        try {
            // Step 1: Initialize Login Page Object Model
            logger.info("Step 1: Initializing Login Page Object for negative test");
            LoginPageObjects lp = new LoginPageObjects(driver);
            logger.debug("Login Page Object created successfully");

            // Step 2: Enter valid username credentials
            logger.info("Step 2: Entering valid username credentials");
            String username = "student";
            lp.enterUserName(username);
            logger.debug("Valid username '{}' entered successfully", username);

            // Step 3: Enter INVALID password credentials (this is the negative test scenario)
            logger.info("Step 3: Entering INVALID password credentials (negative test scenario)");
            String invalidPassword = "inCorrectPassword";
            lp.enterPassword(invalidPassword);
            logger.debug("Invalid password entered successfully (length: {} characters)", invalidPassword.length());
            logger.warn("Using incorrect password intentionally for negative testing");

            // Step 4: Click submit button to attempt login
            logger.info("Step 4: Clicking submit button to attempt login (expecting failure)");
            lp.clickSubmit();
            logger.debug("Submit button clicked - login attempt made with invalid credentials");

            // Step 5: Initialize logout button presence check
            logger.info("Step 5: Checking for logout button presence (should be false)");
            boolean btnPresent = false;

            /*
             * Step 6: Attempt to access success page elements
             * We expect this to either:
             * a) Not find the logout button (btnPresent remains false)
             * b) Throw an exception if success page elements don't exist
             * 
             * This is wrapped in try-catch because we EXPECT failure in negative testing
             */
            try {
                logger.debug("Attempting to create Login Success Page Object (may fail - expected behavior)");
                LoginSuccessPageObject lspo = new LoginSuccessPageObject(driver);
                btnPresent = lspo.isLogoutBtnDisplayed();
                logger.debug("Logout button check completed - Present: {}", btnPresent);
                
                if (btnPresent) {
                    logger.warn("⚠️ WARNING: Logout button found! This suggests login may have succeeded unexpectedly");
                }
                
            } catch (Exception e) {
                // This exception is EXPECTED in negative testing - login should fail
                logger.debug("Expected exception caught during success page access: {} - {}", 
                           e.getClass().getSimpleName(), e.getMessage());
                logger.info("✓ Success page elements not accessible (expected behavior for failed login)");
                btnPresent = false; // Ensure button presence is false
            }

            // Step 7: Capture error message from login page
            logger.info("Step 6: Capturing error message from login page");
            String actualErrorMsg = lp.getUserErrorMsg();
            logger.debug("Actual error message captured: '{}'", actualErrorMsg);

            // Step 8: Define expected results for negative test validation
            logger.info("Step 7: Setting up expected results for negative test validation");
            String expectedErrorMsg = "Your password is invalid!";
            boolean expectedBtnPresent = false; // Logout button should NOT be present
            
            logger.debug("Expected logout button presence: {}", expectedBtnPresent);
            logger.debug("Expected error message: '{}'", expectedErrorMsg);

            // Step 9: Log current validation state for debugging
            logger.info("Step 8: Performing negative test validations");
            logger.info("Negative Test Validation Results:");
            logger.info("  → Logout button displayed: {} (Expected: {})", btnPresent, expectedBtnPresent);
            logger.info("  → Actual error message: '{}'", actualErrorMsg);
            logger.info("  → Expected error message: '{}'", expectedErrorMsg);
            logger.info("  → Error message match: {}", actualErrorMsg.equals(expectedErrorMsg));
            logger.info("  → Login properly failed: {}", !btnPresent);

            // Step 10: Perform comprehensive negative test validation
            /*
             * For negative testing, we validate that:
             * 1. Login failed (logout button NOT present)
             * 2. Appropriate error message is displayed
             * 
             * Both conditions must be true for the test to pass
             */
            if (!btnPresent && actualErrorMsg.equals(expectedErrorMsg)) {
                logger.info("✅ NEGATIVE TEST CASE PASSED - Login failed as expected with correct error message");
                logger.info("  ✓ Login properly rejected with invalid credentials");
                logger.info("  ✓ Logout button is not present (login failed)");
                logger.info("  ✓ Correct error message displayed to user");
                logger.info("  ✓ Application security working correctly");
                
            } else {
                // Log detailed failure information for negative test
                logger.error("❌ NEGATIVE TEST CASE FAILED - Login behavior not as expected");
                
                if (btnPresent) {
                    logger.error("  ✗ CRITICAL: Logout button is present - Login may have succeeded with invalid password!");
                    logger.error("  ✗ SECURITY RISK: Application accepted invalid credentials");
                }
                
                if (!actualErrorMsg.equals(expectedErrorMsg)) {
                    logger.error("  ✗ Error message mismatch - Expected: '{}', Actual: '{}'", 
                               expectedErrorMsg, actualErrorMsg);
                    logger.error("  ✗ User may not receive proper feedback about login failure");
                }
                
                // Fail the test with comprehensive error information
                String failureReason = String.format(
                    "Negative login test failed. Expected login to fail but got unexpected behavior. " +
                    "Logout button present: %s (should be false), " +
                    "Error message: '%s' (expected: '%s')", 
                    btnPresent, actualErrorMsg, expectedErrorMsg);
                
                Assert.fail(failureReason);
            }
            
        } catch (AssertionError e) {
            // Re-throw assertion errors (these are test failures)
            logger.error("❌ NEGATIVE TEST ASSERTION FAILED", e);
            throw e;
            
        } catch (Exception e) {
            // Catch any other unexpected exceptions during test execution
            logger.error("❌ NEGATIVE TEST FAILED - Unexpected exception occurred", e);
            logger.error("Exception type: {}", e.getClass().getSimpleName());
            logger.error("Exception message: {}", e.getMessage());
            
            // Re-throw as assertion failure with context
            Assert.fail("Negative login test failed due to unexpected exception: " + e.getMessage(), e);
            
        } finally {
            // Final logging regardless of test outcome
            logger.info("=== Negative Login Test Case (Wrong Password) Execution Completed ===");
        }
    }
}
