package com.example.pages.primerSprintPage.localizadores;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.example.pages.BasePage;

public class LocalizadoresPage extends BasePage {

    public LocalizadoresPage(WebDriver driver, long explicitWaitSeconds) {
        super(driver, explicitWaitSeconds);
    }
    
    public LocalizadoresPage open(String baseUrl){
        driver.get(baseUrl + "/login");
        return this;
    }

    /* Practico CSS selector por hijos padres */
    private By username = By.cssSelector("#username");
    private By password = By.cssSelector("#password");
    private By submitBtn = By.cssSelector("button[type='submit']");
    private By messageError = By.id("flash");
    /* Uso objetos de la pantalla login tomados con CSS SELECTOR */
    public LocalizadoresPage typeUsername(String user){
        type(username, user);
        return this;
    }
    public LocalizadoresPage typePassword(String pass){
        type(password, pass);
        return this;
    }
    public void submit(){
        click(submitBtn);
    }
    public String getMessageError(){
        return getText(messageError);
    }

}
