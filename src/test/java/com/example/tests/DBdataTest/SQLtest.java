package com.example.tests.DBdataTest;

import java.util.List;
import java.util.Map;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.example.utils.DBManager;

public class SQLtest {
    // Instancia de tu Manager de DB
    private DBManager dbManager = new DBManager();
    
    // Primero, asegura que la DB esté lista antes de obtener los datos.
    // Esto se haría en un @BeforeSuite o @BeforeClass, pero lo ponemos aquí para el ejemplo:
    public SQLtest() {
        dbManager.setupDatabase();
    }

    /**
     * DataProvider que obtiene los datos de login desde la base de datos H2.
     * @return Object[][] compatible con TestNG.
     */
    @DataProvider(name = "dbUsers")
    public Object[][] getDBLoginData() {
        
        // Consulta SQL para obtener solo los usuarios que queremos testear
        String query = "SELECT USERNAME, PASSWORD FROM USERS WHERE USERNAME LIKE 'tester%'";
        
        // Obtiene los datos como una lista de Mapas
        List<Map<String, Object>> userDataList = dbManager.executeQuery(query);

        // Crear el Object[][] del tamaño correcto
        Object[][] testData = new Object[userDataList.size()][2]; // [filas][columnas: USERNAME, PASSWORD]

        // Iterar sobre la lista para poblar el Object[][]
        for (int i = 0; i < userDataList.size(); i++) {
            Map<String, Object> row = userDataList.get(i);
            // Asegúrate de que los nombres de las columnas coincidan (en mayúsculas por JDBC)
            testData[i][0] = row.get("USERNAME"); 
            testData[i][1] = row.get("PASSWORD"); 
        }

        return testData;
    }

    // Ejemplo de Test Case de Selenium
    @Test(dataProvider = "dbUsers")
    public void testLoginWithDBData(String username, String password) {
        System.out.println("--- Ejecutando prueba con datos de DB ---");
        System.out.println("Usuario: " + username + " | Contraseña: " + password);
        
        // **Aquí iría tu lógica de Selenium:**
        // driver.findElement(By.id("username")).sendKeys(username);
        // driver.findElement(By.id("password")).sendKeys(password);
        // driver.findElement(By.id("loginButton")).click();
        
        // Validación...
        // Assert.assertTrue(driver.getCurrentUrl().endsWith("/dashboard"));
    }
}
