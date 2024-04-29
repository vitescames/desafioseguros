package br.com.itau.desafioseguros.application.exceptions;

public class ValidationError {

    private final String field;

    private final String invalidValue;

    private final String errorMessage;

    public ValidationError(String field, String invalidValue, String errorMessage) {
        this.field = field;
        this.invalidValue = invalidValue;
        this.errorMessage = errorMessage;
    }

    public String getField() {
        return field;
    }

    public String getInvalidValue() {
        return invalidValue;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
