package br.com.itau.desafioseguros.domain.strategy;

import br.com.itau.desafioseguros.domain.enums.InsuranceProductCategory;

public class ResidTariffedPriceCalculatorStrategy implements TariffedPriceCalculatorStrategy {

    private final float iof = 0.04f;

    private final float pis = 0;

    private final float cofins = 0.3f;

    @Override
    public float calculate(float basePrice) {
        return this.calculate(basePrice, iof, pis, cofins);
    }

    @Override
    public InsuranceProductCategory getInsuranceProductCategoryEnum() {
        return InsuranceProductCategory.RESIDENCIAL;
    }
}
