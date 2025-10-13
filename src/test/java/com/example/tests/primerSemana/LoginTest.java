package com.example.tests.primerSemana;

import org.testng.Assert;
import org.testng.annotations.*;

import com.example.flows.LoginFlow;
import com.example.pages.sauceDemo.LoginSaucePage;
import com.example.tests.BaseTest;

public class LoginTest extends BaseTest{
 LoginSaucePage loginP;
 LoginFlow loginFlow;
 @DataProvider(name="loginErrorData")
 public Object[][] loginErrorData(){
    return new Object[][]{
        {"standard_user", "wrongPassword", LoginSaucePage.LoginErrorMessage.LOGIN_INVALID_USER},
        {"locked_out_user", "secret_sauce", LoginSaucePage.LoginErrorMessage.LOGIN_LOCKED_OUT},
        {" ", " ", LoginSaucePage.LoginErrorMessage.LOGIN_LOCKED_OUT}
    };
 }
  @DataProvider(name="loginData")
 public Object[][] loginData(){
    return new Object[][]{
        {"standard_user", "secret_sauce"}
    };
 }
 @BeforeMethod
 public void setup(){
    loginP = new LoginSaucePage(driver, config.getExplicitWait());
    loginFlow = new LoginFlow(driver, config.getExplicitWait()); 
 }
 
 @Test(dataProvider = "loginErrorData")
 public void shouldDisplayProperErrorMessageForInvalidLoginScenarios(String username, String password, String expectedString){
    loginP.login(username, password, config.getSauceDemoUrl());
    Assert.assertEquals(loginP.getErrorMessage(), expectedString, "Error message is not correct, check actual value: " + loginP.getErrorMessage());
 }
 @Test(dataProvider = "loginData")
 public void shouldDisplayHomePageWithValidCredentials(String username, String password){
   loginFlow.loginAs(config.getSauceDemoUrl(), username, password)
   .waitHomeDisplayed();
 }
}
