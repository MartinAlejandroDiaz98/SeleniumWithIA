package com.example.tests;

import com.example.config.Config;
import com.example.driver.DriverManager;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import java.time.Duration;

public class BaseTest {
    protected DriverManager driverManager;
    protected Config config;
    protected WebDriver driver;

    @BeforeSuite
    public void setupAllureDirectory() {
    System.setProperty("allure.results.directory", "target/allure-results");
    }
    /* Before method always run en true, dice que SIEMPRE que se ejecute un test ejecute primero esto. */
    @BeforeMethod(alwaysRun = true)
    @Parameters({"browser"})
    public void setUp(@Optional("chrome") String browser) {
        /* Inicio configuraciones */
        config = new Config();
        System.setProperty("browser", browser); // permite que el parámetro overridee config
        DriverManager.initDriver(config);
        driver = DriverManager.getDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(config.getImplicitWait()));
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        if (DriverManager.getDriver() != null) DriverManager.quitDriver();
    }
    public WebDriver getDriver() {
        return driver;
    }
}
