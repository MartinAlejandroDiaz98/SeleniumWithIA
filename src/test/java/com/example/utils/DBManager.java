package com.example.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DBManager {

    // URL para la base de datos H2 en modo File-Based (guardado en un archivo local)
    // ~/testDB.mv.db se creará en el directorio home del usuario.
    private static final String DB_URL = "jdbc:h2:~/testDB"; 
    private static final String DB_USER = "sa"; // Usuario por defecto de H2
    private static final String DB_PASSWORD = ""; // Contraseña por defecto de H2

    /**
     * Establece la conexión con la base de datos H2.
     * @return Objeto Connection.
     */
    private Connection getConnection() throws SQLException {
        // La clase del driver para H2 se registra automáticamente
        return DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
    }

    /**
     * Ejecuta una consulta SQL (SELECT) y devuelve los resultados 
     * como una lista de mapas (ideal para DataProvider).
     * @param sqlQuery La consulta SQL a ejecutar.
     * @return Una lista de Mapas, donde cada mapa es una fila de datos.
     */
    public List<Map<String, Object>> executeQuery(String sqlQuery) {
        List<Map<String, Object>> data = new ArrayList<>();

        // Uso del try-with-resources para asegurar que Connection y Statement se cierren automáticamente
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sqlQuery)) {
            
            // Metadatos para obtener nombres de columnas
            ResultSetMetaData rsmd = rs.getMetaData();
            int columnCount = rsmd.getColumnCount();

            // Iterar sobre las filas del ResultSet
            while (rs.next()) {
                Map<String, Object> row = new HashMap<>();
                // Iterar sobre las columnas de cada fila
                for (int i = 1; i <= columnCount; i++) {
                    // Guarda el nombre de la columna (key) y su valor (value)
                    row.put(rsmd.getColumnName(i).toUpperCase(), rs.getObject(i));
                }
                data.add(row);
            }

        } catch (SQLException e) {
            System.err.println("Error al ejecutar la consulta SQL: " + e.getMessage());
            // En un framework real, usarías un logger y lanzarías una excepción de tiempo de ejecución.
        }
        return data;
    }

    /**
     * Ejecuta una sentencia SQL que no devuelve un ResultSet (INSERT, UPDATE, DELETE, CREATE).
     * @param sqlStatement La sentencia SQL a ejecutar.
     * @return El número de filas afectadas.
     */
    public int executeUpdate(String sqlStatement) {
        int rowsAffected = 0;
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement()) {
            
            rowsAffected = stmt.executeUpdate(sqlStatement);

        } catch (SQLException e) {
            System.err.println("Error al ejecutar la actualización SQL: " + e.getMessage());
        }
        return rowsAffected;
    }
    
    // Método para preparar la DB creando tablas y insertando datos de prueba (SETUP)
    public void setupDatabase() {
        // 1. Crear la tabla si no existe
        String createTableSQL = "CREATE TABLE IF NOT EXISTS USERS ("
                              + "ID INT PRIMARY KEY AUTO_INCREMENT, "
                              + "USERNAME VARCHAR(50) NOT NULL, "
                              + "PASSWORD VARCHAR(50) NOT NULL, "
                              + "EMAIL VARCHAR(100) UNIQUE)";
        executeUpdate(createTableSQL);

        // 2. Insertar datos de prueba
        executeUpdate("INSERT INTO USERS (USERNAME, PASSWORD, EMAIL) VALUES ('tester1', 'pass123', 't1@example.com')");
        executeUpdate("INSERT INTO USERS (USERNAME, PASSWORD, EMAIL) VALUES ('tester2', 'secure456', 't2@example.com')");
        executeUpdate("INSERT INTO USERS (USERNAME, PASSWORD, EMAIL) VALUES ('locked_user', 'fail_pass', 't3@example.com')");
        
        System.out.println("Base de datos H2 configurada con datos de prueba.");
    }
}
