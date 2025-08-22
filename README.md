# Selenium + TestNG Mini-Framework (Java)

Listo para practicar o iniciar un proyecto propio.

## Requisitos
- Java 17+
- Maven 3.9+

## Ejecutar
```bash
mvn clean test -Dbrowser=chrome -Dheadless=false
```
Variables útiles (todas opcionales, también se pueden setear en `src/test/resources/config.properties`):
- `-Dbrowser=chrome|firefox|edge`
- `-Dheadless=true|false`
- `-DgridUrl=http://localhost:4444/wd/hub` (para Selenium Grid)
- `-Denv=local|qa|prod`

## Estructura
- `core/` → DriverManager (ThreadLocal), DriverFactory, Config, Waits
- `pages/` → Page Objects
- `tests/` → Tests (TestNG)
- `src/test/resources/testng.xml` → Suite
- `src/test/resources/config.properties` → Config por defecto

## Demo
Se usa la página pública `the-internet` (login) para un test de ejemplo.

