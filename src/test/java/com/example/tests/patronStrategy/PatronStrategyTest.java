package com.example.tests.patronStrategy;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.example.driver.DriverManager;
import com.example.pages.sauceDemo.LoginSaucePage;
import com.example.strategies.pageStrategie.OpenUrl;
import com.example.strategies.pageStrategie.SauceDemoStrategy;
import com.example.tests.BaseTest;

public class PatronStrategyTest extends BaseTest {
    OpenUrl openUrl;
    LoginSaucePage loginSaucePage;
    @BeforeMethod
    public void setUp(){
        openUrl = new OpenUrl();
        loginSaucePage = new LoginSaucePage(DriverManager.getDriver() , config.getExplicitWait());
        openUrl.setStrategy(new SauceDemoStrategy(
            config.getUser(), 
            config.getPass(), 
            config.getSauceDemoUrl(),
            loginSaucePage));
    }
    @Test
    public void shouldOpenPagesUsingStrategies() {
        // Test implementation for Patron Strategy
        
        openUrl.open();
    }
}
