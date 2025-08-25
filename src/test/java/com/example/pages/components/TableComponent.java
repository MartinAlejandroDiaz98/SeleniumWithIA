package com.example.pages.components;

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
}
