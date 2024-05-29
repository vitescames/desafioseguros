package br.com.itau.desafioseguros.domain.strategy;

import br.com.itau.desafioseguros.domain.valueobjects.InsuranceProductCategory;

import java.math.BigDecimal;

public class VidaTariffedPriceCalculatorStrategy extends TariffedPriceCalculatorStrategy {

    public VidaTariffedPriceCalculatorStrategy() {
        super(BigDecimal.valueOf(0.01), BigDecimal.valueOf(0.022), BigDecimal.valueOf(0));
    }

    @Override
    public InsuranceProductCategory getInsuranceProductCategoryEnum() {
        return InsuranceProductCategory.VIDA;
    }
}
