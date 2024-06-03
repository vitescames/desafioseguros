package br.com.itau.desafioseguros.application.event.publisher;

import br.com.itau.desafioseguros.application.event.handler.DomainEventHandler;
import br.com.itau.desafioseguros.domain.event.DomainEvent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EventBusImpl implements EventBus {
    private final Map<Class<? extends DomainEvent>, List<DomainEventHandler>> handlers = new HashMap<>();

    @Override
    public <T extends DomainEvent> void registerHandler(Class<T> eventType, DomainEventHandler handler) {
        handlers.computeIfAbsent(eventType, k -> new ArrayList<>()).add(handler);
    }

    @Override
    public void publish(DomainEvent event) {
        List<DomainEventHandler> eventHandlers = handlers.get(event.getClass());
        if (eventHandlers != null) {
            eventHandlers.forEach(handler -> handler.handle(event));
        }
    }
}
