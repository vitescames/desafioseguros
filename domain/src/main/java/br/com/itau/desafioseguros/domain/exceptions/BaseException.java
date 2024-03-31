package br.com.itau.desafioseguros.domain.exceptions;

public abstract class BaseException extends RuntimeException {

    private String message;

    public BaseException(String message) {
        super(message);
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
