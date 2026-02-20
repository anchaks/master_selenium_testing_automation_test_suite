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
 * Test Class: TC_login_WrongUserName
 * Purpose: Validates that login fails appropriately with incorrect username
 * Author: Test Automation Team
 * 
 * This negative test case follows these validation steps:
 * 1. Navigate to login page (handled in BaseClass setup)
 * 2. Enter invalid username but valid password
 * 3. Click submit button
 * 4. Verify login failure by checking:
 *    - Logout button should NOT be present
 *    - Appropriate error message should be displayed
 * 5. Generate comprehensive test reports using ExtentReports
 * 
 * Expected Behavior: Application should reject login and show username error message
 */
@Listeners(ExtentReportManager.class)  // Attaches ExtentReport listener for detailed HTML reporting
public class TC_login_WrongUserName extends BaseClass
{
    // Initialize Log4j2 logger for this test class - enables structured logging
    private static final Logger logger = LogManager.getLogger(TC_login_WrongUserName.class);
    
    /**
     * Test Method: login()
     * Test ID: TC_login_WrongUserName
     * Test Type: Negative Testing
     * Objective: Verify that login fails appropriately when incorrect username is provided
     * 
     * Test Data:
     * - Username: "incorrectUser" (invalid)
     * - Password: "Password123" (valid)
     * 
     * Expected Results:
     * - Login should fail
     * - Logout button should NOT be visible
     * - Error message: "Your username is invalid!"
     */
    @Test
    public void login()
    {
        logger.info("=== Starting Negative Login Test Case (Wrong Username) ===");
        
        try {
            // Step 1: Initialize Login Page Object Model
            logger.info("Step 1: Initializing Login Page Object for negative username test");
            LoginPageObjects lp = new LoginPageObjects(driver);
            logger.debug("Login Page Object created successfully");

            // Step 2: Enter INVALID username credentials (this is the negative test scenario)
            logger.info("Step 2: Entering INVALID username credentials (negative test scenario)");
            String invalidUsername = "incorrectUser";
            lp.enterUserName(invalidUsername);
            logger.debug("Invalid username '{}' entered successfully", invalidUsername);
            logger.warn("Using incorrect username intentionally for negative testing");

            // Step 3: Enter valid password credentials
            logger.info("Step 3: Entering valid password credentials");
            String validPassword = "Password123";
            lp.enterPassword(validPassword);
            logger.debug("Valid password entered successfully (length: {} characters)", validPassword.length());

            // Step 4: Click submit button to attempt login
            logger.info("Step 4: Clicking submit button to attempt login (expecting failure)");
            lp.clickSubmit();
            logger.debug("Submit button clicked - login attempt made with invalid username");

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
                    logger.warn("⚠️ SECURITY CONCERN: Invalid username was accepted by the application");
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
            logger.info("Step 7: Setting up expected results for negative username test validation");
            String expectedErrorMsg = "Your username is invalid!";
            boolean expectedBtnPresent = false; // Logout button should NOT be present
            
            logger.debug("Expected logout button presence: {}", expectedBtnPresent);
            logger.debug("Expected error message: '{}'", expectedErrorMsg);

            // Step 9: Log current validation state for debugging
            logger.info("Step 8: Performing negative username test validations");
            logger.info("Negative Username Test Validation Results:");
            logger.info("  → Logout button displayed: {} (Expected: {})", btnPresent, expectedBtnPresent);
            logger.info("  → Actual error message: '{}'", actualErrorMsg);
            logger.info("  → Expected error message: '{}'", expectedErrorMsg);
            logger.info("  → Error message match: {}", actualErrorMsg.equals(expectedErrorMsg));
            logger.info("  → Login properly failed: {}", !btnPresent);

            // Step 10: Perform comprehensive negative test validation
            /*
             * For negative username testing, we validate that:
             * 1. Login failed (logout button NOT present)
             * 2. Appropriate username error message is displayed
             * 
             * Both conditions must be true for the test to pass
             * This ensures proper user authentication and error handling
             */
            if (!btnPresent && actualErrorMsg.equals(expectedErrorMsg)) {
                logger.info("✅ NEGATIVE USERNAME TEST CASE PASSED - Login failed as expected with correct error message");
                logger.info("  ✓ Login properly rejected with invalid username");
                logger.info("  ✓ Logout button is not present (login failed)");
                logger.info("  ✓ Correct username error message displayed to user");
                logger.info("  ✓ Application authentication working correctly");
                logger.info("  ✓ User receives proper feedback about username validation");
                
            } else {
                // Log detailed failure information for negative username test
                logger.error("❌ NEGATIVE USERNAME TEST CASE FAILED - Login behavior not as expected");
                
                if (btnPresent) {
                    logger.error("  ✗ CRITICAL SECURITY RISK: Logout button is present - Login succeeded with invalid username!");
                    logger.error("  ✗ AUTHENTICATION BYPASS: Application accepted non-existent username");
                    logger.error("  ✗ POTENTIAL VULNERABILITY: User authentication may be compromised");
                }
                
                if (!actualErrorMsg.equals(expectedErrorMsg)) {
                    logger.error("  ✗ Username error message mismatch - Expected: '{}', Actual: '{}'", 
                               expectedErrorMsg, actualErrorMsg);
                    logger.error("  ✗ User may not receive proper feedback about username validation failure");
                    logger.error("  ✗ Error message inconsistency may confuse users");
                }
                
                // Fail the test with comprehensive error information
                String failureReason = String.format(
                    "Negative username login test failed. Expected login to fail but got unexpected behavior. " +
                    "Logout button present: %s (should be false), " +
                    "Error message: '%s' (expected: '%s'). " +
                    "This may indicate a security vulnerability in username validation.", 
                    btnPresent, actualErrorMsg, expectedErrorMsg);
                
                Assert.fail(failureReason);
            }
            
        } catch (AssertionError e) {
            // Re-throw assertion errors (these are test failures)
            logger.error("❌ NEGATIVE USERNAME TEST ASSERTION FAILED", e);
            throw e;
            
        } catch (Exception e) {
            // Catch any other unexpected exceptions during test execution
            logger.error("❌ NEGATIVE USERNAME TEST FAILED - Unexpected exception occurred", e);
            logger.error("Exception type: {}", e.getClass().getSimpleName());
            logger.error("Exception message: {}", e.getMessage());
            
            // Re-throw as assertion failure with context
            Assert.fail("Negative username login test failed due to unexpected exception: " + e.getMessage(), e);
            
        } finally {
            // Final logging regardless of test outcome
            logger.info("=== Negative Login Test Case (Wrong Username) Execution Completed ===");
        }
    }
}
