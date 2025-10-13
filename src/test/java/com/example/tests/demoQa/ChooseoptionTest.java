package com.example.tests.demoQa;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.example.config.Config;
import com.example.pages.demoQa.HomeDemoQaPage;
import com.example.tests.BaseTest;

public class ChooseoptionTest extends BaseTest{
    HomeDemoQaPage homeDemoQaPage;
    @BeforeMethod
    public void setUp(){
        homeDemoQaPage = new HomeDemoQaPage(driver, new Config().getExplicitWait());
        /* Inicio demoQA page aqui ya que es un proyecto donde practico cosas que contiene varias url */
        homeDemoQaPage.goToDemoQaPage();
    }
    @Test
    public void chooseOptionMenuWithParameter() throws InterruptedException{
        homeDemoQaPage.clickOptionMenu(HomeDemoQaPage.menuOption.ALERTS);
        /* Thread sleep solamente momentaneo para validar que elija el camino correcto que le marco en el option */
        Thread.sleep(10000);
    }
}
