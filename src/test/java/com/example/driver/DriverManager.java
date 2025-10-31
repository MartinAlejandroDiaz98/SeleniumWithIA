package com.example.driver;

import org.openqa.selenium.WebDriver;

import com.example.config.Config;

public class DriverManager {
    
    /* // Constructor privado para evitar que se instancie */
    private DriverManager() {}

    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public static void initDriver(Config config) {
        WebDriver webDriver = DriverFactory.createDriver(config);
        driver.set(webDriver);
    }

    public static WebDriver getDriver() {
        WebDriver d = driver.get();
        if (d == null) {
            throw new IllegalStateException("Driver no inicializado. Llama a initDriver() en @BeforeMethod.");
        }
        return d;
    }

    public static void quitDriver() {
        WebDriver d = driver.get();
        if (d != null) {
            d.quit();
            driver.remove();
        }
    }
}   
