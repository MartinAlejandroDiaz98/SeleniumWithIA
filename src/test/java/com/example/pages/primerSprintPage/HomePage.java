package com.example.pages.primerSprintPage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.example.pages.BasePage;

public class HomePage extends BasePage{

    public HomePage(WebDriver driver, long explicitWaitSeconds) {
        super(driver, explicitWaitSeconds);
        //TODO Auto-generated constructor stub
    }

    public HomePage open(String baseUrl){
       driver.get(baseUrl);
       return this;
    }
    /* Selecciono opcion home */
    public HomePage selectOption(MenuOption opc){
        String opString = String.format("//a[normalize-space()='%s']", opc.getPath());
        By select = By.xpath(opString);
        click(select);
        return this;
    }
    /* Creo clase ENUM propia de home page para pasarle por parametro la opcion elegida y no tipear incorrectamente el usuario */
    // ENUM interno para las opciones del menú
    public enum MenuOption {
        /* Opciones de ENUM */
        ALERTS("JavaScript Alerts"),
        CONFIRMS("Click for JS Confirm"),
        PROMPT("Click for JS Prompt");
        
        /* Creo variable PATH que es el string del ENUM */
        private final String path;
        /* Metodo para inicializar los ENUM */
        MenuOption(String path) {
            this.path = path;
        }
        /* Obtengo el valor del ENUM String */
        public String getPath() {
            return path;
        }
     /* ..................................................................................................................... */
    }
    
}

