package br.com.itau.desafioseguros.application.event.handler;

import br.com.itau.desafioseguros.domain.event.DomainEvent;

public class InsuranceProductCreatedEventHandler implements DomainEventHandler {
    @Override
    public void handle(DomainEvent event) {
        System.out.printf("Processando evento %s", event.getClass());
    }
}
