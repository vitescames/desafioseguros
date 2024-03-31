package br.com.itau.desafioseguros.domain.exceptions;

import br.com.itau.desafioseguros.domain.enums.InsuranceProductCategory;

public class TariffedPriceCalculatorStrategyNotFoundException extends BaseException {
    public TariffedPriceCalculatorStrategyNotFoundException(InsuranceProductCategory insuranceProductCategory) {
        super("Não foi encontrada uma implementação de cálculo de preço tarifado para a categoria " + insuranceProductCategory.toString());
    }
}
