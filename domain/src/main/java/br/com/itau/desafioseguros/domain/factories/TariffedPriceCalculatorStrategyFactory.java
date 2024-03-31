package br.com.itau.desafioseguros.domain.factories;

import br.com.itau.desafioseguros.domain.enums.InsuranceProductCategory;
import br.com.itau.desafioseguros.domain.exceptions.TariffedPriceCalculatorStrategyNotFoundException;
import br.com.itau.desafioseguros.domain.strategy.TariffedPriceCalculatorStrategy;

import java.util.EnumMap;
import java.util.Map;
import java.util.Set;

public class TariffedPriceCalculatorStrategyFactory {

    private final Map<InsuranceProductCategory, TariffedPriceCalculatorStrategy> strategiesMap = new EnumMap<>(InsuranceProductCategory.class);

    public TariffedPriceCalculatorStrategyFactory(Set<TariffedPriceCalculatorStrategy> strategies) {
        strategies.forEach(strategy -> strategiesMap.put(strategy.getInsuranceProductCategoryEnum(), strategy));
    }

    public TariffedPriceCalculatorStrategy getStrategy(InsuranceProductCategory insuranceProductCategory) {
        TariffedPriceCalculatorStrategy strategy = strategiesMap.get(insuranceProductCategory);

        if (strategy == null) {
            throw new TariffedPriceCalculatorStrategyNotFoundException(insuranceProductCategory);
        }

        return strategy;
    }

}
