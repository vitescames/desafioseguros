package br.com.itau.desafioseguros.domain.exceptions;

public class EmptyInsuranceProductNameException extends BaseException {
    public EmptyInsuranceProductNameException(String name) {
        super("O nome do produto de seguro não deve ser vazio/nulo");
    }
}
