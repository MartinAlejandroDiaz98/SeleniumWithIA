package com.example.driver;

import org.openqa.selenium.WebDriver;

import com.example.config.Config;

public class DriverManager {
    private static DriverManager instance;
    private DriverManager() {}

    public static synchronized DriverManager getInstance() {
        if (instance == null) {
            instance = new DriverManager();
        }
        return instance;
    }

    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public void initDriver(Config config) {
        WebDriver webDriver = DriverFactory.createDriver(config);
        driver.set(webDriver);
    }

    public WebDriver getDriver() {
        WebDriver d = driver.get();
        if (d == null) {
            throw new IllegalStateException("Driver no inicializado. Llama a initDriver() en @BeforeMethod.");
        }
        return d;
    }

    public void quitDriver() {
        WebDriver d = driver.get();
        if (d != null) {
            d.quit();
            driver.remove();
        }
    }
}
