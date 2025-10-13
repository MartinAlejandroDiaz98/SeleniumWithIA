package com.example.flows.sauceDemoFlows;

import org.openqa.selenium.WebDriver;
import com.example.pages.sauceDemo.HomeSaucePage;
import com.example.pages.sauceDemo.LoginSaucePage;

public class LoginSauceFlow {
    private LoginSaucePage loginSauce;

    public LoginSauceFlow(WebDriver driver, Long secondos){
        loginSauce = new LoginSaucePage(driver, secondos);
    }

    public HomeSaucePage loginAs(String user, String pass, String url){
        return loginSauce.login(user, pass, url );
    }
    
}
