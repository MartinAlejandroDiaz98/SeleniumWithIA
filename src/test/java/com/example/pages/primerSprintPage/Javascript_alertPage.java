package com.example.pages.primerSprintPage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.example.pages.BasePage;

public class Javascript_alertPage extends BasePage {
    private By resultVerification = By.id("result");
    /* Constructor */
    public Javascript_alertPage(WebDriver driver, long explicitWaitSeconds) {
        super(driver, explicitWaitSeconds);
        //TODO Auto-generated constructor stub
    }

    /* Selecciono alerta */
    public Javascript_alertPage selectAlert(SelectAlert opc){
        String opString = String.format("//button[normalize-space()='%s']", opc.getScript());
                                        //button[normalize-space()='Click for JS Alert']
        By opAlert = By.xpath(opString);
        click(opAlert);
        return this;
    }
    /* Clase enum con opciones de alert */
    public enum SelectAlert{
        JS_ALERT("Click for JS Alert"),
        JS_CONFIRM("Click for JS Confirm"),
        JS_PROMPT("Click for JS Prompt");
        
        private final String script;
    

        SelectAlert(String script) {
            this.script = script;
            
        }

        public String getScript() {
            return script;
        } 
        
    }
    /* Obtengo texto de resultado */
    public String getResultText(){
        return getText(resultVerification);
    }
}
