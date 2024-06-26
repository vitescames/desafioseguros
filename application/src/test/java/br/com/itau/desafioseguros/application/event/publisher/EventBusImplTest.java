package br.com.itau.desafioseguros.application.event.publisher;

import br.com.itau.desafioseguros.application.event.handler.InsuranceProductCreatedEventHandler;
import br.com.itau.desafioseguros.domain.events.InsuranceProductCreated;
import br.com.itau.desafioseguros.domain.valueobjects.InsuranceProductId;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.only;
import static org.mockito.Mockito.verify;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@ExtendWith(MockitoExtension.class)
class EventBusImplTest {

    private final EventBusImpl eventBusImpl = new EventBusImpl();

    @Test
    void publish_test() {
        InsuranceProductCreatedEventHandler insuranceProductCreatedEventHandler1 = mock(InsuranceProductCreatedEventHandler.class);
        InsuranceProductCreatedEventHandler insuranceProductCreatedEventHandler2 = mock(InsuranceProductCreatedEventHandler.class);

        eventBusImpl.registerHandler(InsuranceProductCreated.class, insuranceProductCreatedEventHandler1);
        eventBusImpl.registerHandler(InsuranceProductCreated.class, insuranceProductCreatedEventHandler2);

        eventBusImpl.publish(new InsuranceProductCreated(new InsuranceProductId(UUID.randomUUID())));

        verify(insuranceProductCreatedEventHandler1, only()).handle(any());
        verify(insuranceProductCreatedEventHandler2, only()).handle(any());
    }

    @Test
    void publish_handlersListIsNull_test() {
        assertDoesNotThrow(() -> eventBusImpl.publish(new InsuranceProductCreated(new InsuranceProductId(UUID.randomUUID()))));
    }

}