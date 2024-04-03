package br.com.itau.desafioseguros.domain.exceptions;

public class InsuranceProductIdNullException extends BaseException {
    public InsuranceProductIdNullException() {
        super("O ID do produto de seguro não deve ser nulo");
    }
}
