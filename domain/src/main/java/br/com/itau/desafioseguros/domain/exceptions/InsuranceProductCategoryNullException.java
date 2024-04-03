package br.com.itau.desafioseguros.domain.exceptions;

public class InsuranceProductCategoryNullException extends BaseException {
    public InsuranceProductCategoryNullException() {
        super("A categoria do produto de seguro não deve ser nula");
    }
}
