package br.com.itau.desafioseguros.domain.valueobjects;

import java.util.UUID;

public class InsuranceProductId {

    private UUID id;

    public InsuranceProductId(UUID id) {
        this.id = id;
    }

    public UUID getId() {
        return id;
    }
}
