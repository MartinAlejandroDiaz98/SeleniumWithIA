package com.example.tests.primerSemana;

import org.testng.Assert;
import org.testng.annotations.*;

import com.example.data.LoginData;
import com.example.flows.LoginFlow;
import com.example.pages.sauceDemo.LoginSaucePage;
import com.example.tests.BaseTest;

public class LoginTest extends BaseTest{
 LoginSaucePage loginP;
 LoginFlow loginFlow;

 @DataProvider(name="loginErrorData")
 public Object[][] loginErrorData(){
         LoginData dataLogin = new LoginData.Builder("standard_user", "wrongPassword").withExpectedMessage(LoginSaucePage.LoginErrorMessage.LOGIN_INVALID_USER.getMessage()).build();
         LoginData dataLogin2 = new LoginData.Builder("locked_out_user", "secret_sauce").withExpectedMessage(LoginSaucePage.LoginErrorMessage.LOGIN_LOCKED_OUT.getMessage()).build();
         LoginData dataLogin3 = new LoginData.Builder(" ", " ").withExpectedMessage(LoginSaucePage.LoginErrorMessage.LOGIN_LOCKED_OUT.getMessage()).build();
    return new Object[][]{
        {dataLogin},
        {dataLogin2},
        {dataLogin3}
         
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
 public void shouldDisplayProperErrorMessageForInvalidLoginScenarios(LoginData data){
    loginP.login(data.getUsername(), data.getPassword(), config.getSauceDemoUrl());
    Assert.assertEquals(loginP.getErrorMessage(), data.getExpectedMessage(), "Error message is not correct, check actual value: " + loginP.getErrorMessage());
 }
 @Test(dataProvider = "loginData")
 public void shouldDisplayHomePageWithValidCredentials(String username, String password){
   loginFlow.loginAs(config.getSauceDemoUrl(), username, password)
   .waitHomeDisplayed();
 }
}
