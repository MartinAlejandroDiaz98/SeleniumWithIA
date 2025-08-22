package com.example.tests;

import com.example.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    @Test
    public void loginConCredencialesInvalidas_muestraError() {
        var page = new LoginPage(driver, config.getExplicitWait());
        page.open(config.getBaseUrl())
            .typeUsername("tomsmith")
            .typePassword("mal_password")
            .submit();

        String message = page.getFlashMessage();
        Assert.assertTrue(message.toLowerCase().contains("your password is invalid"),
                "Se esperaba mensaje de password inválida, pero fue: " + message);
    }
}
