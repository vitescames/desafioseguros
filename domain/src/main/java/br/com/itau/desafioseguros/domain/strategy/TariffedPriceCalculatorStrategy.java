package br.com.itau.desafioseguros.domain.strategy;

import br.com.itau.desafioseguros.domain.valueobjects.InsuranceProductCategory;

public interface TariffedPriceCalculatorStrategy {
    float calculate(float basePrice);

    default float calculate(float basePrice, float iof, float pis, float cofins) {
        return basePrice + (basePrice * iof) + (basePrice * pis) + (basePrice * cofins);
    }

    InsuranceProductCategory getInsuranceProductCategoryEnum();
}
