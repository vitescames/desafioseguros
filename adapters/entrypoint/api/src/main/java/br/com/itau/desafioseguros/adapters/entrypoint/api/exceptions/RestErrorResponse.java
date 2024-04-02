package br.com.itau.desafioseguros.adapters.entrypoint.api.exceptions;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class RestErrorResponse {

    private long timestamp = new Date().getTime();

    @JsonProperty("mensagem")
    private String message;

    @JsonProperty("erros")
    private List<RestValidationError> errors;

    public RestErrorResponse(String message, List<RestValidationError> errors) {
        this.message = message;
        this.errors = errors;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<RestValidationError> getErrors() {
        return errors;
    }

    public void setErrors(List<RestValidationError> errors) {
        this.errors = errors;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}
