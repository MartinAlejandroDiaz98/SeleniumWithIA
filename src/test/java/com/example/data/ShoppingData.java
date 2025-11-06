package com.example.data;

import java.util.List;

import net.bytebuddy.implementation.bytecode.Throw;

public class ShoppingData {
    private final List<String> productsNameList;
    private final String firstName;
    private final String lastName;
    private final String zipCode;
    private final String completeMessage;
    /* CONSTRUCTOR PRIVADO */
    private ShoppingData(Builder builder){
        this.productsNameList = builder.productsNameList;
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.zipCode = builder.zipCode;
        this.completeMessage = builder.completeMessage;
    }

    /* GETTERS VARIABLES DATA */
    public List<String> getProductsNameList() {
        return productsNameList;
    }
    public String getFirstName(){
        return firstName;
    }
    public String getLastName(){
        return lastName;
    }
    public String getZipCode(){
        return zipCode;
    }
    public String getCompleteMessage(){
        return completeMessage;
    }
    /* SUBCLASE BUILDER */
    public static class Builder{
        private List<String> productsNameList;
        private String firstName;
        private String lastName;
        private String zipCode;
        private String completeMessage;
        /* METODOS INICIACION A ELECCION, PARA NO FORZAR OBLIGATORIO EN CONSTRUCTOR ALGUN DATA */
        public Builder withProductsNameList (List<String> productsNameList){
            this.productsNameList = productsNameList;
            return this;
        }
        public Builder withFirstName (String firstName){
            this.firstName = firstName;
            return this;
        }
        public Builder withLastName (String lastName){
            this.lastName = lastName;
            return this;
        }
        public Builder withZipCode (String zipCode){
            this.zipCode = zipCode;
            return this;
        }
        public Builder withCompleteMessage (String completeMessage){
            this.completeMessage = completeMessage;
            return this;
        }
        /* BUILD PARA FORMAR EL OBJETO, PODRIAMOS HACER VERIFICACIONES DE NULL PERO ACA HAY PRUEBAS QUE NECESITAN UNA U OTRA COSA */
        public ShoppingData build(){
            
            return new ShoppingData(this);
        }
    }
}
