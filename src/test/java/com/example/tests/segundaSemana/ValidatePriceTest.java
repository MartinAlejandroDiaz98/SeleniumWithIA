package com.example.tests.segundaSemana;

import java.util.List;

import org.testng.annotations.*;

import com.example.flows.LoginFlow;
import com.example.tests.BaseTest;

public class ValidatePriceTest extends BaseTest {
    LoginFlow loginFlow;

    @DataProvider(name = "products")
    public Object[][] products(){
        return new Object[][]{
            {List.of("Sauce Labs Backpack","Sauce Labs Bike Light","Sauce Labs Bolt T-Shirt")}
        };
    }
    
    @BeforeMethod
    public void setUp()
    {
        loginFlow = new LoginFlow(driver, config.getExplicitWait());
    }
    @Test(dataProvider = "products")
    public void shouldValidatePriceWithAllItems(List<String> productsNameList){
        loginFlow.loginAs(config.getSauceDemoUrl(), config.getUser(), config.getPass())
        .addProductsToCartByName(productsNameList)
        .goToCart()
        .goTocheckout()
        .completeCheckoutOverviewPage("Martin", "Diaz", "1224")
        .validateTotal();
    }
    
}
