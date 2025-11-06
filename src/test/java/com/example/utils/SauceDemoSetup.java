package com.example.utils;

import com.example.pages.sauceDemo.CartSaucePage;
import com.example.pages.sauceDemo.HomeSaucePage;
import com.example.pages.sauceDemo.LoginSaucePage;
import com.example.pages.sauceDemo.Checkout.CheckoutCompletePage;

public class SauceDemoSetup {
    public final HomeSaucePage homeSaucePage;
    public final LoginSaucePage loginSaucePage;
    public final CartSaucePage cartSaucePage;
    public final CheckoutCompletePage checkoutCompletePage;

    public SauceDemoSetup(HomeSaucePage homeSaucePage, LoginSaucePage loginSaucePage,
            CartSaucePage cartSaucePage, CheckoutCompletePage checkoutCompletePage) {
        this.homeSaucePage = homeSaucePage;
        this.loginSaucePage = loginSaucePage;
        this.cartSaucePage = cartSaucePage;
        this.checkoutCompletePage = checkoutCompletePage;
    }
}
