package com.example.tests.primerSprintTest.alertasFrames;

import com.example.flows.LoginFlow;
import com.example.pages.primerSprintPage.LoginPage;
import com.example.tests.BaseTest;

import org.testng.Assert;
import org.testng.annotations.*;

public class LoginTest extends BaseTest {
    private LoginFlow loginFlow;
    private LoginPage loginPage;
    @BeforeMethod
    public void setUp() {
        loginFlow = new LoginFlow(driver, config.getExplicitWait());
        loginPage = new LoginPage(driver, config.getExplicitWait());
    }
    @Test
    public void loginConCredencialesInvalidas_muestraError() {
        /* Flow inicio de seision */
        loginFlow.loginAs(config.getBaseUrl(),"tomsmith", "mal_password");
        /* Valido assert login incorrecto */
        String message = loginPage.getFlashMessage();
        Assert.assertTrue(message.toLowerCase().contains("your password is invalid"),
                "Se esperaba mensaje de password inválida, pero fue: " + message);
    }
}
