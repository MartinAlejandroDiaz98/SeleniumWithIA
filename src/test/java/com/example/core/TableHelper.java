package com.example.core;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class TableHelper {
    private final WebDriver driver;
    private final WebElement table;

    public TableHelper(WebDriver driver, By tableLocator) {
        this.driver = driver;
        this.table = driver.findElement(tableLocator);
    }

    public int getColumnIndex(String columnName) {
        List<WebElement> headers = table.findElements(By.cssSelector("thead th"));
        for (int i = 0; i < headers.size(); i++) {
            if (headers.get(i).getText().trim().equalsIgnoreCase(columnName)) {
                return i;
            }
        }
        throw new RuntimeException("Columna no encontrada: " + columnName);
    }

    public WebElement getRowByCellText(String columnName, String text) {
        int colIndex = getColumnIndex(columnName);
        List<WebElement> rows = table.findElements(By.cssSelector("tbody tr"));
        for (WebElement row : rows) {
            List<WebElement> cells = row.findElements(By.tagName("td"));
            if (cells.size() > colIndex && cells.get(colIndex).getText().trim().equalsIgnoreCase(text)) {
                return row;
            }
        }
        throw new RuntimeException("Fila no encontrada con texto: " + text);
    }

    public String getCellValue(String searchColumn, String searchText, String returnColumn) {
        WebElement row = getRowByCellText(searchColumn, searchText);
        int colIndex = getColumnIndex(returnColumn);
        return row.findElements(By.tagName("td")).get(colIndex).getText();
    }

    public void clickInRow(String searchColumn, String searchText, By cellLocator) {
        WebElement row = getRowByCellText(searchColumn, searchText);
        row.findElement(cellLocator).click();
    }
}
