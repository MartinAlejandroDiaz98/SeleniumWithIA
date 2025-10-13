package com.example.pages.primerSprintPage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.example.pages.BasePage;

public class LoginPage extends BasePage {

    private final By username = By.id("username");
    private final By password = By.id("password");
    private final By loginBtn = By.cssSelector("button[type='submit']");
    private final By flash = By.id("flash");

    public LoginPage(WebDriver driver, long explicitWaitSeconds) {
        super(driver, explicitWaitSeconds);
    }

    /* Metodos de tipo clase LoginPage que retornan THIS se hace para poder encadenarlos luego en el test
     * "Cuando un método devuelve this, significa que está devolviendo la misma instancia del objeto actual.
     *  Esto se hace para encadenar métodos (method chaining), lo que hace que el código sea más legible y fluido."
     * Ejemplo de uso en "LoginTest"
     */
    public LoginPage open(String baseUrl) {
        driver.get(baseUrl + "/login");
        return this;
    }

    public LoginPage typeUsername(String user) {
        type(username, user);
        return this;
    }

    public LoginPage typePassword(String pass) {
        type(password, pass);
        return this;
    }
    /* ------------------------------------------------------------------------------------------------------------ */

    public void submit() {
        click(loginBtn);
    }

    public String getFlashMessage() {
        return getText(flash);
    }
}
