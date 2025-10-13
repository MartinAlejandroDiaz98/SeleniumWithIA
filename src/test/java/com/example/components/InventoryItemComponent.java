package com.example.components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class InventoryItemComponent {
    /* Item del inventario */
    private WebElement item;
    /* Inicio constructor para decirle que es el item */
    public InventoryItemComponent(WebElement item){
        this.item = item;   
    }
    /* Logica */
    /* Obtengo datos */
    public String getTitle(){
        return item.findElement(By.cssSelector(".inventory_item_name")).getText();
    }
    public String getDescription(){
        return item.findElement(By.cssSelector(".inventory_item_desc")).getText();
    }
    public double getPrice(){
        String priceText = item.findElement(By.cssSelector(".inventory_item_price")).getText();
        return Double.parseDouble(priceText.replace("$", ""));
    }
    /* ------------------------------------------------------------------------------------------ */
    /* Hago click */
    public void clickAddToCart(){
        item.findElement(By.cssSelector("button[id*=add-to-cart]")).click();
    }
}
