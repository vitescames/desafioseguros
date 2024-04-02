package br.com.itau.desafioseguros.adapters.entrypoint.api.exceptions;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RestValidationError {

    @JsonProperty("campo")
    private String field;

    @JsonProperty("valor_invalido")
    private String invalidValue;

    @JsonProperty("causa")
    private String errorMessage;

    public RestValidationError(String field, String invalidValue, String errorMessage) {
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
