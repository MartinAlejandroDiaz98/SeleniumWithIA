package com.example.components;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class TableComponent {
    private WebDriver driver;
    private By tableLocator;

    public TableComponent(WebDriver driver, By tableLocator) {
        this.driver = driver;
        this.tableLocator = tableLocator;
    }

    private List<WebElement> getRows() {
        return driver.findElement(tableLocator).findElements(By.cssSelector("tbody tr"));
    }

    public String findCellByText(String search, int returnColIndex) {
        for (WebElement row : getRows()) {
            List<WebElement> cols = row.findElements(By.tagName("td"));
            if (cols.stream().anyMatch(c -> c.getText().equalsIgnoreCase(search))) {
                return cols.get(returnColIndex).getText();
            }
        }
        return null;
    }
    
    public int getRowCount() {
        return getRows().size();
    }

    public int getColumnIndex(String columnName) {
        List<WebElement> headers = driver.findElement(tableLocator).findElements(By.cssSelector("thead th"));
        for (int i = 0; i < headers.size(); i++) {
            if (headers.get(i).getText().trim().equalsIgnoreCase(columnName)) {
                return i;
            }
        }
        throw new RuntimeException("Columna no encontrada: " + columnName);
    }

    public WebElement getRowByCellText(String columnName, String text) {
        int colIndex = getColumnIndex(columnName);
        List<WebElement> rows = driver.findElement(tableLocator).findElements(By.cssSelector("tbody tr"));
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
