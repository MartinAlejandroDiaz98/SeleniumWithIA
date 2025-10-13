package com.example.pages.sauceDemo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.example.pages.BasePage;

public class LoginSaucePage extends BasePage {
    /* Selectores */
    private By username = By.cssSelector("input[placeholder='Username']"); /* CSS por abributo */
    private By password = By.cssSelector("input[placeholder='Password']");
    private By btnLogin = By.cssSelector("#login-button"); /* CSS por ID */
    private By errorMessage = By.cssSelector("h3[data-test='error']"); /* CSS por atributo */
    /* Constructor */
    public LoginSaucePage(WebDriver driver, Long miliSeconds) {
        super(driver, miliSeconds);
    }
    /* ------------------------------------------------------------------------------------------------- */
    /* Metodos acciones page */
    /* Abro Page souce demo */
    public LoginSaucePage open(String url){
        driver.get(url);
        return this;
    }
    public LoginSaucePage typeUser(String user){
        type(username, user);
        return this;
    }
    public LoginSaucePage typePass(String pass){
        type(password, pass);
        return this;
    }
    public HomeSaucePage clickBtnLogin(){
        click(btnLogin);
        return new HomeSaucePage(driver, config.getImplicitWait());
    }
    /* ------------------------------------------------------------------------------------------------- */
    /* Logica */
    /* Metodo login */
    public HomeSaucePage login(String user, String pass, String url){
        return open(url).typeUser(user).typePass(pass).clickBtnLogin();
    }
    /* Tomo error */
    public String getErrorMessage(){
        return getText(errorMessage);
    }
    public String mensaje(LoginErrorMessage mensaje){
        return mensaje.getMessage();

    }

    public enum LoginErrorMessage{
        LOGIN_EMPTY("Epic sadface: Username is required"),
        LOGIN_LOCKED_OUT("Epic sadface: Sorry, this user has been locked out."),
        LOGIN_INVALID_USER("Epic sadface: Username and password do not match any user in this service");

        private final String message;
        LoginErrorMessage(String message){
            this.message = message;
        }
       public String getMessage(){
        return message;
       }
        
        
    }
    

}
