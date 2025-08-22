package com.example.pages;

import com.example.core.Waits;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class BasePage {
    protected WebDriver driver;
    protected Waits waits;

    public BasePage(WebDriver driver, long explicitWaitSeconds) {
        this.driver = driver;
        this.waits = new Waits(driver, explicitWaitSeconds);
    }

    protected void click(By locator) { waits.clickable(locator).click(); }
    protected void type(By locator, String text) {
        var el = waits.visible(locator);
        el.clear();
        el.sendKeys(text);
    }
    protected String text(By locator) { return waits.visible(locator).getText(); }
}
