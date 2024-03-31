package br.com.itau.desafioseguros.application.exceptions;

public class ValidationError {

    private String field;

    private String invalidValue;

    private String errorMessage;

    public ValidationError(String field, String invalidValue, String errorMessage) {
        this.field = field;
        this.invalidValue = invalidValue;
        this.errorMessage = errorMessage;
    }
}
