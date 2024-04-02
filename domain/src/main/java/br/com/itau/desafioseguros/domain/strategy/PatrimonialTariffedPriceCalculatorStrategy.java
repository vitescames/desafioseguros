package br.com.itau.desafioseguros.domain.strategy;

import br.com.itau.desafioseguros.domain.enums.InsuranceProductCategory;

public class PatrimonialTariffedPriceCalculatorStrategy implements TariffedPriceCalculatorStrategy {

    @Override
    public float calculate(float basePrice) {
        float iof = 0.05f;
        float cofins = 0;
        float pis = 0.03f;
        return this.calculate(basePrice, iof, pis, cofins);
    }

    @Override
    public InsuranceProductCategory getInsuranceProductCategoryEnum() {
        return InsuranceProductCategory.PATRIMONIAL;
    }
}
