package br.com.itau.desafioseguros.domain.services.strategy.tariffedpricecalculator;

import br.com.itau.desafioseguros.domain.valueobjects.InsuranceProductCategory;

import java.math.BigDecimal;

public class ResidTariffedPriceCalculatorStrategy extends TariffedPriceCalculatorStrategy {

    public ResidTariffedPriceCalculatorStrategy() {
        super(BigDecimal.valueOf(0.04), BigDecimal.valueOf(0), BigDecimal.valueOf(0.3));
    }

    @Override
    public InsuranceProductCategory getInsuranceProductCategoryEnum() {
        return InsuranceProductCategory.RESIDENCIAL;
    }
}
