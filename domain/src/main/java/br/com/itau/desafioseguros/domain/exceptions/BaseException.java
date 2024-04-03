package br.com.itau.desafioseguros.domain.exceptions;

public class BaseException extends RuntimeException {
    public BaseException(String message) {
        super(message);
    }
}
