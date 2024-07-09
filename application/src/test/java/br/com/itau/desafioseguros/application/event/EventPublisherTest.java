package br.com.itau.desafioseguros.application.event;

import br.com.itau.desafioseguros.application.event.handlers.InsuranceProductCreatedEventHandler;
import br.com.itau.desafioseguros.domain.valueobjects.InsuranceProductId;
import br.com.itau.desafioseguros.domain.events.InsuranceProductCreated;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Set;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.Mockito.atMostOnce;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EventPublisherTest {

    private EventPublisher eventPublisher;

    @Test
    void publish_test() {
        InsuranceProductCreatedEventHandler insuranceProductCreatedEventHandler1 = mock(InsuranceProductCreatedEventHandler.class);
        InsuranceProductCreatedEventHandler insuranceProductCreatedEventHandler2 = mock(InsuranceProductCreatedEventHandler.class);

        when(insuranceProductCreatedEventHandler1.shouldHandle(any())).thenReturn(true);
        when(insuranceProductCreatedEventHandler2.shouldHandle(any())).thenReturn(false);

        eventPublisher = new EventPublisher(Set.of(insuranceProductCreatedEventHandler1, insuranceProductCreatedEventHandler2));

        eventPublisher.publish(new InsuranceProductCreated(new InsuranceProductId(UUID.randomUUID())));

        verify(insuranceProductCreatedEventHandler1, atMostOnce()).handle(any());
        verify(insuranceProductCreatedEventHandler2, never()).handle(any());
    }

    @Test
    void publish_handlersListIsNull_test() {
        eventPublisher = new EventPublisher(null);
        assertDoesNotThrow(() -> eventPublisher.publish(new InsuranceProductCreated(new InsuranceProductId(UUID.randomUUID()))));
    }

}