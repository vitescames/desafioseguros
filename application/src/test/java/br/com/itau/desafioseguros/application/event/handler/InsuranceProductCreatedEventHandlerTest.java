package br.com.itau.desafioseguros.application.event.handler;

import br.com.itau.desafioseguros.domain.events.InsuranceProductCreated;
import br.com.itau.desafioseguros.domain.valueobjects.InsuranceProductId;
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