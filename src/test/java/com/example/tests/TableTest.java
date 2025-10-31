package com.example.tests;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.example.components.TableComponent;
import com.example.utils.Waits;

public class TableTest extends BaseTest {
    @Test
    public void testTableComponent() {
        driver.get("https://the-internet.herokuapp.com/tables");
        By tableLocator = By.cssSelector("#table1");
        TableComponent table = new TableComponent(driver, tableLocator);
        Waits wait = new Waits(driver, 10);
        wait.visible(tableLocator);
        /* Paso parametros de busqueda en un MAP */
        Map<Integer, String> values = new HashMap<>();
        values.put(0, "Smith");
        values.put(2, "jsmith@gmail.com");
        By edit = By.cssSelector("a[href='#edit']");
        System.out.println("asd");
        table.clickButtonInRow(table.findRowByValues(values), edit);
        Assert.assertEquals(driver.getCurrentUrl(), "https://the-internet.herokuapp.com/tables#edit");
    }
}
