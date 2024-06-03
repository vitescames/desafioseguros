package br.com.itau.desafioseguros.application.event.publisher;

import br.com.itau.desafioseguros.application.event.handler.DomainEventHandler;
import br.com.itau.desafioseguros.domain.event.DomainEvent;

public interface EventBus {
    <T extends DomainEvent> void registerHandler(Class<T> eventType, DomainEventHandler handler);

    void publish(DomainEvent event);
}
