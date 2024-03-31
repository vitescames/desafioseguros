package br.com.itau.desafioseguros;

import br.com.itau.desafioseguros.domain.factories.TariffedPriceCalculatorStrategyFactory;
import br.com.itau.desafioseguros.domain.strategy.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Set;

@Configuration
public class BeanConfiguration {

    @Bean
    public TariffedPriceCalculatorStrategy vidaTariffedPriceCalculatorStrategy() {
        return new VidaTariffedPriceCalculatorStrategy();
    }

    @Bean
    public TariffedPriceCalculatorStrategy autoTariffedPriceCalculatorStrategy() {
        return new AutoTariffedPriceCalculatorStrategy();
    }

    @Bean
    public TariffedPriceCalculatorStrategy viagemTariffedPriceCalculatorStrategy() {
        return new ViagemTariffedPriceCalculatorStrategy();
    }

    @Bean
    public TariffedPriceCalculatorStrategy residTariffedPriceCalculatorStrategy() {
        return new ResidTariffedPriceCalculatorStrategy();
    }

    @Bean
    public TariffedPriceCalculatorStrategy patrimonialTariffedPriceCalculatorStrategy() {
        return new PatrimonialTariffedPriceCalculatorStrategy();
    }

    @Bean
    public TariffedPriceCalculatorStrategyFactory tariffedPriceCalculatorStrategyFactory(Set<TariffedPriceCalculatorStrategy> strategies) {
        return new TariffedPriceCalculatorStrategyFactory(strategies);
    }

}
