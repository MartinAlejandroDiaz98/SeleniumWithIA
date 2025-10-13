package com.example.flows;

import org.openqa.selenium.WebDriver;
import com.example.pages.sauceDemo.HomeSaucePage;
import com.example.pages.sauceDemo.LoginSaucePage;

public class LoginFlow {
    /* Creo variables de las pages que neceite usar */
    private final LoginSaucePage loginSaucePage;
    /* Pido en el constructor las configuraciones de driver, waits y se las paso a la iniciacion de las pages */
    public LoginFlow(WebDriver driver, Long seconds){
        loginSaucePage = new LoginSaucePage(driver, seconds);
    }
    /* Logica que necesite usar las pages */
    public HomeSaucePage loginAs(String url,String username, String password){
        return loginSaucePage.login(username, password, url);
    }
}
