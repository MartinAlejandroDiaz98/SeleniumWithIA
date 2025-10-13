package com.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.example.config.Config;
import com.example.utils.Waits;

public class BasePage {
    protected WebDriver driver;
    protected Waits waits;
    protected Config config;

    public BasePage(WebDriver driver, long explicitWaitSeconds) {
        this.driver = driver;
        this.waits = new Waits(driver, explicitWaitSeconds);
        this.config = new Config();
    }
    /* Declarando de FORMA PROTECTED las clases hijas pueden usar los metodos sin necesidad de iniciazion de clase. Concepto encampsulamiento */
    protected void click(By locator) { waits.clickable(locator).click(); }
    protected void type(By locator, String text) {
        var el = waits.visible(locator);
        el.clear();
        el.sendKeys(text);
    }
    protected String getText(By locator) { return waits.visible(locator).getText(); }
}
