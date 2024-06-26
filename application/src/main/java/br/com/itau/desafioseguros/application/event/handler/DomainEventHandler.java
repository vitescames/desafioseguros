package br.com.itau.desafioseguros.application.event.handler;

import br.com.itau.desafioseguros.domain.events.DomainEvent;

public interface DomainEventHandler<T extends DomainEvent> {
    void handle(T event);
}
