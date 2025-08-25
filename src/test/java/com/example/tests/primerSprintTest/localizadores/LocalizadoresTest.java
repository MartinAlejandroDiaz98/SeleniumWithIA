package com.example.tests.primerSprintTest.localizadores;

import org.testng.Assert;
import org.testng.annotations.Test;
import com.example.pages.primerSprintPage.localizadores.LocalizadoresPage;
import com.example.tests.BaseTest;

public class LocalizadoresTest extends BaseTest {
    
    @Test
    public void localizador(){
        var loginPage = new LocalizadoresPage(driver, config.getExplicitWait());
        loginPage.open(config.getBaseUrl())
        .typeUsername("tomsmith")
        .typePassword("mal_password")
        .submit();

        String message = loginPage.getMessageError();
        Assert.assertTrue(message.contains("Your password is invalid"), "Mensaje de error esperado no encontrado, se encontro: " + message);
    }
}
