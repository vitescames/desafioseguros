package br.com.itau.desafioseguros.application.command.handlers;

import br.com.itau.desafioseguros.application.command.AddInsuranceProductCommand;
import br.com.itau.desafioseguros.application.command.handler.AddInsuranceProductCommandHandler;
import br.com.itau.desafioseguros.application.command.responses.AddInsuranceProductCommandResponse;
import br.com.itau.desafioseguros.application.command.validation.CommandValidator;
import br.com.itau.desafioseguros.application.event.publisher.EventBus;
import br.com.itau.desafioseguros.domain.entities.InsuranceProduct;
import br.com.itau.desafioseguros.domain.repositories.AddInsuranceProductRepository;
import br.com.itau.desafioseguros.domain.services.strategy.TariffedPriceCalculatorStrategyFactory;
import br.com.itau.desafioseguros.domain.services.strategy.VidaTariffedPriceCalculatorStrategy;
import br.com.itau.desafioseguros.domain.valueobjects.InsuranceProductCategory;
import br.com.itau.desafioseguros.domain.valueobjects.InsuranceProductId;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AddInsuranceProductCommandHandlerTest {

    @InjectMocks
    private AddInsuranceProductCommandHandler addInsuranceProductCommandHandler;

    @Mock
    private TariffedPriceCalculatorStrategyFactory strategyFactory;

    @Mock
    private CommandValidator validator;

    @Mock
    private AddInsuranceProductRepository repository;

    @Mock
    private EventBus eventBus;

    @Captor
    private ArgumentCaptor<InsuranceProduct> insuranceProductArgumentCaptor;

    @Test
    void handle_test() {
        UUID uuid = UUID.fromString("d16a4f7d-fa2c-4ea1-ac9c-c2fce8088541");
        when(strategyFactory.getStrategy(any(InsuranceProductCategory.class))).thenReturn(new VidaTariffedPriceCalculatorStrategy());
        when(repository.add(insuranceProductArgumentCaptor.capture())).thenReturn(InsuranceProduct.create(new InsuranceProductId(uuid),
                "teste",
                InsuranceProductCategory.VIDA,
                BigDecimal.valueOf(100),
                BigDecimal.valueOf(105)));

        AddInsuranceProductCommandResponse response =
                addInsuranceProductCommandHandler.handle(new AddInsuranceProductCommand("teste", "VIDA", BigDecimal.valueOf(100)));

        assertEquals("teste", response.getName());
        assertEquals("d16a4f7d-fa2c-4ea1-ac9c-c2fce8088541", response.getId().toString());
        assertEquals("VIDA", response.getCategory());
        assertEquals(new BigDecimal("100"), response.getBasePrice());
        assertEquals(new BigDecimal("105.00"), response.getTariffedPrice());

        InsuranceProduct insuranceProduct = insuranceProductArgumentCaptor.getValue();
        assertNotNull(insuranceProduct.getInsuranceProductId());
        assertEquals("teste", insuranceProduct.getName());
        assertEquals(InsuranceProductCategory.VIDA, insuranceProduct.getCategory());
        assertEquals(new BigDecimal("100"), insuranceProduct.getBasePrice());
        assertEquals(new BigDecimal("103.200"), insuranceProduct.getTariffedPrice());
    }

}