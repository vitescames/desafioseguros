package br.com.itau.desafioseguros.domain.services.strategy;

import br.com.itau.desafioseguros.domain.valueobjects.InsuranceProductCategory;

import java.math.BigDecimal;

public abstract class TariffedPriceCalculatorStrategy {

    protected BigDecimal iof;
    protected BigDecimal pis;
    protected BigDecimal cofins;

    protected TariffedPriceCalculatorStrategy(BigDecimal iof, BigDecimal pis, BigDecimal cofins) {
        this.iof = iof;
        this.pis = pis;
        this.cofins = cofins;
    }

    public BigDecimal calculate(BigDecimal basePrice) {
        return basePrice.add(basePrice.multiply(iof)).add(basePrice.multiply(pis)).add(basePrice.multiply(cofins));
    }

    protected abstract InsuranceProductCategory getInsuranceProductCategoryEnum();

}
