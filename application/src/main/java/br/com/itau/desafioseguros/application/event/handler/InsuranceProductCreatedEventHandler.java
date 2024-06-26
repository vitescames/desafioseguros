package br.com.itau.desafioseguros.application.event.handler;

import br.com.itau.desafioseguros.domain.events.InsuranceProductCreated;

public class InsuranceProductCreatedEventHandler implements DomainEventHandler<InsuranceProductCreated> {
    @Override
    public void handle(InsuranceProductCreated event) {
        System.out.printf("Processando evento %s, com o ID %s na data %s",
                event.getClass(), event.getInsuranceProductId().getId(), event.ocurredOn());
    }
}
