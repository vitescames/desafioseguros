package br.com.itau.desafioseguros.domain.event;

import br.com.itau.desafioseguros.domain.valueobjects.InsuranceProductId;

public class InsuranceProductCreated extends DomainEvent {

    private final InsuranceProductId insuranceProductId;

    public InsuranceProductCreated(InsuranceProductId insuranceProductId) {
        super();
        this.insuranceProductId = insuranceProductId;
    }

    public InsuranceProductId getInsuranceProductId() {
        return insuranceProductId;
    }

}
