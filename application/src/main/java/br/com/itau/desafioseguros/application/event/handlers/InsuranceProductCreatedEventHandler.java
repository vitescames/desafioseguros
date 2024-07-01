package br.com.itau.desafioseguros.application.event.handlers;

import br.com.itau.desafioseguros.domain.events.DomainEvent;
import br.com.itau.desafioseguros.domain.events.InsuranceProductCreated;

public class InsuranceProductCreatedEventHandler implements DomainEventHandler {

    @Override
    public void handle(DomainEvent event) {
        InsuranceProductCreated insuranceProductCreated = (InsuranceProductCreated) event;
        System.out.printf("Processando evento %s, com o ID %s na data %s",
                event.getClass(), insuranceProductCreated.getInsuranceProductId().getId(), event.ocurredOn());
    }

    @Override
    public boolean shouldHandle(DomainEvent event) {
        return event instanceof InsuranceProductCreated;
    }
}
