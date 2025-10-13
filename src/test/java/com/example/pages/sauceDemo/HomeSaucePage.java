package com.example.pages.sauceDemo;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.example.components.InventoryItemComponent;
import com.example.models.Product;
import com.example.pages.BasePage;

public class HomeSaucePage extends BasePage {
    private By inventoryList = By.cssSelector(".app_logo");
    private By inventoryItem = By.cssSelector("div[data-test='inventory-item']");
    private List<Product> products;
    private By shoppingCartBtn = By.cssSelector(".shopping_cart_link");
    
    public HomeSaucePage(WebDriver driver, Long miliSeconds) {
        super(driver, miliSeconds);
    }
    /* Espero cargue */
    public HomeSaucePage waitHomeDisplayed() {
        waits.visible(inventoryList);
        return this;
    }
    public CartSaucePage goToCart(){
        click(shoppingCartBtn);
        return new CartSaucePage(driver, config.getExplicitWait());
    }
    /* Obtengo todos los ITEMS del home */
    public List<WebElement> getInventoryItems(){
        /* Primer paso, declarar una lista de elementos que contenga justamente todos los items del inventorio */
        List<WebElement> items = driver.findElements(inventoryItem);
        return items;
        
    }
    // Devuelve todos los InventoryItemComponent
    public List<InventoryItemComponent> getItems() {
        List<WebElement> items = driver.findElements(inventoryItem);
        return items.stream() /* Convierte la lista de WebElement en un stream de Java 8, que permite usar operaciones funcionales como map, filter y collect. */
                       .map(InventoryItemComponent::new) /* Lo que hace: por cada WebElement en items, crea un nuevo InventoryItemComponent pasando ese WebElement al constructor. */
                       .collect(Collectors.toList()); /* Convierte el stream de InventoryItemComponent nuevamente en una lista */
    }
     // Agrega productos por lista de nombres
    public HomeSaucePage addProductsToCartByName(List<String> productNames){
        List<InventoryItemComponent> items = getItems();
        for (String name : productNames) {
            items.stream()
                 .filter(i -> i.getTitle().equalsIgnoreCase(name))
                 .findFirst()   
                 .ifPresent(InventoryItemComponent::clickAddToCart);
        }
        return this; // Fluent Interface
    }
    /* Creo lista de objetos dentro de CADA ITEM de getInventory items */
    public List<Product> getProduct(){
        products = new ArrayList<>();
        /* Foreach recorriendo items inventory, por cada item existente almaceno titulo descripcion precio */
        for (WebElement item : getInventoryItems())
        {
            /* Tomo los datos segun variables PRODUCT para instanciar objeto en la lista de array */
            String title = item.findElement(By.cssSelector(".inventory_item_name")).getText();
            String description = item.findElement(By.cssSelector(".inventory_item_desc")).getText();
            Double price = Double.parseDouble(item.findElement(By.cssSelector(".inventory_item_price")).getText().replace("$", ""));
            /* Lo agrego al objeto */
            products.add(new Product(title, description, price));
        }
        /* Retorno la lista de productos llena */
        return products;
    } 

}
