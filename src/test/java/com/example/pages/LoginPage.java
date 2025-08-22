package com.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {

    private final By username = By.id("username");
    private final By password = By.id("password");
    private final By loginBtn = By.cssSelector("button[type='submit']");
    private final By flash = By.id("flash");

    public LoginPage(WebDriver driver, long explicitWaitSeconds) {
        super(driver, explicitWaitSeconds);
    }

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

    public void submit() {
        click(loginBtn);
    }

    public String getFlashMessage() {
        return text(flash);
    }
}
