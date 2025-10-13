package com.example.tests.segundaSemana;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.example.flows.LoginFlow;
import com.example.pages.sauceDemo.CartSaucePage;
import com.example.tests.BaseTest;
public class ShoppingTest extends BaseTest {
    private LoginFlow loginFlow;
    private CartSaucePage cartSaucePage;

    @DataProvider(name="products")
    public Object[][] product(){
        return new Object[][]{
            {List.of("Sauce Labs Backpack", "Sauce Labs Bike Light", "Sauce Labs Bolt T-Shirt")}
        };
    }
    @DataProvider(name="completeCheckout")
    public Object[][] completeCheckout(){
        return new Object[][]{
            {List.of("Sauce Labs Backpack", "Sauce Labs Bike Light", "Sauce Labs Bolt T-Shirt"), "Juan", "Perez", "12345", "Thank you for your order!"}
        };
    }

    @BeforeMethod
    public void setUp()
    {
        loginFlow = new LoginFlow(driver, config.getExplicitWait());
        cartSaucePage = new CartSaucePage(driver, config.getExplicitWait());
    }
    
    @Test(dataProvider = "products", description = "Agregar productos al carrito mediante lista y verificar que esten")
    public void shouldAddProductsWithTextToCart(List<String> productsNameList)
    {
        List <String> productsCart = loginFlow.loginAs(config.getSauceDemoUrl(), config.getUser(), config.getPass())
        .addProductsToCartByName(productsNameList)
        .goToCart()
        .getCartItems()
        .stream()
        .map(i -> i.getTitle())
        .toList();

        Assert.assertEquals(productsCart, productsNameList, "No es el producto esperado, revisar. Valor actual: " + productsCart);
    }
    
    @Test(dataProvider = "completeCheckout", description = "Agregar productos al carrito, verificar que esten y completar checkout")
    public void shouldBuyProductsSuccessfully(List<String> productsNameList, String firstName, String lastName, String zipCode, String completeMessage)
    {
        List <String> itemsCart = loginFlow.loginAs(config.getSauceDemoUrl(), config.getUser(), config.getPass())
        .addProductsToCartByName(productsNameList)
        .goToCart()
        .getCartItems()
        .stream()
        .map(i -> i.getTitle())
        .toList();
        Assert.assertEquals(itemsCart, productsNameList, "No son los productos esperados, revisar. Valor actual: " + itemsCart);
        
        String finishMessage = cartSaucePage.goTocheckout()
        .completeCheckoutOverviewPage(firstName, lastName, zipCode)
        .validateTotal()
        .clickFinish()
        .getCheckoutCompleteMessage();
        Assert.assertEquals(finishMessage, completeMessage, "No se pudo hacer la compra, revisar. Valor actual: " + completeMessage);
    }
}
