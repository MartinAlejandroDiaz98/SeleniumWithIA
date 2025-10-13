package com.example.components;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

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
    private WebElement getTable(){
        return driver.findElement(tableLocator);
    }

    private List<WebElement> getRows() {
        /* Retorna filas datable, no cabecera que es TH */
        return getTable().findElements(By.cssSelector("tbody tr"));
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
    /* Cuento filas */
    public int getRowCount() {
        return getRows().size();
    }
    /* Cuenta columnas */
    public int getColumnIndex(String columnName) {
        List<WebElement> headers = getTable().findElements(By.cssSelector("thead th"));
        for (int i = 0; i < headers.size(); i++) {
            if (headers.get(i).getText().trim().equalsIgnoreCase(columnName)) {
                return i;
            }
        }
        throw new RuntimeException("Columna no encontrada: " + columnName);
    }
    /* Busco fila por texto en UNA columna */
    public WebElement getRowByCellText(String columnName, String text) {
        int colIndex = getColumnIndex(columnName);
        List<WebElement> rows = getTable().findElements(By.cssSelector("tbody tr"));
        for (WebElement row : rows) {
            List<WebElement> cells = row.findElements(By.tagName("td"));
            if (cells.size() > colIndex && cells.get(colIndex).getText().trim().equalsIgnoreCase(text)) {
                return row;
            }
        }
        throw new RuntimeException("Fila no encontrada con texto: " + text);
    }
     // ✅ Buscar fila por múltiples condiciones (ej: nombre + rol)
    public WebElement findRowByValues(Map<Integer, String> colIndexValueMap) {
        return getRows().stream()
                .filter(row -> {
                    List<WebElement> cells = row.findElements(By.tagName("td"));
                    return colIndexValueMap.entrySet().stream()
                            .allMatch(e -> cells.get(e.getKey()).getText().equalsIgnoreCase(e.getValue()));
                })
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("No se encontró fila con valores: " + colIndexValueMap));
    }
    /* Obtengo valor de una celda */
    public String getCellValue(String searchColumn, String searchText, String returnColumn) {
        WebElement row = getRowByCellText(searchColumn, searchText);
        int colIndex = getColumnIndex(returnColumn);
        return row.findElements(By.tagName("td")).get(colIndex).getText();
    }
    /* Click en celda */
    public void clickInRow(String searchColumn, String searchText, By cellLocator) {
        WebElement row = getRowByCellText(searchColumn, searchText);
        row.findElement(cellLocator).click();
    }
    /* Click en el boton de la row encontrada/buscada */
    public void clickButtonInRow(WebElement row, By buttonLocator) {
          row.findElement(buttonLocator).click();
    }



}
