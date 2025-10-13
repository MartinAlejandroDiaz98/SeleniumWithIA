package com.example.components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class CartItemComponent {
    private WebElement item;

    public CartItemComponent(WebElement item){
        this.item = item;   
    }

    public String getTitle(){
        return item.findElement(By.cssSelector(".inventory_item_name")).getText();
    }
    public String getDescription(){
        return item.findElement(By.cssSelector(".inventory_item_desc")).getText();
    }
    public Double getPrice(){
        String priceText = item.findElement(By.cssSelector(".inventory_item_price")).getText();
        return Double.parseDouble(priceText.replace("$", ""));
    }
    public void clickRemoveFromCart(){
        item.findElement(By.cssSelector("button[id^='remove-sauce']")).click();
    }
    public WebElement getRootElement(){
        return item;
    }
}
