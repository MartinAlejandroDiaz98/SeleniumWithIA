package com.example.data;
import com.example.pages.sauceDemo.LoginSaucePage.LoginErrorMessage;

 /**
 * Clase final (Inmutable) que representa el objeto de datos de prueba para el Login.
 * Este objeto es el "Producto" que el Builder entrega al DataProvider.
 */
public final class LoginData { // Se recomienda "final" para inmutabilidad

    // ----------------------------------------------------------------------
    // 1. CAMPOS (El estado del objeto)
    // ----------------------------------------------------------------------
    private final String username;
    private final String password;
    private final String expectedMessage; // El ENUM del mensaje de error
    private final boolean rememberMe; // Campo Opcional de ejemplo

    /**
     * CONSTRUCTOR PRIVADO:
     * Propósito Senior: Forzar el uso del Builder. 
     * Nadie puede crear un objeto LoginData directamente; debe pasar por la clase Builder, 
     * lo que asegura que la validación y el proceso de construcción se ejecuten.
     */
    private LoginData(Builder builder) {
        this.username = builder.username;
        this.password = builder.password;
        this.expectedMessage = builder.expectedMessage;
        this.rememberMe = builder.rememberMe;
    }

    // ----------------------------------------------------------------------
    // 2. GETTERS: Única forma de acceder al estado (Refuerza Inmutabilidad)
    // ----------------------------------------------------------------------
    public String getUsername() { return username; }
    public String getPassword() { return password; }
    public String getExpectedMessage() { return expectedMessage; }
    public boolean isRememberMe() { return rememberMe; }


    // ======================================================================
    // 3. CLASE BUILDER (La Subclase Estática)
    // ======================================================================

    /**
     * CLASE BUILDER (Estatica Anidada):
     * Propósito Senior: Contiene la lógica paso a paso para construir el objeto.
     * Al ser estática, puede ser instanciada sin necesidad de una instancia de LoginData.
     */
    public static class Builder {
        // Los campos del Builder no necesitan ser final porque cambian durante la construcción.
        private final String username; // Obligatorio
        private final String password; // Obligatorio
        
        // Campos Opcionales (con valor por defecto, si aplica)
        private String expectedMessage = null; 
        private boolean rememberMe = false; 

        /**
         * CONSTRUCTOR DEL BUILDER:
         * Propósito Senior: Aquí se definen los parámetros OBLIGATORIOS para la creación del objeto.
         * Si un desarrollador omite estos, el IDE le alertará inmediatamente.
         */
        public Builder(String username, String password) {
            this.username = username;
            this.password = password;
        }
        
        // ----------------------------------------------------------------------
        // 4. MÉTODOS "WITH" (Configuración Opcional - Fluent Interface)
        // ----------------------------------------------------------------------

        /**
         * Método para configurar el mensaje esperado (Campo opcional/condicional).
         * @return Retorna 'this' (la propia instancia del Builder) para permitir el encadenamiento de llamadas (Fluent Interface).
         */
        public Builder withExpectedMessage(String message) {
            this.expectedMessage = message;
            return this;
        }

        /**
         * Método para configurar el campo rememberMe.
         * @return Retorna 'this' para Fluent Interface.
         */
        public Builder withRememberMe() {
            this.rememberMe = true;
            return this;
        }
        
        // ----------------------------------------------------------------------
        // 5. MÉTODO FINAL BUILD() (El Portero de Calidad)
        // ----------------------------------------------------------------------

        /**
         * MÉTODO BUILD():
         * Propósito Senior: Ejecuta validaciones finales y crea la instancia final de LoginData.
         * Aquí se asegura la consistencia de los datos antes de que el objeto exista.
         */
        public LoginData build() {
            // Ejemplo de Validación: Aseguramos que el mensaje esperado siempre se defina en un test de ERROR.
            if (this.expectedMessage == null) {
                // Lanza una excepción clara ANTES de la ejecución del test.
                throw new IllegalStateException("ERROR: 'expectedMessage' es obligatorio para este tipo de test. Por favor, configúrelo con .withExpectedMessage().");
            }
            
            // Si las validaciones pasan, llama al constructor privado de LoginData, pasando el estado actual del Builder.
            return new LoginData(this);
        }
    }
}
