package com.example.components;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.example.utils.Waits;

public class PopUpComponent {
    /* Variables */
    private WebDriver driver;
    private Waits wait;
    /* Construtor */
    public PopUpComponent(WebDriver driver, long timeout){
        this.driver = driver;
        this.wait = new Waits(driver, timeout);
    }
    /* -------------------------------------------------------------------------------------------------- */
    /* Metodos para ALERTS */
     public String getAlertText() {
        wait.alertIsPresent();
        Alert alert = driver.switchTo().alert();
        return alert.getText();
    }

    public void acceptAlert() {
        wait.alertIsPresent();
        driver.switchTo().alert().accept();
    }

    public void dismissAlert() {
        wait.alertIsPresent();
        driver.switchTo().alert().dismiss();
    }
     public void sendKeysToAlert(String texto) {
        wait.alertIsPresent();
        driver.switchTo().alert().sendKeys(texto);
    }

    public boolean alertTextMatches(String expectedText) {
        String actual = getAlertText().toLowerCase();
        return actual.equals(expectedText.toLowerCase());
    }

    public boolean alertTextContains(String subString){
        String actual = getAlertText().toLowerCase();
        return actual.contains(subString.toLowerCase());
    }

    /*  
        public boolean alertTextMatchesRegex(String regex) {
        String actual = getAlertText();
        return Pattern.matches(regex, actual);
    } 
    */
    /* -------------------------------------------------------------------------------------------------- */
   // ----------------- MODALES WEB -----------------

    public String getModalText(By modalLocator) {
        WebElement modal = wait.visible(modalLocator);
        return modal.getText();
    }

    public void closeModal(By closeButtonLocator) {
        WebElement btn = wait.clickable(closeButtonLocator);
        btn.click();
    }

    public boolean isModalDisplayed(By modalLocator) {
        try {
            WebElement modal = wait.visible(modalLocator);
            return modal.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean modalTextContains(By modalLocator, String partialText) {
        String text = getModalText(modalLocator);
        return text.contains(partialText);
    }

    /* 
        public boolean modalTextMatchesRegex(By modalLocator, String regex) {
        String text = getModalText(modalLocator);
        return Pattern.matches(regex, text);
    } */
    /* -------------------------------------------------------------------------------------------------- */

}
