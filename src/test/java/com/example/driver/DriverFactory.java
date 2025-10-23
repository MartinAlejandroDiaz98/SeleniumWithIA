package com.example.driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.example.config.Config;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class DriverFactory {

    public static WebDriver createDriver(Config config) {
        String browser = config.getBrowser();
        boolean headless = config.isHeadless();
        String gridUrl = config.getGridUrl();

        switch (browser.toLowerCase()) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                ChromeOptions chrome = new ChromeOptions();
                if (Boolean.getBoolean("headless")) {
                chrome.addArguments("--headless");
                chrome.addArguments("--no-sandbox");
                chrome.addArguments("--disable-dev-shm-usage");
                chrome.addArguments("--disable-gpu");
                }else{
                    chrome.addArguments("--no-sandbox");
                    chrome.addArguments("--disable-dev-shm-usage");
                    chrome.addArguments("--disable-gpu");
                    chrome.addArguments("--window-size=1920,1080");
                }
       /* ------------------------------------------------------------------------------------------------- */

                if (isRemote(gridUrl)) {
                    return new RemoteWebDriver(toURL(gridUrl), chrome);
                } else {
                    WebDriverManager.chromedriver().setup();
                    return new ChromeDriver(chrome);
                }

            case "firefox":
                FirefoxOptions ff = new FirefoxOptions();
                if(headless) ff.addArguments("--headless=new");
                if (isRemote(gridUrl)) {
                    return new RemoteWebDriver(toURL(gridUrl), ff);
                } else {
                    WebDriverManager.firefoxdriver().setup();
                    return new FirefoxDriver(ff);
                }

            case "edge":
                EdgeOptions edge = new EdgeOptions();
                if (headless) edge.addArguments("--headless=new");
                edge.addArguments("--start-maximized");
                edge.addArguments("--disable-gpu");
                edge.addArguments("--remote-allow-origins=*");
                if (isRemote(gridUrl)) {
                    return new RemoteWebDriver(toURL(gridUrl), edge);
                } else {
                    WebDriverManager.edgedriver().setup();
                    return new EdgeDriver(edge);
                }

            default:
                throw new IllegalArgumentException("Navegador no soportado: " + browser);
        }
    }

    private static boolean isRemote(String gridUrl) {
        return gridUrl != null && !gridUrl.isBlank();
    }

    private static URL toURL(String url) {
        try {
            return new URL(url);
        } catch (MalformedURLException e) {
            throw new RuntimeException("gridUrl inválida: " + url, e);
        }
    }
}
