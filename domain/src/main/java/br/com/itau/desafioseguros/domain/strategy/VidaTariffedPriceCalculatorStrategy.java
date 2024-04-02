package br.com.itau.desafioseguros.domain.strategy;

import br.com.itau.desafioseguros.domain.enums.InsuranceProductCategory;

public class VidaTariffedPriceCalculatorStrategy implements TariffedPriceCalculatorStrategy {

    @Override
    public float calculate(float basePrice) {
        float iof = 0.01f;
        float pis = 0.022f;
        float cofins = 0;
        return this.calculate(basePrice, iof, pis, cofins);
    }

    @Override
    public InsuranceProductCategory getInsuranceProductCategoryEnum() {
        return InsuranceProductCategory.VIDA;
    }
}
