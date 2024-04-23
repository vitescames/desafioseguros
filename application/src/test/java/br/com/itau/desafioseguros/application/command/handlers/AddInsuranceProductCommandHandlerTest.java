package br.com.itau.desafioseguros.application.command.handlers;

import br.com.itau.desafioseguros.application.command.AddInsuranceProductCommand;
import br.com.itau.desafioseguros.application.command.responses.AddInsuranceProductCommandResponse;
import br.com.itau.desafioseguros.application.command.validation.CommandValidator;
import br.com.itau.desafioseguros.domain.entities.InsuranceProduct;
import br.com.itau.desafioseguros.domain.enums.InsuranceProductCategory;
import br.com.itau.desafioseguros.domain.repositories.AddInsuranceProductRepository;
import br.com.itau.desafioseguros.domain.strategy.TariffedPriceCalculatorStrategyFactory;
import br.com.itau.desafioseguros.domain.strategy.VidaTariffedPriceCalculatorStrategy;
import br.com.itau.desafioseguros.domain.valueobjects.InsuranceProductId;
import br.com.itau.desafioseguros.domain.valueobjects.InsuranceProductName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AddInsuranceProductCommandHandlerTest {

    @Mock
    private TariffedPriceCalculatorStrategyFactory strategyFactory;

    @Mock
    private CommandValidator<AddInsuranceProductCommand> validator;

    @Mock
    private AddInsuranceProductRepository repository;

    @InjectMocks
    private AddInsuranceProductCommandHandler addInsuranceProductCommandHandler;

    @Captor
    private ArgumentCaptor<InsuranceProduct> insuranceProductArgumentCaptor;

    @Test
    void handle_test() {
        UUID uuid = UUID.fromString("d16a4f7d-fa2c-4ea1-ac9c-c2fce8088541");
        when(strategyFactory.getStrategy(any(InsuranceProductCategory.class))).thenReturn(new VidaTariffedPriceCalculatorStrategy());
        when(repository.add(insuranceProductArgumentCaptor.capture())).thenReturn(InsuranceProduct.create(new InsuranceProductId(uuid),
                new InsuranceProductName("teste"),
                InsuranceProductCategory.VIDA,
                100f,
                105f));

        AddInsuranceProductCommandResponse response =
                addInsuranceProductCommandHandler.handle(new AddInsuranceProductCommand("teste", "VIDA", 100f));

        assertEquals("teste", response.getName());
        assertEquals("d16a4f7d-fa2c-4ea1-ac9c-c2fce8088541", response.getId().toString());
        assertEquals("VIDA", response.getCategory());
        assertEquals(100f, response.getBasePrice());
        assertEquals(105f, response.getTariffedPrice());

        InsuranceProduct insuranceProduct = insuranceProductArgumentCaptor.getValue();
        assertNotNull(insuranceProduct.getInsuranceProductId());
        assertEquals("teste", insuranceProduct.getInsuranceProductName().getName());
        assertEquals(InsuranceProductCategory.VIDA, insuranceProduct.getInsuranceProductCategory());
        assertEquals(100f, insuranceProduct.getInsuranceProductBasePrice());
        assertEquals(103.2f, insuranceProduct.getInsuranceProductTariffedPrice());
    }

}