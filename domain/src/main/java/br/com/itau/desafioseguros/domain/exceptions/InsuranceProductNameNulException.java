package br.com.itau.desafioseguros.domain.exceptions;

public class InsuranceProductNameNulException extends BaseException {
    public InsuranceProductNameNulException() {
        super("O nome do produto de seguro não deve estar nulo/vazio");
    }
}
