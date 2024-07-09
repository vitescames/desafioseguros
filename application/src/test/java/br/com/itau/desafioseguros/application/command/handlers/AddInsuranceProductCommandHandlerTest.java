package br.com.itau.desafioseguros.application.command.handlers;

import br.com.itau.desafioseguros.application.command.AddInsuranceProductCommand;
import br.com.itau.desafioseguros.application.command.validation.CommandValidator;
import br.com.itau.desafioseguros.application.event.EventPublisher;
import br.com.itau.desafioseguros.application.command.responses.AddInsuranceProductCommandResponse;
import br.com.itau.desafioseguros.domain.valueobjects.InsuranceProductCategory;
import br.com.itau.desafioseguros.domain.repositories.AddInsuranceProductRepository;
import br.com.itau.desafioseguros.domain.services.TariffedPriceCalculatorService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AddInsuranceProductCommandHandlerTest {

    @InjectMocks
    private AddInsuranceProductCommandHandler addInsuranceProductCommandHandler;

    @Mock
    private TariffedPriceCalculatorService strategyFactory;

    @Mock
    private CommandValidator validator;

    @Mock
    private AddInsuranceProductRepository repository;

    @Mock
    private EventPublisher eventPublisher;

    @Test
    void handle_test() {
        when(strategyFactory.calculate(any(), any(InsuranceProductCategory.class))).thenReturn(new BigDecimal("103.200"));

        AddInsuranceProductCommandResponse response =
                addInsuranceProductCommandHandler.handle(new AddInsuranceProductCommand("teste", "VIDA", BigDecimal.valueOf(100)));

        assertEquals("teste", response.getName());
        assertNotNull(response.getId());
        assertEquals("VIDA", response.getCategory());
        assertEquals(new BigDecimal("100"), response.getBasePrice());
        assertEquals(new BigDecimal("103.20"), response.getTariffedPrice());
    }

}