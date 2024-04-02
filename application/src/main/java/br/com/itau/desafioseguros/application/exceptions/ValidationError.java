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

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getInvalidValue() {
        return invalidValue;
    }

    public void setInvalidValue(String invalidValue) {
        this.invalidValue = invalidValue;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
