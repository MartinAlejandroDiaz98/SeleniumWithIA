package com.example.tests.primerSprintTest.alertasFrames;

import org.testng.Assert;
import org.testng.annotations.*;

import com.example.components.PopUpComponent;
import com.example.pages.primerSprintPage.HomePage;
import com.example.pages.primerSprintPage.Javascript_alertPage;
import com.example.pages.primerSprintPage.Javascript_alertPage.SelectAlert;
import com.example.tests.BaseTest;

import io.qameta.allure.Step;

public class AlertFrameTest extends BaseTest{
    /* Inicio pages */
     HomePage homePage;
     Javascript_alertPage java_aPage;
     PopUpComponent alertComponent;
     /* Data provider para que se ejecute por cada alerta elegida, si agregan mas podria agregar mas aqui */
    @DataProvider(name = "alertOptions")
    public Object[][] alertOptions(){
        return new Object[][]{
                {SelectAlert.JS_ALERT, "I am a JS Alert", "You successfully clicked an alert"},
                {SelectAlert.JS_CONFIRM, "I am a JS Confirm", "You clicked: Ok"},
                {SelectAlert.JS_PROMPT, "I am a JS prompt", "You entered:"}
        };
    }
    /* Inicio pages */
    @BeforeMethod
    public void initPages(){
        homePage = new HomePage(driver, config.getImplicitWait());
        java_aPage = new Javascript_alertPage(driver, config.getImplicitWait());
        alertComponent = new PopUpComponent(driver, config.getImplicitWait());
    }
    /* Test con data provider, reducimos a un solo test con una sola linea de codigo donde utiliza un metodo con la logica */
    @Step("Validar alerta {alert} con mensaje esperado: {messageAlert}")
    @Test(dataProvider = "alertOptions")
    public void selectAlertPopUp(SelectAlert alert, String messageAlert, String expectedAssert){
        navigateToSelectAlert(alert, messageAlert, expectedAssert);
    }
    /* Metodo con la logica deseada */
    private void navigateToSelectAlert(SelectAlert alert, String messageAlert, String expectedAssert){
        homePage.open(config.getBaseUrl())
        .selectOption(HomePage.MenuOption.ALERTS);
        java_aPage.selectAlert(alert);
        /* Clickeo en alerta */
        String alertText = alertComponent.getAlertText().toLowerCase();
        Assert.assertEquals(alertText, messageAlert.toLowerCase(),"Error, texto obtenido:" + alertText);
         alertComponent.acceptAlert();
        /* Valido texto final */
        String validacion = java_aPage.getResultText().toLowerCase();
        Assert.assertEquals(validacion, expectedAssert.toLowerCase(),"Error, texto obtenido:" + validacion);
    }
}
