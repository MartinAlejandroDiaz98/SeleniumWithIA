package com.example.pages.sauceDemo;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.example.components.CartItemComponent;
import com.example.pages.BasePage;
import com.example.pages.sauceDemo.Checkout.CheckoutInformationPage;

public class CartSaucePage extends BasePage{
    private By cartItems = By.cssSelector(".cart_item");
    private By checkout = By.cssSelector("#checkout");

    public CartSaucePage(WebDriver driver, long explicitWaitSeconds) {
        super(driver, explicitWaitSeconds);
    }
    public CheckoutInformationPage goTocheckout(){
        click(checkout);
        return new CheckoutInformationPage(driver, config.getExplicitWait());
    }
    public List<CartItemComponent> getCartItems(){
        List <WebElement> cartItemsList = driver.findElements(cartItems);

        return cartItemsList.stream()
                            .map(CartItemComponent::new)
                            .collect(Collectors.toList());            
    }

    public CartSaucePage removeItemWithProductName(List <String> productName){
        
        for (String name : productName) {
            getCartItems().stream()
                 .filter(i -> i.getTitle().equalsIgnoreCase(name))
                 .findFirst()
                 .ifPresent(i -> {
                    i.clickRemoveFromCart();
                    waits.invisible(i.getRootElement());
                 });
                
        }
        return this;
    }


    
}
