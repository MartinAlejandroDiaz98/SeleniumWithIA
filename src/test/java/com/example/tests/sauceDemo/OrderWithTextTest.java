package com.example.tests.sauceDemo;

import java.util.Arrays;
import java.util.List;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.example.flows.sauceDemoFlows.LoginSauceFlow;
import com.example.helper.CartHelper;
import com.example.pages.sauceDemo.CartSaucePage;
import com.example.pages.sauceDemo.HomeSaucePage;
import com.example.tests.BaseTest;

public class OrderWithTextTest extends BaseTest {

    private LoginSauceFlow loginSauceFlow;
    private List<String> productosEsperados;
    private CartHelper cartHelper;
    private HomeSaucePage homeSaucePage;
    private CartSaucePage cartSaucePage;
    @BeforeMethod
    public void setUp()
    {
        loginSauceFlow = new LoginSauceFlow(driver, config.getImplicitWait());
        homeSaucePage = new HomeSaucePage(driver, config.getImplicitWait());
        cartSaucePage = new CartSaucePage(driver, config.getImplicitWait());
        /* Datos de prueba */
        productosEsperados = Arrays.asList("Sauce Labs Backpack", "Sauce Labs Bolt T-Shirt");
        cartHelper = new CartHelper();
    }
    @Test
    public void addToCartItemsWithTitleOnShop()
    {
        addToCartWithTitleItems(productosEsperados)
        .goToCart()
        .removeItemWithProductName(productosEsperados);
        
        Double checkPrice = cartHelper.priceOfProductsSelected(productosEsperados, homeSaucePage.getItems());
        System.out.println(checkPrice);
   
    }
    public HomeSaucePage addToCartWithTitleItems(List<String> products)
    {
        /* Flujo */
        return loginSauceFlow.loginAs("standard_user", "secret_sauce", config.getSauceDemoUrl())
        .addProductsToCartByName(products);/* Agrego productos al carrito */
    }

    }
    

