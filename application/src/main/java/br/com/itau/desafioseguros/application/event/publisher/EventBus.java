package br.com.itau.desafioseguros.application.event.publisher;

import br.com.itau.desafioseguros.application.event.handler.DomainEventHandler;
import br.com.itau.desafioseguros.domain.events.DomainEvent;

public interface EventBus {
    <T extends DomainEvent> void registerHandler(Class<T> eventType, DomainEventHandler<T> handler);

    <T extends DomainEvent> void publish(T event);
}
