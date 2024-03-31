package br.com.itau.desafioseguros.domain.strategy;

import br.com.itau.desafioseguros.domain.enums.InsuranceProductCategory;

public class PatrimonialTariffedPriceCalculatorStrategy implements TariffedPriceCalculatorStrategy {

    private final float iof = 0.05f;

    private final float pis = 0.03f;

    private final float cofins = 0;

    @Override
    public float calculate(float basePrice) {
        return this.calculate(basePrice, iof, pis, cofins);
    }

    @Override
    public InsuranceProductCategory getInsuranceProductCategoryEnum() {
        return InsuranceProductCategory.PATRIMONIAL;
    }
}
