package br.com.itau.desafioseguros.domain.services;

import br.com.itau.desafioseguros.domain.valueobjects.InsuranceProductCategory;
import br.com.itau.desafioseguros.domain.exceptions.TariffedPriceCalculatorStrategyNotFoundException;
import br.com.itau.desafioseguros.domain.services.strategy.tariffedpricecalculator.AutoTariffedPriceCalculatorStrategy;
import br.com.itau.desafioseguros.domain.services.strategy.tariffedpricecalculator.PatrimonialTariffedPriceCalculatorStrategy;
import br.com.itau.desafioseguros.domain.services.strategy.tariffedpricecalculator.ResidTariffedPriceCalculatorStrategy;
import br.com.itau.desafioseguros.domain.services.strategy.tariffedpricecalculator.ViagemTariffedPriceCalculatorStrategy;
import br.com.itau.desafioseguros.domain.services.strategy.tariffedpricecalculator.VidaTariffedPriceCalculatorStrategy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class TariffedPriceCalculatorServiceTest {

    private TariffedPriceCalculatorService strategyService;

    @BeforeEach
    void setUp() {
        this.strategyService = new TariffedPriceCalculatorService(Set.of(new ViagemTariffedPriceCalculatorStrategy(),
                new VidaTariffedPriceCalculatorStrategy(),
                new AutoTariffedPriceCalculatorStrategy(),
                new PatrimonialTariffedPriceCalculatorStrategy(),
                new ResidTariffedPriceCalculatorStrategy()));
    }

    @Test
    void vidaStrategy_test() {
        assertEquals(new BigDecimal("103.200"), this.strategyService.calculate(BigDecimal.valueOf(100), InsuranceProductCategory.VIDA));
    }

    @Test
    void viagemStrategy_test() {
        assertEquals(new BigDecimal("107.00"), this.strategyService.calculate(BigDecimal.valueOf(100), InsuranceProductCategory.VIAGEM));
    }

    @Test
    void residStrategy_test() {
        assertEquals(new BigDecimal("134.00"), this.strategyService.calculate(BigDecimal.valueOf(100), InsuranceProductCategory.RESIDENCIAL));
    }

    @Test
    void patrimStrategy_test() {
        assertEquals(new BigDecimal("108.00"), this.strategyService.calculate(BigDecimal.valueOf(100), InsuranceProductCategory.PATRIMONIAL));
    }

    @Test
    void autoStrategy_test() {
        assertEquals(new BigDecimal("110.500"), this.strategyService.calculate(BigDecimal.valueOf(100), InsuranceProductCategory.AUTO));
    }

    @Test
    void nullStrategy_test() {
        this.strategyService = new TariffedPriceCalculatorService(Set.of(new ViagemTariffedPriceCalculatorStrategy(),
                new VidaTariffedPriceCalculatorStrategy()));
        BigDecimal basePrice = BigDecimal.valueOf(100);
        assertThrows(TariffedPriceCalculatorStrategyNotFoundException.class, () -> this.strategyService.calculate(basePrice, InsuranceProductCategory.AUTO));
    }

}