package com.example.pages.demoQa;

import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.example.pages.BasePage;

public class HomeDemoQaPage extends BasePage {
    By options = By.cssSelector(".card-body");
    public HomeDemoQaPage(WebDriver driver, long explicitWaitSeconds) {
        super(driver, explicitWaitSeconds);    
    }
    public void goToDemoQaPage()
    {
        driver.get("https://demoqa.com/");
    }
    public WebElement option(menuOption option){
        return driver.findElements(options)
        .stream()
        .filter(element-> element.getText().equalsIgnoreCase(option.getOption()))
        .findFirst()
        .orElseThrow(()-> new NoSuchElementException("No se encontro la opcion: " + option ));
    }
    public void clickOptionMenu(menuOption option){
        option(option).click();
    }
    public enum menuOption{
        ELEMENTS("Elements"),
        FORMS("Forms"),
        ALERTS("Alerts, Frame & Windows"),
        WIDGETS("Widgets"),
        INTERACTIONS("Interactions"),
        BOOK_STORE("Book Store Application");
        private final String option;
        menuOption(String option) {
            this.option = option;
        }
        public String getOption(){
            return option;
        }
    }

}
