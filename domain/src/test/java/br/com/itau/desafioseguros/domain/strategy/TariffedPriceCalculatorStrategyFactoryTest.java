package br.com.itau.desafioseguros.domain.strategy;

import br.com.itau.desafioseguros.domain.enums.InsuranceProductCategory;
import br.com.itau.desafioseguros.domain.exceptions.TariffedPriceCalculatorStrategyNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class TariffedPriceCalculatorStrategyFactoryTest {

    private TariffedPriceCalculatorStrategyFactory strategyFactory;

    @BeforeEach
    void setUp() {
        this.strategyFactory = new TariffedPriceCalculatorStrategyFactory(Set.of(new ViagemTariffedPriceCalculatorStrategy(),
                new VidaTariffedPriceCalculatorStrategy(),
                new AutoTariffedPriceCalculatorStrategy(),
                new PatrimonialTariffedPriceCalculatorStrategy(),
                new ResidTariffedPriceCalculatorStrategy()));
    }

    @Test
    void vidaStrategy_test() {
        VidaTariffedPriceCalculatorStrategy strategy = (VidaTariffedPriceCalculatorStrategy) this.strategyFactory.getStrategy(InsuranceProductCategory.VIDA);
        assertEquals(103.2f, strategy.calculate(100f));
    }

    @Test
    void viagemStrategy_test() {
        ViagemTariffedPriceCalculatorStrategy strategy = (ViagemTariffedPriceCalculatorStrategy) this.strategyFactory.getStrategy(InsuranceProductCategory.VIAGEM);
        assertEquals(107.0f, strategy.calculate(100f));
    }

    @Test
    void residStrategy_test() {
        ResidTariffedPriceCalculatorStrategy strategy = (ResidTariffedPriceCalculatorStrategy) this.strategyFactory.getStrategy(InsuranceProductCategory.RESIDENCIAL);
        assertEquals(134.0f, strategy.calculate(100f));
    }

    @Test
    void patrimStrategy_test() {
        PatrimonialTariffedPriceCalculatorStrategy strategy = (PatrimonialTariffedPriceCalculatorStrategy) this.strategyFactory.getStrategy(InsuranceProductCategory.PATRIMONIAL);
        assertEquals(108.0f, strategy.calculate(100f));
    }

    @Test
    void autoStrategy_test() {
        AutoTariffedPriceCalculatorStrategy strategy = (AutoTariffedPriceCalculatorStrategy) this.strategyFactory.getStrategy(InsuranceProductCategory.AUTO);
        assertEquals(110.5f, strategy.calculate(100f));
    }

    @Test
    void nullStrategy_test() {
        this.strategyFactory = new TariffedPriceCalculatorStrategyFactory(Set.of(new ViagemTariffedPriceCalculatorStrategy(),
                new VidaTariffedPriceCalculatorStrategy()));
        assertThrows(TariffedPriceCalculatorStrategyNotFoundException.class, () -> this.strategyFactory.getStrategy(InsuranceProductCategory.AUTO));
    }

}