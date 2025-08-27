package com.example.tests.primerSprintTest.alertasFrames;

import org.testng.Assert;
import org.testng.annotations.*;

import com.example.pages.Javascript_alertPage;
import com.example.components.PopUpComponent;
import com.example.pages.HomePage;
import com.example.tests.BaseTest;

import io.qameta.allure.Step;

public class AlertFrameTest extends BaseTest{
    /* Inicio pages */
     HomePage homePage;
     Javascript_alertPage java_aPage;
     PopUpComponent alert;

    @BeforeMethod
    public void initPages(){
        homePage = new HomePage(driver, config.getImplicitWait());
        java_aPage = new Javascript_alertPage(driver, config.getImplicitWait());
        alert = new PopUpComponent(driver, config.getImplicitWait());
    }
    @Step("Login con usuario")
    @Test
    public void testAlertFrame(){
    /* Paso a paso */
    /* Abro y voy alertas */
    homePage.open(config.getBaseUrl())
    .selectOption(HomePage.MenuOption.ALERTS);
    /* Selecciono JS ALERT */
    java_aPage.selectAlert(Javascript_alertPage.SelectAlert.JS_ALERT);
    /* Clickeo en alerta */
    String alertText = alert.getAlertText();
    Assert.assertEquals(alertText, "I am a JS Alert","Error, texto obtenido:" + alertText);
    alert.acceptAlert();
    /* Valido texto */
    String validacion = java_aPage.getResultText();
    Assert.assertEquals(validacion, "You successfully clicked an alert","Error, texto obtenido:" + validacion);
    }
    @Test
    public void testConfirmFrame(){
    /* Paso a paso */
    /* Abro y voy alertas */
    homePage.open(config.getBaseUrl())
    .selectOption(HomePage.MenuOption.ALERTS);
    ahorrandoCodigoEjemplo();
    /* Selecciono JS ALERT */
    java_aPage.selectAlert(Javascript_alertPage.SelectAlert.JS_CONFIRM);
    /* Clickeo en alerta */
    String alertText = alert.getAlertText();
    Assert.assertEquals(alertText, "I am a JS Confirm","Error, texto obtenido:" + alertText);
    alert.acceptAlert();
    /* Valido texto */
    String validacion = java_aPage.getResultText();
    Assert.assertEquals(validacion, "You clicked: Ok","Error, texto obtenido:" + validacion);
    }

    private void ahorrandoCodigoEjemplo(){
        homePage.open(config.getBaseUrl())
        .selectOption(HomePage.MenuOption.ALERTS);
    }
}
