package com.example.flows;

import org.openqa.selenium.WebDriver;

import com.example.pages.LoginPage;

public class LoginFlow {
    /* Creo variables de las pages que neceite usar */
    private final LoginPage loginPage;
    /* Pido en el constructor las configuraciones de driver, waits y se las paso a la iniciacion de las pages */
    public LoginFlow(WebDriver driver, Long seconds){
        loginPage = new LoginPage(driver, seconds);
    }
    /* Logica que necesite usar las pages */
    public void loginAs(String url,String username, String password){

        loginPage.open(url).typeUsername(username)
        .typePassword(password)
        .submit();
    }
}
