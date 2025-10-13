package com.example.pages.sauceDemo.Checkout;

import org.openqa.selenium.*;
import org.openqa.selenium.WebDriver;

import com.example.pages.BasePage;

public class CheckoutInformationPage extends BasePage {
    private By firstName = By.cssSelector("#first-name");
    private By lastName = By.cssSelector("#last-name");
    private By postalCode = By.cssSelector("#postal-code");
    private By btnContinue = By.cssSelector("#continue");
    
    public CheckoutInformationPage(WebDriver driver, long explicitWaitSeconds) {
        super(driver, explicitWaitSeconds);
        //TODO Auto-generated constructor stub
    }
    
    public void typeFirstName(String firstName)
    {
        type(this.firstName, firstName);
    }
    public void typeLastName(String lastName){
        type(this.lastName, lastName);
    }
    public void typePostalCode(String postalCode){
        type(this.postalCode, postalCode);
    }
    public CheckoutOverviewPage clickContinue(){
        click(btnContinue);
        return new CheckoutOverviewPage(driver, config.getExplicitWait());
    }

    public CheckoutOverviewPage completeCheckoutOverviewPage(String firstName, String lastName, String postalCode){
        typeFirstName(firstName);
        typeLastName(lastName);
        typePostalCode(postalCode);
        return clickContinue();
    }
}
