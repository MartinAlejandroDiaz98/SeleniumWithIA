package com.example.utils;


import com.example.config.Config;
import com.example.driver.DriverManager;
import com.example.pages.sauceDemo.CartSaucePage;
import com.example.pages.sauceDemo.HomeSaucePage;
import com.example.pages.sauceDemo.LoginSaucePage;
import com.example.pages.sauceDemo.Checkout.CheckoutCompletePage;

public class SetUpFacade {
    public static SauceDemoSetup inicializeSaucePages() {
        // Initialization logic for tests
        LoginSaucePage loginSaucePage = new LoginSaucePage(DriverManager.getDriver(), new Config().getExplicitWait());
        HomeSaucePage homeSaucePage = new HomeSaucePage(DriverManager.getDriver(), new Config().getExplicitWait());
        CartSaucePage cartSaucePage = new CartSaucePage(DriverManager.getDriver(), new Config().getExplicitWait());
        CheckoutCompletePage checkoutCompletePage = new CheckoutCompletePage(DriverManager.getDriver(), new Config().getExplicitWait());
        return new SauceDemoSetup(homeSaucePage, loginSaucePage, cartSaucePage, checkoutCompletePage);
    }   
}
