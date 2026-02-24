package jayscompany_staging_test_automation_suite.pageObjects;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CreateAccountPageObjects 
{
    public WebDriver driver;
    public WebDriverWait wait;

    public CreateAccountPageObjects(WebDriver driver)
    {
        this.driver=driver;
        wait=new WebDriverWait(driver, java.time.Duration.ofSeconds(30));
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath="//div//h1[@class='page-title']") WebElement createAccountPageTitle;
    //verify that this field is present in the create account page
    public boolean isCreateAccountPageTitleDisplayed()
    {
        wait.until(ExpectedConditions.visibilityOf(createAccountPageTitle));
        return createAccountPageTitle.isDisplayed();
    }

    @FindBy(xpath="//input[@id='about_us']") WebElement aboutUsField;
    @FindBy(xpath="//input[@id='business_type']") WebElement businessTypeField;
    @FindBy(xpath="//input[@id='spire_company_name']") WebElement companyNameField;
    @FindBy(xpath="//input[@id='email_invoice']") WebElement emailInvoiceField;
    @FindBy(xpath="//input[@id='firstname']") WebElement firstNameField;
    @FindBy(xpath="//input[@id='lastname']") WebElement lastNameField;
    @FindBy(xpath="//input[@id='telephone']") WebElement telephoneField;
    @FindBy(xpath="//input[@id='street_1']") WebElement streetAddress1Field;
    @FindBy(xpath="//input[@id='street_2']") WebElement streetAddress2Field;
    @FindBy(xpath="//input[@id='street_3']") WebElement streetAddress3Field;
    @FindBy(xpath="//input[@id='city']") WebElement cityField;
    @FindBy(xpath="//input[@id='zip']") WebElement postCodeField;
    @FindBy(xpath="//input[@id='email_address']") WebElement emailAddressField;
    @FindBy(xpath="//input[@id='password']") WebElement passwordField;
    @FindBy(xpath="//div[@id='password-strength-meter']") WebElement passwordStrengthMeter;
    @FindBy(xpath="//input[@id='password_confirmation']") WebElement confirmPasswordField;
  

    //action methods to enter data into fields
    public void enterHowDidYouHearAboutUs(String aboutUs)
    {
        wait.until(ExpectedConditions.visibilityOf(aboutUsField));
        aboutUsField.clear();
        aboutUsField.sendKeys(aboutUs);
    }

    public void enterBusinessType(String businessType)
    {
        wait.until(ExpectedConditions.visibilityOf(businessTypeField));
        businessTypeField.clear();
        businessTypeField.sendKeys(businessType);
    }
    public void enterCompanyName(String companyName)
    {
        wait.until(ExpectedConditions.visibilityOf(companyNameField));
        companyNameField.clear();
        companyNameField.sendKeys(companyName);
    }
    public void enterEmailInvoice(String emailInvoice)
    {
        wait.until(ExpectedConditions.visibilityOf(emailInvoiceField));
        emailInvoiceField.clear();
        emailInvoiceField.sendKeys(emailInvoice);
    }

    public void enterFirstName(String firstName)
    {
        wait.until(ExpectedConditions.visibilityOf(firstNameField));
        firstNameField.clear();
        firstNameField.sendKeys(firstName);
    }

    public void enterLastName(String lastName)
    {
        wait.until(ExpectedConditions.visibilityOf(lastNameField));
        lastNameField.clear();
        lastNameField.sendKeys(lastName);
    }
    public void enterTelephone(String telephone)
    {
        wait.until(ExpectedConditions.visibilityOf(telephoneField));
        telephoneField.clear();
        telephoneField.sendKeys(telephone);
    }

    public void enterStreetAddress(String streetAddress1)
    {
        wait.until(ExpectedConditions.visibilityOf(streetAddress1Field));
        streetAddress1Field.clear();
        streetAddress1Field.sendKeys(streetAddress1);
    }
    public void enterCity(String city)
    {
        wait.until(ExpectedConditions.visibilityOf(cityField));
        cityField.clear();
        cityField.sendKeys(city);
    }
    public void enterPostCode(String postCode)
    {
        wait.until(ExpectedConditions.visibilityOf(postCodeField));
        postCodeField.clear();
        postCodeField.sendKeys(postCode);
    }

    public void enterZipCode(String zipCode)
    {
        wait.until(ExpectedConditions.visibilityOf(postCodeField));
        postCodeField.clear();
        postCodeField.sendKeys(zipCode);
    }


    public void enterEmailAddress(String emailAddress)
    {
        wait.until(ExpectedConditions.visibilityOf(emailAddressField));
        emailAddressField.clear();
        emailAddressField.sendKeys(emailAddress);
    }
    public void enterPassword(String password)
    {
        wait.until(ExpectedConditions.visibilityOf(passwordField));
        passwordField.clear();
        passwordField.sendKeys(password);
    }
    public void enterConfirmPassword(String confirmPassword)
    {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            // Try to find and fill the confirm password field
            WebDriverWait shortWait = new WebDriverWait(driver, java.time.Duration.ofSeconds(10));
            
            // Scroll down to ensure field is in view
            ((JavascriptExecutor) driver).executeScript("window.scrollBy(0, 300);");
            
            shortWait.until(ExpectedConditions.presenceOfElementLocated(
                org.openqa.selenium.By.xpath("//input[@id='password_confirmation']")
            ));
            
            confirmPasswordField.clear();
            confirmPasswordField.sendKeys(confirmPassword);
        } catch (Exception e) {
            // If primary locator fails, try alternative locators
            try {
                WebElement altField = driver.findElement(
                    org.openqa.selenium.By.xpath("//input[contains(@name, 'confirmation')]")
                );
                altField.clear();
                altField.sendKeys(confirmPassword);
            } catch (Exception ex) {
                // Log the error and continue
                System.err.println("Failed to locate confirm password field: " + ex.getMessage());
                throw new RuntimeException("Confirm password field not found", ex);
            }
        }
    }

    //method to check password strength meter is displayed and get the strength level
    public boolean isPasswordStrengthMeterDisplayed()
    {
        wait.until(ExpectedConditions.visibilityOf(passwordStrengthMeter));
        return passwordStrengthMeter.isDisplayed();
    }
    public String getPasswordStrengthLevel()
    {
        wait.until(ExpectedConditions.visibilityOf(passwordStrengthMeter));
        return passwordStrengthMeter.getText();
    }

    

    //dropdown locator for country and state
    @FindBy(xpath="//select[@id='country']") WebElement countryDropdown;
    @FindBy(xpath="//select[@id='region_id']") WebElement stateDropdown;

    //mthod to select country from dropdown using by text
    public void selectCountry(String countryName)
    {
        wait.until(ExpectedConditions.visibilityOf(countryDropdown));
        Select selectCountry=new Select(countryDropdown);
        selectCountry.selectByVisibleText(countryName);
    }
    //method to select state from dropdown using by text
    public void selectState(String stateName)
    {
        wait.until(ExpectedConditions.visibilityOf(stateDropdown));
        Select selectState=new Select(stateDropdown);
        selectState.selectByVisibleText(stateName);
    }

    @FindBy(xpath="//button[@title='Create an Account']") WebElement createAccountButton;
    //action method to click the create account button
    public void clickCreateAccountButton()
    {
        wait.until(ExpectedConditions.elementToBeClickable(createAccountButton));
        createAccountButton.click();
    }

    // Method to scroll to the create account button and click it
    public void scrollToAndClickCreateAccountButton() 
    {
        wait.until(ExpectedConditions.visibilityOf(createAccountButton));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", createAccountButton);
        wait.until(ExpectedConditions.elementToBeClickable(createAccountButton));
        createAccountButton.click();
    }
    

    //duplicate account information error message
    @FindBy(xpath="//div[contains(@class,'message-error') and contains(@class,'error') and contains(@class,'message')]//div[contains(@class,'content')]") WebElement duplicateAccountErrorMessage;
    public boolean isDuplicateAccountErrorMessageDisplayed()
    {
        try
        {
            WebDriverWait shortWait = new WebDriverWait(driver, java.time.Duration.ofSeconds(5));
            shortWait.until(ExpectedConditions.visibilityOf(duplicateAccountErrorMessage));
            return duplicateAccountErrorMessage.isDisplayed();
        }
        catch (TimeoutException e)
        {
            return false;
        }
    }

    public String getDuplicateAccountErrorMessage()
    {
        wait.until(ExpectedConditions.visibilityOf(duplicateAccountErrorMessage));
        return duplicateAccountErrorMessage.getText();
    }

    //blank validation error messages
    @FindBy(xpath="//div[@id='spire_company_name-error']") WebElement companyNameBlankErrorMessage;
    @FindBy(xpath="//div[@id='firstname-error']") WebElement firstNameBlankErrorMessage;
    @FindBy(xpath="//div[@id='lastname-error']") WebElement lastNameBlankErrorMessage;
    @FindBy(xpath="//div[@id='telephone-error']") WebElement telephoneBlankErrorMessage;
    @FindBy(xpath="//div[@id='street_1-error']") WebElement streetAddress1BlankErrorMessage;
    @FindBy(xpath="//div[@id='city-error']") WebElement cityBlankErrorMessage;
    @FindBy(xpath="//div[@id='zip-error']") WebElement postCodeBlankErrorMessage;
    @FindBy(xpath="//div[@id='email_address-error']") WebElement emailAddressBlankErrorMessage;
    @FindBy(xpath="//div[@id='password-error']") WebElement passwordBlankErrorMessage;
    @FindBy(xpath="//div[@id='password-confirmation-error']") WebElement confirmPasswordBlankErrorMessage;

    

    //methods to check if the blank error messages are displayed using explicit wait
    public boolean isCompanyNameBlankErrorMessageDisplayed()
    {
        wait.until(ExpectedConditions.visibilityOf(companyNameBlankErrorMessage));
        return companyNameBlankErrorMessage.isDisplayed();
    }
    public boolean isFirstNameBlankErrorMessageDisplayed()
    {
        wait.until(ExpectedConditions.visibilityOf(firstNameBlankErrorMessage));
        return firstNameBlankErrorMessage.isDisplayed();
    }
    public boolean isLastNameBlankErrorMessageDisplayed()
    {
        wait.until(ExpectedConditions.visibilityOf(lastNameBlankErrorMessage));
        return lastNameBlankErrorMessage.isDisplayed();
    }
    public boolean isTelephoneBlankErrorMessageDisplayed()
    {
        wait.until(ExpectedConditions.visibilityOf(telephoneBlankErrorMessage));
        return telephoneBlankErrorMessage.isDisplayed();
    }

    public boolean isStreetAddress1BlankErrorMessageDisplayed()
    {
        wait.until(ExpectedConditions.visibilityOf(streetAddress1BlankErrorMessage));
        return streetAddress1BlankErrorMessage.isDisplayed();
    }

    public boolean isCityBlankErrorMessageDisplayed()
    {
        wait.until(ExpectedConditions.visibilityOf(cityBlankErrorMessage));
        return cityBlankErrorMessage.isDisplayed();
    }
    public boolean isPostCodeBlankErrorMessageDisplayed()
    {
        wait.until(ExpectedConditions.visibilityOf(postCodeBlankErrorMessage));
        return postCodeBlankErrorMessage.isDisplayed();
    }

    public boolean isEmailAddressBlankErrorMessageDisplayed()
    {
        wait.until(ExpectedConditions.visibilityOf(emailAddressBlankErrorMessage));
        return emailAddressBlankErrorMessage.isDisplayed();
    }

    public boolean isPasswordBlankErrorMessageDisplayed()
    {
        wait.until(ExpectedConditions.visibilityOf(passwordBlankErrorMessage));
        return passwordBlankErrorMessage.isDisplayed();
    }
    public String getPasswordErrorMessage()
    {
        wait.until(ExpectedConditions.visibilityOf(passwordBlankErrorMessage));
        return passwordBlankErrorMessage.getText();
    }
    public boolean isConfirmPasswordBlankErrorMessageDisplayed()
    {
        wait.until(ExpectedConditions.visibilityOf(confirmPasswordBlankErrorMessage));
        return confirmPasswordBlankErrorMessage.isDisplayed();
    }



    
    

}
