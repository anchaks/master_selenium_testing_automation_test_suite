package jayscompany_staging_test_automation_suite.pageObjects;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPageObjects 
{

    public WebDriver driver;
    public WebDriverWait wait;

    public LoginPageObjects(WebDriver driver)
    {
        this.driver=driver;
        PageFactory.initElements(driver, this);
        wait=new WebDriverWait(driver, Duration.ofSeconds(300));
    }

    @FindBy(xpath="//input[@id='email']") WebElement emailField;
    @FindBy(xpath="//fieldset[@class='fieldset login']//input[@id='pass']") WebElement passwordField;

    //Method to enter email and password
    public void enterEmailAndPassword(String email, String password)
    {
        wait.until(ExpectedConditions.visibilityOf(emailField));
        emailField.clear();
        emailField.sendKeys(email);
        wait.until(ExpectedConditions.visibilityOf(passwordField));
        passwordField.clear();
        passwordField.sendKeys(password);
    }

    //method to click the sign in button
    @FindBy(xpath="//fieldset[@class='fieldset login']//button[@id='send2']") WebElement signInButton;

    public void clickSignInButton()
    {
        wait.until(ExpectedConditions.elementToBeClickable(signInButton));
        signInButton.click();
    }
    
    
}