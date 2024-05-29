package br.com.itau.desafioseguros.domain.strategy;

import br.com.itau.desafioseguros.domain.valueobjects.InsuranceProductCategory;

import java.math.BigDecimal;

public class PatrimonialTariffedPriceCalculatorStrategy extends TariffedPriceCalculatorStrategy {

    public PatrimonialTariffedPriceCalculatorStrategy() {
        super(BigDecimal.valueOf(0.05), BigDecimal.valueOf(0), BigDecimal.valueOf(0.03));
    }

    @Override
    public InsuranceProductCategory getInsuranceProductCategoryEnum() {
        return InsuranceProductCategory.PATRIMONIAL;
    }
}
