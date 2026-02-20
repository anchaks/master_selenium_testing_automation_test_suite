package jayscompany_staging_test_automation_suite.baseClasses;

import org.openqa.selenium.WebDriver;

// DriverProvider is an interface to ensure implementing classes provide a WebDriver instance.
// This allows for consistent access to WebDriver across different base test classes.
/**
 * DriverProvider interface defines a contract for providing a Selenium WebDriver instance.
 * Any class that implements this interface must supply an implementation of getDriver().
 * This is useful for base test classes to allow test code to access the browser driver in a uniform way.
 */
public interface DriverProvider
{
    /**
     * Returns the WebDriver instance managed by the implementing class.
     * @return WebDriver instance for browser automation
     */
    WebDriver getDriver();
    
}
