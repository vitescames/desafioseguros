package br.com.itau.desafioseguros.domain.valueobjects;

import br.com.itau.desafioseguros.domain.exceptions.InsuranceProductIdNullException;

import java.util.UUID;

public class InsuranceProductId {

    private final UUID id;

    public InsuranceProductId(UUID id) {
        if (id == null)
            throw new InsuranceProductIdNullException();

        this.id = id;
    }

    public UUID getId() {
        return id;
    }
}
