package com.example.pages.sauceDemo.Checkout;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.example.pages.BasePage;

public class CheckoutOverviewPage extends BasePage {
    private By finish = By.cssSelector("#finish");
    private By subTotal = By.cssSelector(".summary_subtotal_label");
    private By tax = By.cssSelector(".summary_tax_label");
    private By total = By.cssSelector(".summary_total_label");
    public CheckoutOverviewPage(WebDriver driver, long explicitWaitSeconds) {
        super(driver, explicitWaitSeconds);
        //TODO Auto-generated constructor stub
    }

    public Double getSubTotal() {
        return Double.valueOf(getText(subTotal).replace("Item total: $", ""));
    }
    public Double getTax(){
        return Double.valueOf(getText(tax).replace("Tax: $", ""));
    }
    public Double getTotal(){
        return Double.valueOf(getText(total).replace("Total: $", ""));
    }
    public Double totalOfItemsOnCart(){
        return driver.findElements(By.cssSelector(".inventory_item_price"))
        .stream()
        .map(e-> e.getText().replace("$", ""))
        .mapToDouble(e-> Double.valueOf(e))
        .sum();
    }
    
    public CheckoutOverviewPage validateTotal(){
        if(totalOfItemsOnCart() != getSubTotal()){
            throw new IllegalStateException("El subtotal no es correcto");
        }
        if(getSubTotal() + getTax() != getTotal()){
            throw new IllegalStateException("El total no es correcto");
        }
        return this;
    }
    public CheckoutCompletePage clickFinish(){
        click(finish);
        return new CheckoutCompletePage(driver, config.getExplicitWait());
    }
    
}
