package br.com.itau.desafioseguros.domain.services;

import br.com.itau.desafioseguros.domain.valueobjects.InsuranceProductCategory;
import br.com.itau.desafioseguros.domain.exceptions.TariffedPriceCalculatorStrategyNotFoundException;
import br.com.itau.desafioseguros.domain.services.strategy.tariffedpricecalculator.TariffedPriceCalculatorStrategy;

import java.math.BigDecimal;
import java.util.EnumMap;
import java.util.Map;
import java.util.Set;

public class TariffedPriceCalculatorService {

    private final Map<InsuranceProductCategory, TariffedPriceCalculatorStrategy> strategiesMap = new EnumMap<>(InsuranceProductCategory.class);

    public TariffedPriceCalculatorService(Set<TariffedPriceCalculatorStrategy> strategies) {
        strategies.forEach(strategy -> strategiesMap.put(strategy.getInsuranceProductCategoryEnum(), strategy));
    }

    public BigDecimal calculate(BigDecimal basePrice, InsuranceProductCategory insuranceProductCategory) {
        TariffedPriceCalculatorStrategy strategy = strategiesMap.get(insuranceProductCategory);

        if (strategy == null) {
            throw new TariffedPriceCalculatorStrategyNotFoundException(insuranceProductCategory);
        }

        return strategy.calculate(basePrice);
    }

}
