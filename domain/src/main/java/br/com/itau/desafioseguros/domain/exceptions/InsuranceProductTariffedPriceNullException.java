package br.com.itau.desafioseguros.domain.exceptions;

public class InsuranceProductTariffedPriceNullException extends BaseException {
    public InsuranceProductTariffedPriceNullException() {
        super("O preço tarifado do produto de seguro não deve ser nulo");
    }
}
