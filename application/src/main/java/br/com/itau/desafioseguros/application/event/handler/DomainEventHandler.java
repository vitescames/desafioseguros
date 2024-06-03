package br.com.itau.desafioseguros.application.event.handler;

import br.com.itau.desafioseguros.domain.event.DomainEvent;

public interface DomainEventHandler {
    void handle(DomainEvent event);
}
