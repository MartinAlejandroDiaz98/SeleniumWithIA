package com.example.pages.sauceDemo.Checkout;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.example.pages.BasePage;

public class CheckoutCompletePage extends BasePage {
    private By checkoutCompleteMessage = By.cssSelector(".complete-header");

    public CheckoutCompletePage(WebDriver driver, long explicitWaitSeconds) {
        super(driver, explicitWaitSeconds);
        //TODO Auto-generated constructor stub
    }

    public String getCheckoutCompleteMessage(){
        return getText(checkoutCompleteMessage);
    }
    
}
