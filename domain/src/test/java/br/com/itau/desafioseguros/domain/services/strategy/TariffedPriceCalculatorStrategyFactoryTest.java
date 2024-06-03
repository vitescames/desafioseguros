package br.com.itau.desafioseguros.domain.services.strategy;

import br.com.itau.desafioseguros.domain.exceptions.TariffedPriceCalculatorStrategyNotFoundException;
import br.com.itau.desafioseguros.domain.valueobjects.InsuranceProductCategory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
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
        assertEquals(new BigDecimal("103.200"), strategy.calculate(BigDecimal.valueOf(100)));
    }

    @Test
    void viagemStrategy_test() {
        ViagemTariffedPriceCalculatorStrategy strategy = (ViagemTariffedPriceCalculatorStrategy) this.strategyFactory.getStrategy(InsuranceProductCategory.VIAGEM);
        assertEquals(new BigDecimal("107.00"), strategy.calculate(BigDecimal.valueOf(100)));
    }

    @Test
    void residStrategy_test() {
        ResidTariffedPriceCalculatorStrategy strategy = (ResidTariffedPriceCalculatorStrategy) this.strategyFactory.getStrategy(InsuranceProductCategory.RESIDENCIAL);
        assertEquals(new BigDecimal("134.00"), strategy.calculate(BigDecimal.valueOf(100)));
    }

    @Test
    void patrimStrategy_test() {
        PatrimonialTariffedPriceCalculatorStrategy strategy = (PatrimonialTariffedPriceCalculatorStrategy) this.strategyFactory.getStrategy(InsuranceProductCategory.PATRIMONIAL);
        assertEquals(new BigDecimal("108.00"), strategy.calculate(BigDecimal.valueOf(100)));
    }

    @Test
    void autoStrategy_test() {
        AutoTariffedPriceCalculatorStrategy strategy = (AutoTariffedPriceCalculatorStrategy) this.strategyFactory.getStrategy(InsuranceProductCategory.AUTO);
        assertEquals(new BigDecimal("110.500"), strategy.calculate(BigDecimal.valueOf(100)));
    }

    @Test
    void nullStrategy_test() {
        this.strategyFactory = new TariffedPriceCalculatorStrategyFactory(Set.of(new ViagemTariffedPriceCalculatorStrategy(),
                new VidaTariffedPriceCalculatorStrategy()));
        assertThrows(TariffedPriceCalculatorStrategyNotFoundException.class, () -> this.strategyFactory.getStrategy(InsuranceProductCategory.AUTO));
    }

}