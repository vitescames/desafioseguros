package br.com.itau.desafioseguros.domain.strategy;

import br.com.itau.desafioseguros.domain.enums.InsuranceProductCategory;

public class ResidTariffedPriceCalculatorStrategy implements TariffedPriceCalculatorStrategy {

    @Override
    public float calculate(float basePrice) {
        float iof = 0.04f;
        float pis = 0;
        float cofins = 0.3f;
        return this.calculate(basePrice, iof, pis, cofins);
    }

    @Override
    public InsuranceProductCategory getInsuranceProductCategoryEnum() {
        return InsuranceProductCategory.RESIDENCIAL;
    }
}
