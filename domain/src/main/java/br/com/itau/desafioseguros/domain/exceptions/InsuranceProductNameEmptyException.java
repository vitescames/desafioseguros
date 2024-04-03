package br.com.itau.desafioseguros.domain.exceptions;

public class InsuranceProductNameEmptyException extends BaseException {
    public InsuranceProductNameEmptyException() {
        super("O nome do produto de seguro não deve ser vazio/nulo");
    }
}
