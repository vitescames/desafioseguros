package br.com.itau.desafioseguros.domain.services.strategy.tariffedpricecalculator;

import br.com.itau.desafioseguros.domain.valueobjects.InsuranceProductCategory;

import java.math.BigDecimal;

public class AutoTariffedPriceCalculatorStrategy extends TariffedPriceCalculatorStrategy {

    public AutoTariffedPriceCalculatorStrategy() {
        super(BigDecimal.valueOf(0.055), BigDecimal.valueOf(0.04), BigDecimal.valueOf(0.01));
    }

    @Override
    public InsuranceProductCategory getInsuranceProductCategoryEnum() {
        return InsuranceProductCategory.AUTO;
    }
}
