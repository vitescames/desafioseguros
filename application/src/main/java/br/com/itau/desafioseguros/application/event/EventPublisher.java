package br.com.itau.desafioseguros.application.event;

import br.com.itau.desafioseguros.application.event.handlers.DomainEventHandler;
import br.com.itau.desafioseguros.domain.events.DomainEvent;

import java.util.Set;

public class EventPublisher {

    private final Set<DomainEventHandler> handlers;

    public EventPublisher(Set<DomainEventHandler> handlers) {
        this.handlers = handlers;
    }

    public void publish(DomainEvent event) {
        if (handlers != null) {
            handlers.forEach(handler -> {
                if (handler.shouldHandle(event))
                    handler.handle(event);
            });
        }
    }
}
