package br.com.itau.desafioseguros.domain.strategy;

import br.com.itau.desafioseguros.domain.valueobjects.InsuranceProductCategory;

public class AutoTariffedPriceCalculatorStrategy implements TariffedPriceCalculatorStrategy {

    @Override
    public float calculate(float basePrice) {
        float iof = 0.055f;
        float pis = 0.04f;
        float cofins = 0.01f;
        return this.calculate(basePrice, iof, pis, cofins);
    }

    @Override
    public InsuranceProductCategory getInsuranceProductCategoryEnum() {
        return InsuranceProductCategory.AUTO;
    }
}
