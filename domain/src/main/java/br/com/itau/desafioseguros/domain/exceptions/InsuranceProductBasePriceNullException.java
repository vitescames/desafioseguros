package br.com.itau.desafioseguros.domain.exceptions;

public class InsuranceProductBasePriceNullException extends BaseException {
    public InsuranceProductBasePriceNullException() {
        super("O preço base do produto de seguro não deve estar nulo/vazio");
    }
}
