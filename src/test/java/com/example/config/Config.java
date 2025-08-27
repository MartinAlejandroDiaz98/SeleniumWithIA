package com.example.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Config {
    private final Properties props = new Properties();

    public Config() {
        // Cargar defaults desde resources
        try (InputStream is = getClass().getClassLoader().getResourceAsStream("config.properties")) {
            if (is != null) props.load(is);
        } catch (IOException e) {
            throw new RuntimeException("No se pudo cargar config.properties", e);
        }
        // Overwrite con -Dprop
        System.getProperties().forEach((k, v) -> props.setProperty(k.toString(), v.toString()));
    }
    /* Metodos para traer el valor del archivo CONFIG.PROPERTIES de seteos base como por ej navegadores o base url */
    public String getBaseUrl() {
        return props.getProperty("baseUrl");
    }
    public String getBrowser() {
        return props.getProperty("browser", "chrome");
    }
    public boolean isHeadless() {
        return Boolean.parseBoolean(props.getProperty("headless", "false"));
    }
    public String getEnv() {
        return props.getProperty("env", "local");
    }
    public String getGridUrl() {
        return props.getProperty("gridUrl", "");
    }
    public long getImplicitWait() { return Long.parseLong(props.getProperty("implicitWait", "0")); }
    public long getExplicitWait() { return Long.parseLong(props.getProperty("explicitWait", "10")); }
    /* ------------------------------------------------------------------------------------------------------------ */
    
}
