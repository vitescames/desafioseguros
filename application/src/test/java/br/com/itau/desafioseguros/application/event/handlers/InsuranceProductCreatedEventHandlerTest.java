package br.com.itau.desafioseguros.application.event.handlers;

import br.com.itau.desafioseguros.domain.valueobjects.InsuranceProductId;
import br.com.itau.desafioseguros.domain.events.InsuranceProductCreated;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class InsuranceProductCreatedEventHandlerTest {

    private final InsuranceProductCreatedEventHandler insuranceProductCreatedEventHandler = new InsuranceProductCreatedEventHandler();

    @Test
    void handle_test() {
        assertDoesNotThrow(() -> insuranceProductCreatedEventHandler
                .handle(new InsuranceProductCreated(new InsuranceProductId(UUID.randomUUID()))));
    }

}