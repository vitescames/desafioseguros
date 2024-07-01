package br.com.itau.desafioseguros.application.event.handlers;

import br.com.itau.desafioseguros.domain.events.DomainEvent;

public interface DomainEventHandler {
    void handle(DomainEvent event);

    boolean shouldHandle(DomainEvent event);
}
